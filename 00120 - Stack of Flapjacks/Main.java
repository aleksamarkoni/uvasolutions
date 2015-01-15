/* ideja algoritma pronadjemo najvecu palacinku i jednim okretom je dovedemo na vrh stacka
 * a zatim drugim okretom je dovedemo na mesto na kome bi inace trebalo da se nalazi,
 * sto znaci ideja je u sortiranju, nesto slicno kao insertetion sort
 */
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	private static void flip (int niz[], int indeks) {
		for (int i = 0; i <=indeks/2; i++) {
			int swap = niz[i];
			niz[i]=niz[indeks-i];
			niz[indeks-i]=swap;
		}
	}
	private static void stampa(int niz[]) {
		for (int i = 0; i < niz.length; i++)
			System.out.print(niz[i] + " ");
		System.out.println();
	}
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String stack = in.nextLine();
			System.out.println(stack);
			String split[] = stack.split(" ");
			int niz[] = new int[split.length];
			for (int i = 0; i < split.length; i++)
				niz[i] = Integer.parseInt(split[i].trim());
			for (int i = niz.length-1; i > 0; i--) {
				int indeks = i;
				int max = 0;
				for (int j=i; j>=0; j--)
					if (niz[j] > max) {max = niz[j]; indeks=j;}
				if (indeks!=i) {
					if (indeks!=0) {
						System.out.print((niz.length-indeks) + " ");
						flip(niz, indeks);
					}
					System.out.print((niz.length-i) + " ");
					flip(niz, i);
				}
			}
			System.out.println("0");
		}
	}
}