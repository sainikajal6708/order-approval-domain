package com.rabtech.order.adapter;
import com.rabtech.order.domain.*;
import com.rabtech.order.port.OrderRepository;
import java.util.*;
public class InMemoryOrderRepository implements OrderRepository{
  Map<String,Order> s=new HashMap<>();
  public void save(Order o){ s.put(o.getId().value(),o); }
  public Optional<Order> findById(OrderId id){ return Optional.ofNullable(s.get(id.value())); }
}
