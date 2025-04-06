import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

public class TicketReservationSystem {
    Ticket head = null;

    void addTicket(int id, String cname, String mname, String seat, String time) {
        Ticket newTicket = new Ticket(id, cname, mname, seat, time);
        if (head == null) {
            head = newTicket;
            newTicket.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    void removeTicket(int id) {
        if (head == null) return;

        Ticket temp = head, prev = null;

        do {
            if (temp.ticketId == id) {
                if (temp == head && temp.next == head) {
                    head = null;
                } else if (temp == head) {
                    Ticket last = head;
                    while (last.next != head) {
                        last = last.next;
                    }
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = temp.next;
                }
                System.out.println("Ticket with ID " + id + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Ticket ID not found.");
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        do {
            System.out.println("ID: " + temp.ticketId + ", Name: " + temp.customerName + ", Movie: " + temp.movieName +
                    ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByNameOrMovie(String key) {
        if (head == null) return;

        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(key) || temp.movieName.equalsIgnoreCase(key)) {
                System.out.println("ID: " + temp.ticketId + ", Name: " + temp.customerName + ", Movie: " + temp.movieName +
                        ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No ticket found for: " + key);
    }

    void totalTickets() {
        if (head == null) {
            System.out.println("Total Tickets: 0");
            return;
        }

        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total Tickets: " + count);
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        Scanner sc = new Scanner(System.in);

        system.addTicket(1, "Alice", "Avengers", "A1", "10:00 AM");
        system.addTicket(2, "Bob", "Avengers", "A2", "10:00 AM");
        system.addTicket(3, "Charlie", "Inception", "B1", "12:00 PM");

        system.displayTickets();
        system.searchByNameOrMovie("Bob");
        system.totalTickets();

        system.removeTicket(2);
        system.displayTickets();
        system.totalTickets();
    }
}
