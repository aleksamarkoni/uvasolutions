import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine().trim());
        for (int k = 0; k < T; k++) {
            String testCase = in.nextLine().trim().toLowerCase();
            int score = 0;
            int j = 0;
            for (int i = 0; i < testCase.length(); i++) {
                if (testCase.charAt(i) == 'o') {
                    score += ++j;
                } else {
                    j = 0;
                }
            }
            System.out.println(score);
        }
    }
}