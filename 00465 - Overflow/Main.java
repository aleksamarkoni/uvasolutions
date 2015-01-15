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
			String line = in.nextLine();
			System.out.println(line);
			if (line.indexOf('*')!=-1) iop = line.indexOf('*');
			else iop = line.indexOf('+');
			BigInteger pbi = new BigInteger(line.substring(0, iop).trim());
			char operation = line.charAt(iop);
			BigInteger dbi = new BigInteger(line.substring(iop+1).trim());
			if (pbi.bitLength()>31) {
				System.out.println("first number too big");
				}
			if (dbi.bitLength()>31) { 
				System.out.println("second number too big");
			}
			if (operation == '+' && pbi.add(dbi).bitLength()>31)
				System.out.println("result too big");
			if (operation == '*' && pbi.multiply(dbi).bitLength()>31)
				System.out.println("result too big");
			}
	}
}