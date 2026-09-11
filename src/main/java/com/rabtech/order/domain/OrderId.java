package com.rabtech.order.domain;
import java.util.*;
public final class OrderId {
  private final String value;
  public OrderId(String v){ this.value=v; }
  public static OrderId generate(){ return new OrderId(UUID.randomUUID().toString()); }
  public String value(){ return value; }
}
