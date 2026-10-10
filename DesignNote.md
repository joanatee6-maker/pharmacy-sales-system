# Pharmacy Sales Management System

This project is a Java console application for a pharmacy sales business. It is built according to the student scenario for:

- Student name: Atee Joan
- Registration number: VU-BIT-2511-0805-EVE
- Business: Pharmacy / Sale
- Discount rate: 5%
- Discount applies to: customers aged 60 or over
- Main report sorting: type first, then customer name alphabetically

## Features

- `Chargeable` interface with `calculateCharge()`
- Abstract `Sale` class with shared fields and validation
- Two subclasses:
  - `PrescriptionSale`
  - `CounterSale`
- Custom exception: `InvalidSaleException`
- `SaleManager` to add, find, remove and total sales
- Robust input validation using `InputValidator`
- Menu-driven console application

## Running the program

Compile:

```bash
javac *.java
```

Run:

```bash
java PharmacySystem
```

## Project files

- `Chargeable.java`
- `InvalidSaleException.java`
- `Sale.java`
- `PrescriptionSale.java`
- `CounterSale.java`
- `SaleManager.java`
- `InputValidator.java`
- `PharmacySystem.java`

## Notes

- Record IDs start with the format: `AJ805-001`
- The system uses a 5% discount for eligible senior customers
- All fields are private and validated with exceptions

## Academic note

This repository contains the complete Java source for the coursework project and is tailored to the above student scenario.
