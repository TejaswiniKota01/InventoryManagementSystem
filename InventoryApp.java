import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InventoryApp {

    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    public static boolean isValidPrice(double price) {
        return price >= 0.0;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            InventoryDAO dao = new InventoryDAO();
            while (true) {
                System.out.println("\nInventory Management System");
                System.out.println("1. Add Item");
                System.out.println("2. View All Items");
                System.out.println("3. Update Item");
                System.out.println("4. Delete Item");
                System.out.println("5. Exit");
                System.out.println("6. Search Item by Name");
                System.out.println("7. Show Total Inventory Value");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: // Add Item
                        System.out.print("Item Name: ");
                        String name = sc.nextLine();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();

                        if (!isValidQuantity(qty)) {
                            System.out.println("Invalid quantity! Must be zero or positive.");
                            break;
                        }
                        if (!isValidPrice(price)) {
                            System.out.println("Invalid price! Must be zero or positive.");
                            break;
                        }

                        dao.addItem(new InventoryItem(0, name, qty, price));
                        System.out.println("Item added.");
                        break;

                    case 2: // View All Items
                        List<InventoryItem> items = dao.getAllItems();
                        if (items.isEmpty()) {
                            System.out.println("No items found.");
                        } else {
                            System.out.println("ID\tName\tQuantity\tPrice");
                            for (InventoryItem item : items) {
                                System.out.printf("%d\t%s\t%d\t\t%.2f\n",
                                    item.getId(), item.getName(), item.getQuantity(), item.getPrice());
                            }
                        }
                        break;

                    case 3: // Update Item
                        System.out.print("Enter Item ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Quantity: ");
                        int newQty = sc.nextInt();
                        System.out.print("New Price: ");
                        double newPrice = sc.nextDouble();

                        if (!isValidQuantity(newQty)) {
                            System.out.println("Invalid quantity! Must be zero or positive.");
                            break;
                        }
                        if (!isValidPrice(newPrice)) {
                            System.out.println("Invalid price! Must be zero or positive.");
                            break;
                        }

                        dao.updateItem(new InventoryItem(updateId, newName, newQty, newPrice));
                        System.out.println("Item updated.");
                        break;

                    case 4: // Delete Item
                        System.out.print("Enter Item ID to delete: ");
                        int deleteId = sc.nextInt();
                        dao.deleteItem(deleteId);
                        System.out.println("Item deleted.");
                        break;

                    case 5: // Exit with confirmation
                        System.out.print("Are you sure you want to exit? (y/n): ");
                        String confirm = sc.next();
                        if (confirm.equalsIgnoreCase("y")) {
                            System.out.println("Exiting...");
                            return;
                        }
                        break;

                    case 6: // Search Item by Name
                        System.out.print("Enter name or part of name to search: ");
                        String searchTerm = sc.next();
                        List<InventoryItem> searchResults = dao.searchItemsByName(searchTerm);
                        if (searchResults.isEmpty()) {
                            System.out.println("No items found.");
                        } else {
                            System.out.println("ID\tName\tQuantity\tPrice");
                            for (InventoryItem item : searchResults) {
                                System.out.printf("%d\t%s\t%d\t\t%.2f\n",
                                    item.getId(), item.getName(), item.getQuantity(), item.getPrice());
                            }
                        }
                        break;

                    case 7: // Show total inventory value
                        double totalValue = dao.getTotalInventoryValue();
                        System.out.printf("Total Inventory Value: %.2f\n", totalValue);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
