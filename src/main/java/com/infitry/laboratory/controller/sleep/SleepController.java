package com.infitry.laboratory.controller.sleep;

import com.infitry.laboratory.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sleep")
@RequiredArgsConstructor
public class SleepController {

    private final SleepService sleepService;

    @GetMapping("/{seconds}")
    public ResponseEntity<String> sleep(@PathVariable long seconds) {
        sleepService.sleep(seconds * 1000);
        return ResponseEntity.ok("OK");
    }
}
