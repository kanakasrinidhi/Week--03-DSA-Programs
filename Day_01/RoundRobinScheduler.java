import java.util.*;

class Process {
    int id, burst, priority, remaining;
    Process next;

    Process(int id, int burst, int priority) {
        this.id = id;
        this.burst = burst;
        this.remaining = burst;
        this.priority = priority;
    }
}

public class RoundRobinScheduler {
    Process head = null;

    void addProcess(int id, int burst, int priority) {
        Process newProcess = new Process(id, burst, priority);
        if (head == null) {
            head = newProcess;
            newProcess.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    void removeProcess(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = null;
            return;
        }

        Process prev = null, curr = head;
        do {
            if (curr.id == id) {
                if (curr == head) {
                    Process temp = head;
                    while (temp.next != head) temp = temp.next;
                    head = head.next;
                    temp.next = head;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    void simulate(int quantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        Map<Integer, Integer> waiting = new HashMap<>();
        Map<Integer, Integer> turnaround = new HashMap<>();
        Map<Integer, Integer> finish = new HashMap<>();

        int time = 0;
        Process current = head;

        while (true) {
            boolean done = true;
            do {
                if (current.remaining > 0) {
                    done = false;
                    int execTime = Math.min(current.remaining, quantum);
                    time += execTime;
                    current.remaining -= execTime;
                    if (current.remaining == 0) {
                        finish.put(current.id, time);
                        removeProcess(current.id);
                    }
                }
                current = current.next;
                display();
            } while (current != head);

            if (done) break;
        }

        for (int pid : finish.keySet()) {
            turnaround.put(pid, finish.get(pid));
            waiting.put(pid, turnaround.get(pid) - getBurst(pid));
        }

        double totalWT = 0, totalTAT = 0;
        for (int pid : finish.keySet()) {
            totalWT += waiting.get(pid);
            totalTAT += turnaround.get(pid);
        }

        int n = finish.size();
        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Turnaround Time: " + (totalTAT / n));
    }

    int getBurst(int id) {
        Process temp = head;
        if (temp == null) return 0;
        do {
            if (temp.id == id) return temp.burst;
            temp = temp.next;
        } while (temp != head);
        return 0;
    }

    void display() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Current Process Queue:");
        Process temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Remaining: " + temp.remaining + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID, Burst Time, and Priority: ");
            int id = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            scheduler.addProcess(id, burst, priority);
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        scheduler.simulate(quantum);
    }
}
