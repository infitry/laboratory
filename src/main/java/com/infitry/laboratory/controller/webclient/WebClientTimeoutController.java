package com.infitry.laboratory.controller.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequestMapping("/web-client/time-out")
@RequiredArgsConstructor
public class WebClientTimeoutController {

    private final WebClient webClient;

    @GetMapping("/read/{seconds}")
    public ResponseEntity<String> readTimeout(@PathVariable String seconds) {

        var response = webClient.get()
                .uri("http://localhost:8080/sleep/" + seconds)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }
}
