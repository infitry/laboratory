package com.infitry.laboratory.service.transaction.propagation;

import com.infitry.laboratory.entity.Order;
import com.infitry.laboratory.persistence.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropagationInternalService {

    private final OrderRepository orderRepository;

    @Transactional(propagation = REQUIRED)
    public void required() {
        log.info("활성된 트랜잭션이 있다면 기존 트랜잭션을 사용합니다.");
        saveNewOrder();
    }

    @Transactional(propagation = REQUIRES_NEW)
    public void requiresNew() {
        log.info("항상 새로운 트랜잭션을 사용합니다. 이미 진행중인 트랜잭션이 있다면 보류하고 해당 트랜잭션을 먼저 진행합니다.");
        saveNewOrder();
    }

    @Transactional(propagation = SUPPORTS)
    public void supports() {
        log.info("활성된 트랜잭션이 있다면 기존 트랜잭션을 사용하고 없으면 트랜잭션 없이 실행합니다.");
        saveNewOrder();
    }

    @Transactional(propagation = NOT_SUPPORTED)
    public void notSupported() {
        log.info("활성화 트랜잭션이 있던 없던 Transaction 없이 실행합니다.");
        saveNewOrder();
    }

    @Transactional(propagation = MANDATORY)
    public void mandatory() {
        log.info("부모 트랜잭션 내에서 실행되며, 부모 트랜잭션이 없을 경우 Exception 이 발생합니다.");
        saveNewOrder();
    }

    @Transactional(propagation = NESTED)
    public void nested() {
        log.info("부모 트랜잭션이 커밋될 때 같이 커밋, 자식 트랜잭션의 롤백은 부모 트랜잭션에 영향이 없습니다.");
        saveNewOrder();
    }

    @Transactional(propagation = NEVER)
    public void never() {
        log.info("트랜잭션 없이 실행되며 부모 트랜잭션이 존재하면 Exception 이 발생합니다.");
        saveNewOrder();
    }

    private void saveNewOrder() {
        Order order = new Order();
        order.setOrderName("신규주문-1");
        orderRepository.save(order);
        throw new RuntimeException("rollback");
    }
}
