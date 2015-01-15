import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {

	public static void main(String args[]) // entry point from OS
	{
		Main myWork = new Main(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {
		
		/* mora da se razradi formula, znaci prvo izrazis a1, 
		 * zatim izrazis a2, a posto se u toj formuli koristi a1, ubacis iz prethodnog
		 * zatim izrazis a3, a u formuli zamenih a2 iz prethodnog koraka
		 * i posle par koraka, vidis koje je pravilo
		 * generalno otprilike izgleda formula ovako
		 * 
		 * an = (a0 + n * a(n+1) - (ovde ima oznaka matematicka suma i = 1 do n)( 2 * i * c(i)) )
		 * i to je to, samo moramo ici unazad i racunati a(n), a(n-1) itd do a(1)
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
		int numberOfTestCases = Integer.parseInt(in.readLine());
		for (int i = 0; i < numberOfTestCases; i++) {
			in.readLine();
			int n = Integer.parseInt(in.readLine());
			double an[] = new double[n+2];
			double c[] = new double[n+2];
			an[0] = Double.parseDouble(in.readLine());
			an[n+1] = Double.parseDouble(in.readLine());
			
			for (int j = 1; j<=n; j++) {
				c[j] = c[j-1] - 2 * j * Double.parseDouble(in.readLine()); 
			}
			
			/*for (int j = 1; j<=n; j++) {
				System.out.print(c[j] + " ");
			}
			System.out.println();
			*/
			for (int j = n; j>=1; j--) {
				an[j] = (an[0] + j*an[j+1] + c[j])/(j+1);
			}
			
			/*
			for (int j = 0; j<n+2; j++) {
				System.out.print(an[j] + " ");
			}
			System.out.println();
			*/
			
			System.out.printf("%.2f\n",an[1]);
			if (i!=numberOfTestCases-1)
				System.out.println();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
