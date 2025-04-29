import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

public class TaskScheduler {
    Task head = null;
    Task tail = null;
    Task current = null;

    void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos == 0) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 0; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }

    void removeById(int id) {
        if (head == null) return;

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.id == id) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                if (temp == current) {
                    current = temp.next;
                }
                System.out.println("Task removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found.");
    }

    void viewCurrentTaskAndMoveNext() {
        if (current == null) {
            current = head;
        }

        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("Current Task: ID=" + current.id + ", Name=" + current.name + ", Priority=" + current.priority + ", Due Date=" + current.dueDate);
        current = current.next;
    }

    void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        Task temp = head;
        do {
            System.out.println("ID=" + temp.id + ", Name=" + temp.name + ", Priority=" + temp.priority + ", Due Date=" + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID=" + temp.id + ", Name=" + temp.name + ", Priority=" + temp.priority + ", Due Date=" + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task with that priority found.");
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task and Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            int id, priority, pos;
            String name, dueDate;

            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    name = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtBeginning(id, name, priority, dueDate);
                    break;
                case 2:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    name = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtEnd(id, name, priority, dueDate);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    name = sc.nextLine();
                    priority = sc.nextInt();
                    sc.nextLine();
                    dueDate = sc.nextLine();
                    scheduler.addAtPosition(pos, id, name, priority, dueDate);
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    id = sc.nextInt();
                    scheduler.removeById(id);
                    break;
                case 5:
                    scheduler.viewCurrentTaskAndMoveNext();
                    break;
                case 6:
                    scheduler.displayTasks();
                    break;
                case 7:
                    System.out.print("Enter Priority to search: ");
                    priority = sc.nextInt();
                    scheduler.searchByPriority(priority);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
