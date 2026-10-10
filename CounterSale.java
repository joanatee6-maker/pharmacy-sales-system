public class CounterSale extends Sale {
    private static final double PACKAGING_FEE = 1000.00;

    public CounterSale(String id, String customerName, int quantity, double unitPrice, boolean discountEligible)
            throws InvalidSaleException {
        super(id, customerName, quantity, unitPrice, discountEligible);
    }

    @Override
    public double calculateCharge() {
        return applyDiscountIfEligible(getUndiscountedCharge());
    }

    @Override
    public double getUndiscountedCharge() {
        return (getQuantity() * getUnitPrice()) + PACKAGING_FEE;
    }
}
