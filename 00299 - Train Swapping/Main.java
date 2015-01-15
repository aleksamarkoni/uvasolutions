import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i1 = 0; i1 < n; i1++) {
			int L = in.nextInt();
			int niz[]=new int[L];
			for (int j1=0; j1<L; j1++)
				niz[j1] = in.nextInt();
			int temp; //for swapping
		    int count = 0;
			for ( int i = 0 ; i < L - 1 ; i++)
		    {
		        for (int j = 0 ; j < L - 1 ; j++)
		        {
		            if ( niz[j] > niz[j + 1] )
		            {
		            	count++;
		                temp = niz[j];
		                niz[j]=niz[j + 1];
		                niz[j + 1] = temp;
		            }
		        }
		    }
			System.out.println("Optimal train swapping takes " + count + " swaps.");
		}
	}
}