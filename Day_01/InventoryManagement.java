import java.util.Scanner;

class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

public class InventoryManagement {
    Item head = null;

    void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    void addAtPosition(int pos, String name, int id, int quantity, double price) {
        if (pos == 0) {
            addAtBeginning(name, id, quantity, price);
            return;
        }

        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        for (int i = 0; i < pos - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }

        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    void updateQuantity(int id, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("Found: " + temp.name + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void searchByName(String name) {
        Item temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("Found: ID=" + temp.id + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value = $" + total);
    }

    Item mergeSort(Item node, String key, boolean ascending) {
        if (node == null || node.next == null)
            return node;

        Item middle = getMiddle(node);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(node, key, ascending);
        Item right = mergeSort(nextOfMiddle, key, ascending);

        return sortedMerge(left, right, key, ascending);
    }

    Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    Item sortedMerge(Item a, Item b, String key, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        boolean condition;
        if (key.equals("name")) {
            condition = ascending ? a.name.compareToIgnoreCase(b.name) <= 0 : a.name.compareToIgnoreCase(b.name) > 0;
        } else {
            condition = ascending ? a.price <= b.price : a.price > b.price;
        }

        if (condition) {
            a.next = sortedMerge(a.next, b, key, ascending);
            return a;
        } else {
            b.next = sortedMerge(a, b.next, key, ascending);
            return b;
        }
    }

    void sortInventory(String key, boolean ascending) {
        head = mergeSort(head, key, ascending);
        System.out.println("Inventory sorted by " + key + " in " + (ascending ? "ascending" : "descending") + " order.");
    }

    void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println("ID=" + temp.id + ", Name=" + temp.name + ", Qty=" + temp.quantity + ", Price=$" + temp.price);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagement im = new InventoryManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display Inventory");
            System.out.println("9. Calculate Total Value");
            System.out.println("10. Sort Inventory");
            System.out.println("11. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            int id, qty, pos;
            double price;
            String name;

            switch (choice) {
                case 1:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    name = sc.nextLine();
                    id = sc.nextInt();
                    qty = sc.nextInt();
                    price = sc.nextDouble();
                    im.addAtBeginning(name, id, qty, price);
                    break;
                case 2:
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    name = sc.nextLine();
                    id = sc.nextInt();
                    qty = sc.nextInt();
                    price = sc.nextDouble();
                    im.addAtEnd(name, id, qty, price);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name, ID, Quantity, Price: ");
                    name = sc.nextLine();
                    id = sc.nextInt();
                    qty = sc.nextInt();
                    price = sc.nextDouble();
                    im.addAtPosition(pos, name, id, qty, price);
                    break;
                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    id = sc.nextInt();
                    im.removeById(id);
                    break;
                case 5:
                    System.out.print("Enter Item ID and new quantity: ");
                    id = sc.nextInt();
                    qty = sc.nextInt();
                    im.updateQuantity(id, qty);
                    break;
                case 6:
                    System.out.print("Enter Item ID to search: ");
                    id = sc.nextInt();
                    im.searchById(id);
                    break;
                case 7:
                    System.out.print("Enter Item Name to search: ");
                    name = sc.nextLine();
                    im.searchByName(name);
                    break;
                case 8:
                    im.display();
                    break;
                case 9:
                    im.calculateTotalValue();
                    break;
                case 10:
                    System.out.print("Sort by (name/price): ");
                    String key = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = sc.nextBoolean();
                    im.sortInventory(key, asc);
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
