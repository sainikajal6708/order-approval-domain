package com.rabtech.order.domain.event;
import com.rabtech.order.domain.OrderId;
import java.time.Instant;
public record PaymentRecorded(OrderId orderId, Instant occurredAt) implements DomainEvent{}
