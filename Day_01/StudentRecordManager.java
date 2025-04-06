import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    Student next;

    Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class StudentRecordManager {
    Student head = null;

    void addAtBeginning(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    void addAtEnd(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    void addAtPosition(int pos, int rollNo, String name, int age, String grade) {
        if (pos == 0) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNo, name, age, grade);
        Student temp = head;
        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    void deleteByRollNo(int rollNo) {
        if (head == null) return;
        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student not found.");
            return;
        }
        temp.next = temp.next.next;
    }

    void searchByRollNo(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found: " + temp.rollNo + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    void updateGrade(int rollNo, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Roll No");
            System.out.println("5. Search by Roll No");
            System.out.println("6. Update Grade");
            System.out.println("7. Display All");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            int rollNo, age, pos;
            String name, grade;

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    manager.addAtBeginning(rollNo, name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    manager.addAtEnd(rollNo, name, age, grade);
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    manager.addAtPosition(pos, rollNo, name, age, grade);
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    rollNo = sc.nextInt();
                    manager.deleteByRollNo(rollNo);
                    break;
                case 5:
                    System.out.print("Enter Roll No to search: ");
                    rollNo = sc.nextInt();
                    manager.searchByRollNo(rollNo);
                    break;
                case 6:
                    System.out.print("Enter Roll No and new Grade: ");
                    rollNo = sc.nextInt();
                    grade = sc.next();
                    manager.updateGrade(rollNo, grade);
                    break;
                case 7:
                    manager.displayAll();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
