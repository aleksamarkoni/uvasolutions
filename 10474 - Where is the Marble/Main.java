import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Prvi problem koji zahteva nesto sto do sada nisam znao, algoritam je baziran na 
// count sortu, dobar isecak ima na wikipedi a ispod strane ne wikiju ima i link prema strani
// na kojoj je animacija kako zapravo radi count sort
// od sada ubacujem ove komentare kako bih sebi olaksao zivot
// 1.april.2013 00:25
public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		int casenum = 1;
		while (in.hasNext()) {
			int m = in.nextInt(); 
			int n = in.nextInt();
			if (m==0) break;
			int marbles[] = new int[10001];
			int countsort[] = new int[10001];
			for (int i=0; i<m ; i++)
				marbles[in.nextInt()-1]++;
			countsort[0]=marbles[0];
			for (int i=1; i<10001; i++)
				countsort[i]=countsort[i-1]+marbles[i];
			System.out.println("CASE# " + (casenum++) + ":");
			for (int i=0; i<n; i++) {
				int guess = in.nextInt();
				if (marbles[guess-1]!=0) 
					System.out.println(guess + " found at " + (countsort[guess-1]-marbles[guess-1]+1));
				else System.out.println(guess + " not found");
				}
		}
	}
}