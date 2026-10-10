public class PrescriptionSale extends Sale {
    public PrescriptionSale(String id, String customerName, int quantity, double unitPrice, boolean discountEligible)
            throws InvalidSaleException {
        super(id, customerName, quantity, unitPrice, discountEligible);
    }

    @Override
    public double calculateCharge() {
        return applyDiscountIfEligible(getUndiscountedCharge());
    }

    @Override
    public double getUndiscountedCharge() {
        return getQuantity() * getUnitPrice();
    }
}
