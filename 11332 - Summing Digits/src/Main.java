import java.util.Scanner;

public class Main {

    static int f(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        if (sum < 10)
            return sum;
        else return f(sum);
    }

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            System.out.println(f(n));
            n = in.nextInt();
        }
    }
}
