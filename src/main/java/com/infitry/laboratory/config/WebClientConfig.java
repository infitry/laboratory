package com.infitry.laboratory.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class WebClientConfig {

    private final static int CONNECT_TIMEOUT = 10000;
    private final static int READ_TIMEOUT = 60000;
    private final static int WRITE_TIMEOUT = 60000;

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create(createConnectionProvider())
                .compress(true)
                .wiretap("reactor.netty.http.client.HttpClient", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)));

        // buffer size 설정 2MB (default: 256kb) -1일 때 무제한
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configure -> configure.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter(addLogInterceptor())
                .build();
    }

    private ConnectionProvider createConnectionProvider() {
        return ConnectionProvider.builder("httpConnectionProvider")
                .maxConnections(100)
                .maxIdleTime(Duration.ofSeconds(20))
                .maxLifeTime(Duration.ofSeconds(60))
                .pendingAcquireTimeout(Duration.ofSeconds(60))
                .evictInBackground(Duration.ofSeconds(120))
                .build();
    }

    private ExchangeFilterFunction addLogInterceptor() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            request.headers()
                    .forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
            return Mono.just(request);
        })
        .andThen(ExchangeFilterFunction.ofResponseProcessor(response -> {
            response.headers()
                .asHttpHeaders()
                .forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
            return Mono.just(response);
        }));
    }
}
