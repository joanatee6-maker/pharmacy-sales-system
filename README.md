import java.util.Scanner;

public class PharmacySystem {
    private static final String FULL_NAME = "Atee Joan";
    private static final String REGISTRATION_NUMBER = "VU-BIT-2511-0805-EVE";
    private static final String ID_PREFIX = "AJ805";

    private final SaleManager saleManager;
    private final Scanner scanner;

    public PharmacySystem() {
        this.saleManager = new SaleManager(ID_PREFIX);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("====================================================");
        System.out.println("Pharmacy Sales Management System");
        System.out.println("Student Name: " + FULL_NAME);
        System.out.println("Registration Number: " + REGISTRATION_NUMBER);
        System.out.println("====================================================");

        boolean running = true;

        while (running) {
            showMainMenu();
            String choice = InputValidator.readNonBlank(scanner, "Choose an option: ").trim();

            switch (choice) {
                case "1":
                    addSale();
                    break;
                case "2":
                    listSales();
                    break;
                case "3":
                    findSale();
                    break;
                case "4":
                    removeSale();
                    break;
                case "5":
                    showSummary();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose from 1 to 6.");
            }
        }

        scanner.close();
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("MAIN MENU");
        System.out.println("1. Add a sale");
        System.out.println("2. List all sales");
        System.out.println("3. Find a sale by ID");
        System.out.println("4. Remove a sale");
        System.out.println("5. Show summary");
        System.out.println("6. Exit");
    }

    private void addSale() {
        System.out.println("\nAdd a sale");
        System.out.println("1. Prescription Sale");
        System.out.println("2. Counter Sale");

        String saleTypeChoice = InputValidator.readNonBlank(scanner, "Select type (1 or 2): ");

        try {
            String customerName = InputValidator.readNonBlank(scanner, "Customer name: ");
            int quantity = InputValidator.readPositiveInt(scanner, "Quantity: ");
            double unitPrice = InputValidator.readPositiveDouble(scanner, "Price per unit (UGX): ");
            boolean isDiscountEligible = InputValidator.readYesNo(scanner, "Customer is 60 or over? (Y/N): ");

            String id = saleManager.createNextId();
            Sale sale;

            if (saleTypeChoice.equals("1")) {
                sale = new PrescriptionSale(id, customerName, quantity, unitPrice, isDiscountEligible);
            } else if (saleTypeChoice.equals("2")) {
                sale = new CounterSale(id, customerName, quantity, unitPrice, isDiscountEligible);
            } else {
                System.out.println("Invalid sale type selected.");
                return;
            }

            saleManager.addSale(sale);
            System.out.println("Sale added successfully with ID: " + id);
        } catch (InvalidSaleException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listSales() {
        System.out.println("\nSorted sales report");
        if (saleManager.isEmpty()) {
            System.out.println("No sales have been recorded yet.");
            return;
        }
        saleManager.printSortedReport();
    }

    private void findSale() {
        System.out.println("\nFind a sale by ID");
        String id = InputValidator.readNonBlank(scanner, "Enter sale ID: ");
        Sale sale = saleManager.findById(id);

        if (sale == null) {
            System.out.println("No sale found with ID: " + id);
        } else {
            System.out.println("Found sale:");
            System.out.println(sale);
        }
    }

    private void removeSale() {
        System.out.println("\nRemove a sale");
        String id = InputValidator.readNonBlank(scanner, "Enter sale ID to remove: ");
        Sale sale = saleManager.findById(id);

        if (sale == null) {
            System.out.println("No sale found with ID: " + id);
            return;
        }

        boolean confirm = InputValidator.readYesNo(scanner, "Are you sure you want to remove this sale? (Y/N): ");
        if (confirm) {
            saleManager.removeSale(id);
            System.out.println("Sale with ID " + id + " removed successfully.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }

    private void showSummary() {
        System.out.println("\nSummary of all sales");
        if (saleManager.isEmpty()) {
            System.out.println("No sales recorded yet.");
            return;
        }

        System.out.println("Total charge: UGX " + String.format("%.2f", saleManager.getTotalCharges()));
        System.out.println("Total discount given: UGX " + String.format("%.2f", saleManager.getTotalDiscount()));
    }

    public static void main(String[] args) {
        PharmacySystem system = new PharmacySystem();
        system.start();
    }
}
