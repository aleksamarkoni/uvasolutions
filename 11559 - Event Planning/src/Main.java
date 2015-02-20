import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            long minimumCostFound = Long.MAX_VALUE;
            long numberOfParticipants = in.nextInt();
            long budget = in.nextInt();
            long numberOfHotels = in.nextInt();
            long numberOfWeekendsToChose = in.nextInt();
            for (int hotel = 0; hotel < numberOfHotels; hotel++) {
                long pricePerPersonForWeekend = in.nextInt();
                for (int weekend = 0; weekend < numberOfWeekendsToChose; weekend++) {
                    long availableBeds = in.nextInt();
                    if (availableBeds >= numberOfParticipants && (pricePerPersonForWeekend * numberOfParticipants) <= budget) {
                        if (pricePerPersonForWeekend * numberOfParticipants < minimumCostFound)
                            minimumCostFound = pricePerPersonForWeekend * numberOfParticipants;
                    }
                }
            }
            if (minimumCostFound > budget)
                System.out.println("stay home");
            else
                System.out.println(minimumCostFound);
        }
    }
}
