package com.rabtech.order.port;
import com.rabtech.order.domain.*;
import java.util.Optional;
public interface OrderRepository{ 
  void save(Order o); 
  Optional<Order> findById(OrderId id); 
}
