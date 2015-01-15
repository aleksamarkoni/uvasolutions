/* Ideja algoritma, je da su reci iste ako su frekvencije pojave odredjenih slova u njima iste
 * pa pravimo ogromnu matricu u kojoj za svaku rec cuvamo frekvenciju pojave njenih slova i to koristimo
 * kasnije za poredjenje, i uz pomoc toga izbacujemo reci koje se ponavljaju
 * */
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		String reci[] = new String[1000];
		for (int i=0; i<1000; i++)
			reci[i]="";
		int frc[][] = new int[1000][26];
		int count = 0;
		while (in.hasNext()) {
			String rec = in.next();
			rec = rec.trim();
			if (rec.equals("#")) break;
			reci[count] = rec;
			for (int i = 0; i < rec.length(); i++) {
				frc[count][Character.toLowerCase(rec.charAt(i))-'a']++;
			}
			count++;
		}
		int newcount=count;
		for (int i = 0; i < count; i++) {
			boolean uklonii=false;
			for (int j = i+1; j < count; j++) {
				if (!reci[i].equals("") && !reci[j].equals("") && reci[i].length() == reci[j].length()) {
					boolean iste = true;
					for (int k=0; k<26; k++)
						if (frc[i][k]!=frc[j][k]) { iste=false; break;}
					if (iste) {reci[j]=""; uklonii=true;newcount--;};
				}
			}
			if (uklonii) {
				reci[i]="";
				newcount--;
			}
		}
		Arrays.sort(reci,0,count);
		for (int i=count-newcount; i<count; i++) 
			if (!reci[i].equals(""))
				System.out.println(reci[i]);
	}
}