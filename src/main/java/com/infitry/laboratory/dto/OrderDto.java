package com.infitry.laboratory.dto;

import com.infitry.laboratory.entity.Order;

public record OrderDto(
    Long orderId,
    String orderName
) {
    public static OrderDto from(Order order) {
        return new OrderDto(order.getId(), order.getOrderName());
    }
}
