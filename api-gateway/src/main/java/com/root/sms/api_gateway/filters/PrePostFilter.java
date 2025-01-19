package com.root.sms.api_gateway.filters;

import com.root.sms.api_gateway.configurations.ConsulConfig;
import com.root.sms.api_gateway.constants.LoggingConstants;
import com.root.sms.api_gateway.exception.GlobalException;
import com.root.sms.api_gateway.helpers.PreFilterCookieRefresher;
import com.root.sms.api_gateway.helpers.PreFilterHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class PrePostFilter implements GlobalFilter, Ordered {

    private final ConsulConfig consulConfig;

    private final PreFilterHelper prefilterHelper;

    private final PreFilterCookieRefresher preFilterCookieRefresher;

    @Autowired
    public PrePostFilter(ConsulConfig consulConfig,
                         PreFilterHelper prefilterHelper,
                         PreFilterCookieRefresher preFilterCookieRefresher){
        this.consulConfig = consulConfig;
        this.prefilterHelper = prefilterHelper;
        this.preFilterCookieRefresher = preFilterCookieRefresher;
    }

    private boolean isWhiteListedUrl(String requestUrl){
        List<String> whiteListedUrls = consulConfig.getWhitelistedUrls();
        return whiteListedUrls.stream().anyMatch(requestUrl::contains);
    }

    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try{
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            String requestUrl = serverHttpRequest.getURI().toString();
            if(isWhiteListedUrl(requestUrl)){
                serverHttpRequest = prefilterHelper.createSession(serverHttpRequest);
                ServerWebExchange finalExchange = exchange.mutate().request(serverHttpRequest).build();
                return chain.filter(finalExchange);
            }
            else {
                prefilterHelper.validateRequest(serverHttpRequest, requestUrl);
                ServerHttpResponse response = exchange.getResponse();
                preFilterCookieRefresher.refreshSessionIfNeeded(requestUrl, serverHttpRequest, response);
                ServerWebExchange finalExchange = exchange.mutate().request(serverHttpRequest).response(response).build();
                return chain.filter(finalExchange);
            }

        }
        catch (GlobalException e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, "filter",
                    e.getCause(), e.getMessage(), "");
            throw e;
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
