package com.infitry.laboratory.controller.gracefull;


import com.infitry.laboratory.service.SleepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test graceful shutdown
 * start application with "java -jar" command after build
 * and insert "kill -9" or "kill -15" on your terminal or linux
 * when you insert "kill -9" on your terminal or linux, doesn't work graceful shutdown,
 * but you insert "kill -15" on your terminal or linux, it  works
 *
 */
@RestController
@RequestMapping("/graceful")
@RequiredArgsConstructor
public class GracefulShutdownController {

    private final SleepService sleepService;

    @GetMapping("/sleep/{milliseconds}")
    public void sleep(@PathVariable Long milliseconds) {
        sleepService.sleep(milliseconds);
    }
}
