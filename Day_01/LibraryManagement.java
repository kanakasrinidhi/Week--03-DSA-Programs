import java.util.Scanner;

class Book {
    String title, author, genre;
    int id;
    boolean available;
    Book next, prev;

    Book(String title, String author, String genre, int id, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.available = available;
    }
}

public class LibraryManagement {
    Book head = null, tail = null;

    void addAtBeginning(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    void addAtEnd(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    void addAtPosition(int pos, String title, String author, String genre, int id, boolean available) {
        if (pos == 0) {
            addAtBeginning(title, author, genre, id, available);
            return;
        }
        Book newBook = new Book(title, author, genre, id, available);
        Book temp = head;
        for (int i = 0; i < pos - 1 && temp != null; i++) temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, id, available);
            return;
        }
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }

    void removeById(int id) {
        Book temp = head;
        while (temp != null && temp.id != id) temp = temp.next;
        if (temp == null) return;
        if (temp == head) head = head.next;
        if (temp == tail) tail = tail.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    void searchByTitle(String title) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.id + ", Available: " + temp.available);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    void searchByAuthor(String author) {
        Book temp = head;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.id + ", Available: " + temp.available);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    void updateAvailability(int id, boolean available) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.available = available;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + temp.available);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + temp.available);
            temp = temp.prev;
        }
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total books: " + count);
    }

    public static void main(String[] args) {
        LibraryManagement lib = new LibraryManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add at Beginning\n2.Add at End\n3.Add at Position\n4.Remove by ID\n5.Search by Title\n6.Search by Author\n7.Update Availability\n8.Display Forward\n9.Display Reverse\n10.Count Books\n11.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            int id, pos;
            String title, author, genre;
            boolean available;

            switch (ch) {
                case 1:
                    System.out.print("Enter Title, Author, Genre, ID, Available (true/false): ");
                    title = sc.nextLine();
                    author = sc.nextLine();
                    genre = sc.nextLine();
                    id = sc.nextInt();
                    available = sc.nextBoolean();
                    lib.addAtBeginning(title, author, genre, id, available);
                    break;
                case 2:
                    System.out.print("Enter Title, Author, Genre, ID, Available (true/false): ");
                    title = sc.nextLine();
                    author = sc.nextLine();
                    genre = sc.nextLine();
                    id = sc.nextInt();
                    available = sc.nextBoolean();
                    lib.addAtEnd(title, author, genre, id, available);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title, Author, Genre, ID, Available (true/false): ");
                    title = sc.nextLine();
                    author = sc.nextLine();
                    genre = sc.nextLine();
                    id = sc.nextInt();
                    available = sc.nextBoolean();
                    lib.addAtPosition(pos, title, author, genre, id, available);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    lib.removeById(id);
                    break;
                case 5:
                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();
                    lib.searchByTitle(title);
                    break;
                case 6:
                    System.out.print("Enter Author Name: ");
                    author = sc.nextLine();
                    lib.searchByAuthor(author);
                    break;
                case 7:
                    System.out.print("Enter Book ID and new Availability (true/false): ");
                    id = sc.nextInt();
                    available = sc.nextBoolean();
                    lib.updateAvailability(id, available);
                    break;
                case 8:
                    lib.displayForward();
                    break;
                case 9:
                    lib.displayReverse();
                    break;
                case 10:
                    lib.countBooks();
                    break;
                case 11:
                    return;
            }
        }
    }
}
