import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SaleManager {
    private final ArrayList<Sale> sales;
    private int nextNumber;
    private final String idPrefix;

    public SaleManager(String idPrefix) {
        this.sales = new ArrayList<>();
        this.idPrefix = idPrefix;
        this.nextNumber = 1;
    }

    public String createNextId() {
        String nextId = idPrefix + "-" + String.format("%03d", nextNumber);
        nextNumber++;
        return nextId;
    }

    public void addSale(Sale sale) {
        if (sale == null) {
            throw new IllegalArgumentException("Sale cannot be null.");
        }
        sales.add(sale);
    }

    public Sale findById(String id) {
        for (Sale sale : sales) {
            if (sale.getId().equalsIgnoreCase(id)) {
                return sale;
            }
        }
        return null;
    }

    public boolean removeSale(String id) {
        Sale sale = findById(id);
        if (sale == null) {
            return false;
        }
        return sales.remove(sale);
    }

    public double getTotalCharges() {
        double total = 0;
        for (Sale sale : sales) {
            total += sale.calculateCharge();
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0;
        for (Sale sale : sales) {
            total += sale.getDiscountAmount();
        }
        return total;
    }

    public List<Sale> getSortedSales() {
        List<Sale> sorted = new ArrayList<>(sales);
        sorted.sort(Comparator.comparing(Sale::getRecordType).thenComparing(Sale::getCustomerName));
        return sorted;
    }

    public void printSortedReport() {
        List<Sale> sorted = getSortedSales();
        if (sorted.isEmpty()) {
            System.out.println("No sales recorded yet.");
            return;
        }

        for (Sale sale : sorted) {
            System.out.println(sale.getId() + " | " + sale.getRecordType() + " | " + sale.getCustomerName() +
                    " | Quantity: " + sale.getQuantity() +
                    " | Charge: " + String.format("UGX %.2f", sale.calculateCharge()));
        }
    }

    public boolean isEmpty() {
        return sales.isEmpty();
    }
}
