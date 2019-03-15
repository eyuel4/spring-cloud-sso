/*
package com.sample.oauth2.invite_gateway.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    public static final String COORELATION_ID = "wg-correlation-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";
    public static final String AUTH_TOKEN = "Authorization";

    public String getCoorelationId() {
        RequestContext reqCtx = RequestContext.getCurrentContext();

        if (reqCtx.getRequest().getHeader(COORELATION_ID) != null) {
            return reqCtx.getRequest().getHeader(COORELATION_ID);
        }
        else {
            return reqCtx.getZuulRequestHeaders().get(COORELATION_ID);
        }
    }

    public void setCoorelationId(String correlationId) {
        RequestContext reqCtx = RequestContext.getCurrentContext();
        reqCtx.addZuulRequestHeader(COORELATION_ID, correlationId);
    }

    public final String getAuthToken() {
        RequestContext reqCtx = RequestContext.getCurrentContext();
        return reqCtx.getRequest().getHeader(AUTH_TOKEN);
    }

    public boolean isCorrelationIdPresent(){
        if (getCoorelationId() !=null){
            return true;
        }

        return false;
    }
}
*/
