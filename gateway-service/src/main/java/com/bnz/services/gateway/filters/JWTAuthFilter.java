package com.bnz.services.gateway.filters;

import com.bnz.services.gateway.Models.ResponseModel;
import com.bnz.shared.exceptions.JwtTokenMalformedException;
import com.bnz.shared.exceptions.JwtTokenMissingException;
import com.bnz.shared.security.tokens.JWTokenHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
@Component
public class JWTAuthFilter implements GatewayFilter {

    @Autowired
    private JWTokenHandler jwTokenHandler = new JWTokenHandler();

    private final List<String> openEndPoints = Arrays.asList(
            "/auth/login",
            "/auth/register",
            "/auth/ehlo",
            "/status/info"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        try {
            Predicate<ServerHttpRequest> isEndpointSecured = r -> openEndPoints.stream().noneMatch(uri -> r.getURI().getPath().contains(uri));

            if(isEndpointSecured.test(request)) {
                if(!request.getHeaders().containsKey("Authorization")) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }
                HttpHeaders headers = request.getHeaders();
                jwTokenHandler.validate(headers);

                Claims claims = jwTokenHandler.getDataFromTokens(headers);
                exchange.getRequest().mutate().header("X-BNZ-TOKEN-VALIDATION", claims.getId());
            }

        } catch (JwtTokenMissingException | JwtTokenMalformedException e) {
            return getResponse(response, HttpStatus.BAD_REQUEST, e.getMessage());
        } catch(ResponseStatusException e) {
            return getResponse(response, e.getStatus(), "You need to be authenticated to use this route!");
        } catch (Exception e) {
            return getResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return chain.filter(exchange);
    }

    private Mono<Void> getResponse(ServerHttpResponse response, HttpStatus statusCode, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response.setStatusCode(statusCode);
            response.getHeaders().add("Content-Type", "application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(new ResponseModel<String>(false, message));
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
