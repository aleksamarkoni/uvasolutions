import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
        int numberOfTestCases = Integer.parseInt(in.nextLine().trim());
        for (int testCase = 0; testCase < numberOfTestCases; testCase++) {
            int numberOfFarmers = Integer.parseInt(in.nextLine().trim());
            int sumOfAllThePremiums = 0;
            for (int farmer = 0; farmer < numberOfFarmers; farmer++) {
                String lineOfInput = in.nextLine();
                StringTokenizer stringTokenizer = new StringTokenizer(lineOfInput);
                int farmArea = Integer.parseInt(stringTokenizer.nextToken());
                int numberOfAnimals = Integer.parseInt(stringTokenizer.nextToken());
                int environmentFriendliness = Integer.parseInt(stringTokenizer.nextToken());
                sumOfAllThePremiums += farmArea * environmentFriendliness;
            }
            System.out.println(sumOfAllThePremiums);
        }
        in.close();
    }
}
