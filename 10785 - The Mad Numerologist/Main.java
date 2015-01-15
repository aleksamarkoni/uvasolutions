/*
 * Prvo napravimo odgovor u odnosu na najmanje zbirove samoglasnika i suglasnika kao sto pise u zadatku
 * Kada se zada duzina reci, potrebno je unutar te reci sortirati sve samoglasnike i sve suglasnike
 * u leksikografski poredak kako bi se pored najmanjeg zbira istih dobilo i leksikografski
 * ispravno stanje kao sto se i trazi u zadatku
 */
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		char sve[] =new char[210];
		char vovels[] = {'A', 'U', 'E', 'O', 'I'};
		char consonant[] = {'J', 'S', 'B', 'K', 'T', 'C', 'L', 'D', 'M', 'V',
				'N', 'W', 'F', 'X', 'G', 'P', 'Y', 'H', 'Q', 'Z', 'R'};
		for (int i=0; i<5; i++) 
			for (int j=0; j<21; j++)
				sve[i*42+(j*2)] = vovels[i];
		for (int i=0; i<21; i++)
			for (int j=0; j<5; j++)
				sve[i*10+(j*2+1)] = consonant[i];
		int n = in.nextInt();
		int test;
		for (int i=0; i<n; i++) {
			test = in.nextInt();
			char odg[] = new char[test];
			for (int j=0; j<test; j++)
				odg[j]=sve[j];
			for (int j = 0; j < test; j+=2)
				for (int k = j+2; k < test; k+=2)
					if (odg[j]>odg[k]) {
						char swap = odg[j];
						odg[j] = odg[k];
						odg[k] = swap;
					}
			for (int j = 1; j < test; j+=2)
				for (int k = j+2; k < test; k+=2)
					if (odg[j]>odg[k]) {
						char swap = odg[j];
						odg[j] = odg[k];
						odg[k] = swap;
					}
			
			System.out.println("Case " + (i+1) + ": " + new String(odg));
		
		}
	}
}