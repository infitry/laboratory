package com.infitry.laboratory.controller.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/web-client/time-out")
@RequiredArgsConstructor
public class WebClientTimeoutController {

    private final WebClient webClient;

    @GetMapping("/default/read/{seconds}")
    public ResponseEntity<String> readTimeout(@PathVariable String seconds) {

        var response = webClient.get()
                .uri("http://localhost:8080/sleep/" + seconds)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/request/read/{sleepSeconds}/{timeoutSeconds}")
    public ResponseEntity<String> requestReadTimeout(@PathVariable String sleepSeconds, @PathVariable long timeoutSeconds) {

        var response = webClient.get()
                .uri("http://localhost:8080/sleep/" + sleepSeconds)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(timeoutSeconds));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/reactive/read/{sleepSeconds}/{timeoutSeconds}")
    public ResponseEntity<String> reactiveReadTimeout(@PathVariable String sleepSeconds, @PathVariable long timeoutSeconds) {

        var response = webClient.get()
                .uri("http://localhost:8080/sleep/" + sleepSeconds)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .block();

        return ResponseEntity.ok(response);
    }
}
