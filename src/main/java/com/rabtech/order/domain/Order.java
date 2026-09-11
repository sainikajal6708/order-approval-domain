package com.rabtech.order.domain;
import com.rabtech.order.domain.event.*;
import com.rabtech.order.domain.exception.*;
import java.time.Instant;
import java.util.*;
public class Order {
  private final OrderId id; private OrderState state; private final List<OrderLine> lines; private final List<DomainEvent> events; 
  private Order(OrderId id){ this.id=id; this.state=OrderState.DRAFT; this.lines=new ArrayList<>(); this.events=new ArrayList<>(); }
  public static Order createNew(OrderId id){ return new Order(id); }
  public void addLine(OrderLine line){
    if(state!=OrderState.DRAFT) throw new IllegalStateTransitionException("A paid order cannot return to draft.");
    lines.add(line);
  }
  public OrderConfirmed confirm(Instant now){
    if(state!=OrderState.DRAFT) throw new IllegalStateTransitionException("Only DRAFT can be confirmed");
    if(lines.isEmpty()) throw new DomainRuleViolationException("An order must contain at least one line before confirmation.");
    state=OrderState.CONFIRMED;
    var e=new OrderConfirmed(id,now); events.add(e); return e;
  }
  public PaymentRecorded recordPayment(Instant now){
    if(state==OrderState.CANCELLED) throw new DomainRuleViolationException("A cancelled order cannot be paid.");
    if(state!=OrderState.CONFIRMED) throw new IllegalStateTransitionException("Payment only from CONFIRMED");
    state=OrderState.PAID;
    var e=new PaymentRecorded(id,now); events.add(e); return e;
  }
  public OrderCancelled cancel(String reason, Instant now){
    if(state==OrderState.PAID) throw new IllegalStateTransitionException("Paid order cannot be cancelled");
    if(state==OrderState.CANCELLED) throw new IllegalStateTransitionException("Already cancelled");
    state=OrderState.CANCELLED;
    var e=new OrderCancelled(id,reason,now); events.add(e); return e;
  }
  public Money total(){ Money sum=Money.of(0); for(var l:lines) sum=sum.add(l.lineTotal()); return sum; }
  public OrderId getId(){ return id; } public OrderState getState(){ return state; }
}
