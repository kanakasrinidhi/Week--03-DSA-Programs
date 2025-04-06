import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

public class MovieManagementSystem {
    Movie head = null, tail = null;

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos == 0) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 0; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        newMovie.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = newMovie;
        } else {
            tail = newMovie;
        }
        newMovie.prev = temp;
        temp.next = newMovie;
    }

    void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head) {
                    head = temp.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (temp == tail) {
                    tail = temp.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Movie removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " - " + temp.director + " - " + temp.year + " - " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found by that director.");
    }

    void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + " - " + temp.director + " - " + temp.year + " - " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found with that rating.");
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " - " + temp.director + " - " + temp.year + " - " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " - " + temp.director + " - " + temp.year + " - " + temp.rating);
            temp = temp.prev;
        }
    }

    public static void main(String[] args) {
        MovieManagementSystem mms = new MovieManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Display All Movies (Forward)");
            System.out.println("9. Display All Movies (Reverse)");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            String title, director;
            int year, pos;
            double rating;

            switch (choice) {
                case 1:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtBeginning(title, director, year, rating);
                    break;
                case 2:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtEnd(title, director, year, rating);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    title = sc.nextLine();
                    director = sc.nextLine();
                    year = sc.nextInt();
                    rating = sc.nextDouble();
                    mms.addAtPosition(pos, title, director, year, rating);
                    break;
                case 4:
                    System.out.print("Enter Title to remove: ");
                    title = sc.nextLine();
                    mms.removeByTitle(title);
                    break;
                case 5:
                    System.out.print("Enter Director to search: ");
                    director = sc.nextLine();
                    mms.searchByDirector(director);
                    break;
                case 6:
                    System.out.print("Enter Rating to search: ");
                    rating = sc.nextDouble();
                    mms.searchByRating(rating);
                    break;
                case 7:
                    System.out.print("Enter Title and New Rating: ");
                    title = sc.nextLine();
                    rating = sc.nextDouble();
                    mms.updateRating(title, rating);
                    break;
                case 8:
                    mms.displayForward();
                    break;
                case 9:
                    mms.displayReverse();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
