package com.rabtech.order.domain.event;
import com.rabtech.order.domain.OrderId;
import java.time.Instant;
public record OrderConfirmed(OrderId orderId, Instant occurredAt) implements DomainEvent{}
