package com.sample.oauth2.invite_gateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sample.oauth2.invite_gateway.filter.SimpleFilter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.oauth2.client.OAuth2RestOperations;

import java.util.List;

@EnableZuulProxy
@Configuration
@EnableOAuth2Sso
public class SiteSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/webjars/**", "/error", "/login**").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/").permitAll()
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public OAuth2RestOperations restOperations(
            OAuth2ProtectedResourceDetails resource,
            OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }

    @Bean
    public SimpleFilter simpleFilter() {
      return new SimpleFilter();
    }

    @Bean
    public ZuulFilter zuulFilter(){
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "post";
            }

            @Override
            public int filterOrder() {
                return 999999;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                final List<String> routingDebug = (List<String>) RequestContext.getCurrentContext().get("routingDebug");
                routingDebug.forEach(System.out::println);
                return null;
            }
        };
    }
}
