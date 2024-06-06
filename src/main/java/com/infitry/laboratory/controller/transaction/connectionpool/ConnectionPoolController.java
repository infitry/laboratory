package com.infitry.laboratory.controller.transaction.connectionpool;

import com.infitry.laboratory.service.transaction.connectionpool.ConnectionPoolFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection-pool")
@RequiredArgsConstructor
public class ConnectionPoolController {

    private final ConnectionPoolFacade connectionPoolFacade;

    @GetMapping("before")
    public void connectionPoolBefore() throws Exception {
        connectionPoolFacade.doSomethingBefore();
    }

    @GetMapping("after")
    public void connectionPoolAfter() throws Exception {
        connectionPoolFacade.doSomethingAfter();
    }
}
