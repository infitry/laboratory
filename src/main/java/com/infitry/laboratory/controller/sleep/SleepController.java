package com.infitry.laboratory.controller.sleep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sleep")
public class SleepController {

    @GetMapping("/{seconds}")
    public ResponseEntity<String> sleep(@PathVariable long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            log.info("일어났다!");
        } catch (Exception e) {
            log.error("자는 도중 에러 발생", e);
        }
        return ResponseEntity.ok("OK");
    }
}
