import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			BigDecimal broj = in.nextBigDecimal();
			int stepen = in.nextInt();
			String b = broj.pow(stepen).stripTrailingZeros().toPlainString();
			if (b.charAt(0)=='0')
				b = b.substring(1);
			System.out.println(b);
		}
	}
}