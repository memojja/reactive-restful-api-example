package com.reative.reative.config;

import com.reative.reative.RoutersHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ReactiveConfig {

    @Bean
    RouterFunction<?> routerFunction(RoutersHandlers routersHandlers){
        return RouterFunctions
                .route(
                    RequestPredicates.GET("/api/employee"),routersHandlers::getAll)
                .andRoute(
                        RequestPredicates.GET("/api/employee/{id}"),routersHandlers::getId);
    }
}
