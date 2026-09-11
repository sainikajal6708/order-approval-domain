package com.rabtech.order.domain.event;
import com.rabtech.order.domain.OrderId;
import java.time.Instant;
public record OrderCancelled(OrderId orderId, String reason, Instant occurredAt) implements DomainEvent{}
