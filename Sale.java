public abstract class Sale implements Chargeable {
    private String id;
    private String customerName;
    private int quantity;
    private double unitPrice;
    private boolean discountEligible;

    protected Sale(String id, String customerName, int quantity, double unitPrice, boolean discountEligible)
            throws InvalidSaleException {
        setId(id);
        setCustomerName(customerName);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        this.discountEligible = discountEligible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws InvalidSaleException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidSaleException("Sale ID cannot be blank.");
        }
        this.id = id.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) throws InvalidSaleException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidSaleException("Customer name cannot be blank.");
        }
        this.customerName = customerName.trim();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws InvalidSaleException {
        if (quantity <= 0) {
            throw new InvalidSaleException("Quantity must be greater than zero.");
        }
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) throws InvalidSaleException {
        if (unitPrice <= 0) {
            throw new InvalidSaleException("Unit price must be greater than zero.");
        }
        this.unitPrice = unitPrice;
    }

    public boolean isDiscountEligible() {
        return discountEligible;
    }

    public void setDiscountEligible(boolean discountEligible) {
        this.discountEligible = discountEligible;
    }

    protected double applyDiscountIfEligible(double amount) {
        if (discountEligible) {
            return amount * 0.95;
        }
        return amount;
    }

    public abstract double getUndiscountedCharge();

    public final double getDiscountAmount() {
        double undiscounted = getUndiscountedCharge();
        return undiscounted - calculateCharge();
    }

    public String getRecordType() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getRecordType() + "{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + String.format("%.2f", unitPrice) +
                ", charge=" + String.format("%.2f", calculateCharge()) +
                ", discountEligible=" + discountEligible +
                '}';
    }
}
