import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTestCases = in.nextInt();
        for (int i = 0; i < numberOfTestCases; i++) {
            in.nextLine();
            int numberOfGaps = in.nextInt();
            boolean weCanCloseTheGap = true;
            if (numberOfGaps < 0)
                weCanCloseTheGap = false;
            else {
                int oldDiff = -300;
                for (int j = 0; j < numberOfGaps; j++) {
                    int southMostGap = in.nextInt();
                    int northMostGap = in.nextInt();
                    int diff = Math.abs(northMostGap - southMostGap);
                    if (j != 0) {
                        if (diff != oldDiff) {
                            weCanCloseTheGap = false;
                        }
                    }
                    oldDiff = diff;
                }
            }
            if (weCanCloseTheGap)
                System.out.println("yes");
            else
                System.out.println("no");
            if (i != (numberOfTestCases-1))
                System.out.println();
        }
    }
}
