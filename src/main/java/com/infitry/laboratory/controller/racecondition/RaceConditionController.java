package com.infitry.laboratory.controller.racecondition;

import com.infitry.laboratory.service.racecondition.RaceConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/race-condition")
public class RaceConditionController {

    private final RaceConditionService raceConditionService;

    @GetMapping("/{millis}")
    public String start(@PathVariable Long millis) throws InterruptedException {
        raceConditionService.raceCondition(millis);
        return "OK";
    }
}
