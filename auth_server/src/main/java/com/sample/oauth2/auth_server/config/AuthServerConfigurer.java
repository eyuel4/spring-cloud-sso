package com.sample.oauth2.auth_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@Order(6)
public class AuthServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("InviteEntry")
               .secret("invitepass")
               .scopes(new String[]{"read", "write"})
               .authorizedGrantTypes(new String[]{"authorization_code", "refresh_token"})
               //, "implicit", "password","client_credentials"})
               .autoApprove(true).accessTokenValiditySeconds(600)
               .refreshTokenValiditySeconds(1800)
               .redirectUris("http://localhost:2020/");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("certificate/wgresorts.jks"), "wgresorts".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("wgresorts"));
        return converter;
    }

    //https://github.com/spring-projects/spring-security-oauth/issues/140#
/*    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        endpoints.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request,
                                   HttpServletResponse response, Object handler,
                                   ModelAndView modelAndView) throws Exception {
                if (modelAndView != null
                        && modelAndView.getView() instanceof RedirectView) {
                    RedirectView redirect = (RedirectView) modelAndView.getView();
                    String url = redirect.getUrl();
                    if (url.contains("code=") || url.contains("error=")) {
                        HttpSession session = request.getSession(false);
                        if (session != null) {
                            session.invalidate();
                        }
                    }
                }
            }
        });
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService);
    }*/

}
