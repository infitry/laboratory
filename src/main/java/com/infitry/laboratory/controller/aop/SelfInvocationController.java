package com.infitry.laboratory.controller.aop;

import com.infitry.laboratory.dto.OrderDto;
import com.infitry.laboratory.service.aop.SelfInvocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/self-invocation")
@RequiredArgsConstructor
public class SelfInvocationController {
    private final SelfInvocationService selfInvocationService;

    @GetMapping("/1")
    public OrderDto useSelfInvocation() {
        return selfInvocationService.useSelfInvocation();
    }

    @GetMapping("/2")
    public OrderDto unUseSelfInvocation() {
        return selfInvocationService.unUseSelfInvocation();
    }
}
