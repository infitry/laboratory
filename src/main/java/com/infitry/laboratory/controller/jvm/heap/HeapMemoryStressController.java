package com.infitry.laboratory.controller.jvm.heap;

import com.infitry.laboratory.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/jvm/heap")
public class HeapMemoryStressController {

    @GetMapping("/stress")
    public void stress() throws InterruptedException {
        var list = new ArrayList<MemberDto>();
        IntStream.range(0, 10000).forEach(it -> {
            var memberDto = new MemberDto(null, null, "name" + it, "nick" + it, "pass" + 1, it);
            list.add(memberDto);
        });
        System.out.println("[" + Thread.currentThread().getName() + "] List size : " + list.size());
        Thread.sleep(1000);
    }
}
