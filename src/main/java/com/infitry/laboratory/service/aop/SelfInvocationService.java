package com.infitry.laboratory.service.aop;

import com.infitry.laboratory.dto.OrderDto;
import com.infitry.laboratory.entity.Order;
import com.infitry.laboratory.persistence.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SelfInvocationService {

    private final OrderRepository orderRepository;

    public OrderDto useSelfInvocation() {
        var savedOrder = createNewOrder();
        child(savedOrder);
        return orderRepository.findById(savedOrder.getId())
                .map(OrderDto::from)
                .orElseThrow();
    }

    @Transactional
    public OrderDto unUseSelfInvocation() {
        var savedOrder = createNewOrder();
        child(savedOrder);
        return orderRepository.findById(savedOrder.getId())
                .map(OrderDto::from)
                .orElseThrow();
    }

    /**
     * Self-invocation 으로 인해 트랜잭션이 적용될 수 없음.
     * 따라서 트랜잭션 커밋이 발생하지 않는다.
     */
    @Transactional
    public void child(Order order) {
        order.setOrderName("order2");
    }

    private Order createNewOrder() {
        var order = new Order();
        order.setOrderName("order1");
        return orderRepository.save(order);
    }
}
