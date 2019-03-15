/*
package com.sample.oauth2.invite_gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//TODO To be done with spring cloud seluth (need more reading)
@Component
public class TrackingLogFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER=true;

    private static final Logger logger = LoggerFactory.getLogger(TrackingLogFilter.class);

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return filterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (filterUtils.isCorrelationIdPresent()) {
            logger.debug("WG-correlation-id found in tracking filter: {}", filterUtils.getCoorelationId());
            System.out.println("WG-correlation-id found in tracking filter: {} " + filterUtils.getCoorelationId());
        } else {
            filterUtils.setCoorelationId(generateCorrelationId());
            logger.debug("WG-correlation-id generated in tracking filter: {}.", filterUtils.getCoorelationId());
            System.out.println("WG-correlation-id found in tracking filter: {} " + filterUtils.getCoorelationId());
        }
        String debug = convertToPrettyPrintString(Debug.getRoutingDebug());
        logger.debug("Filter Debug Info = \n{}", debug);
        return null;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    private String convertToPrettyPrintString(List<String> filterDebugList) {
        return filterDebugList.stream()
                              .map(s -> s.startsWith("{") ? "\t" + s : s)
                              .collect(Collectors.joining("\n"));
    }
}
*/
