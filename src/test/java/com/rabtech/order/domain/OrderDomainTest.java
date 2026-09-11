package com.rabtech.order.domain;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;
public class OrderDomainTest{
  @Test public void shouldConfirmOrder(){
    var id = OrderId.generate();
    var order = Order.createNew(id);
    order.addLine(new OrderLine("p1",1,Money.of(100)));
    var event = order.confirm(Instant.now());
    assertEquals(OrderState.CONFIRMED, order.getState());
    assertNotNull(event);
  }
}
