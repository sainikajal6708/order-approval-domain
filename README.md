# Order Approval Domain - RabTech Task 02
Aggregate: Order
States: DRAFT, CONFIRMED, PAID, CANCELLED
Events: OrderConfirmed, PaymentRecorded, OrderCancelled

Architecture: Hexagonal, Pure Domain (No Spring/JPA/DB annotations), Ports, In-Memory Adapters, ADR, JUnit5 Tests

Rules Covered:
- Must have at least one line before confirmation
- Quantity must be > 0
- Cancelled cannot be paid
- Paid cannot go back to Draft
- Total from immutable lines

Run: mvn test
