# Design Note

## 1. Encapsulation

Encapsulation is applied in the `Sale` class. All fields are declared as `private`, including `id`, `customerName`, `quantity`, `unitPrice`, and `discountEligible`. The class exposes controlled access through getters and setters, and the setters validate values before assignment. For example, `Sale.setCustomerName(String)` throws `InvalidSaleException` if the name is blank, and `Sale.setQuantity(int)` throws `InvalidSaleException` if the quantity is zero or negative. This protects the object state and prevents invalid data from entering the system.

## 2. Inheritance

Inheritance is used between `Sale` and its subclasses, `PrescriptionSale` and `CounterSale`. Both subclasses extend the abstract `Sale` class and inherit common fields and validation behavior. Each subclass calls the parent constructor using `super(...)`, which ensures that shared data such as ID, customer name, quantity and price are initialized consistently.

## 3. Polymorphism

Polymorphism is demonstrated in the method `calculateCharge()`. The `Chargeable` interface requires this method, and both subclasses override it with different logic:

- `PrescriptionSale.calculateCharge()` calculates `quantity * unitPrice`
- `CounterSale.calculateCharge()` calculates `(quantity * unitPrice) + 1000`

The system then works with these objects through common references, allowing the same method call to produce different results depending on the object type. The sorting logic in `SaleManager.getSortedSales()` also uses the runtime object type and the customer name to produce a consistent report.

## 4. Abstraction

Abstraction is represented by the `Chargeable` interface and the abstract `Sale` class. The `Chargeable` interface declares the essential operation `double calculateCharge()`, while the `Sale` class provides the template for all sales. The concrete subclasses provide the actual implementation details. This separates what a sale should do from how each specific sale calculates its charge.

## 5. Interface usage

The `Chargeable` interface is the key contract used across the project. Any class representing a chargeable record must provide a `calculateCharge()` method. This allows the manager and menu system to treat each sale record uniformly, even when the actual calculation logic is different.

## 6. Summary

This design uses object-oriented principles effectively: private fields and validation provide encapsulation, subclassing creates inheritance, method overriding enables polymorphism, and abstract classes and interfaces support abstraction. Together, these principles produce a clean, reusable and maintainable pharmacy sales system.
