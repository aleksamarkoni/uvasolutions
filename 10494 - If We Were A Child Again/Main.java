import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		int iop;
		while (in.hasNext()) {
			BigInteger pbi = in.nextBigInteger();
			char operation = in.next().charAt(0);
			BigInteger dbi = in.nextBigInteger();
			if (operation == '/')
				System.out.println(pbi.divide(dbi));
			if (operation == '%')
				System.out.println(pbi.remainder(dbi));
			}
	}
}