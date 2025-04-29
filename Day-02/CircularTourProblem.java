public class CircularTourProblem {
    static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3),
                new PetrolPump(4, 5)
        };

        int start = findStartingPoint(pumps);

        if (start == -1)
            System.out.println("No feasible starting point");
        else
            System.out.println("Start at petrol pump: " + start);
    }

    static int findStartingPoint(PetrolPump[] pumps) {
        int total = 0, curr = 0, start = 0;

        for (int i = 0; i < pumps.length; i++) {
            int gain = pumps[i].petrol - pumps[i].distance;
            total += gain;
            curr += gain;

            if (curr < 0) {
                curr = 0;
                start = i + 1;
            }
        }

        return total >= 0 ? start : -1;
    }
}
