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
		/*
		 * glupa java, ovo je stvarno bruka sporo, ne verujem morao sam da
		 * bufferujem ulaz, kako bi radilo brze, a i izlaz koji sam strpao u
		 * string builder pa sam onda to stampao Ovde je bila i greska prilikom
		 * stampanja, jer kada sam stampao q(x) posle svakog koeficijenta sam
		 * stavljao " ", a to nije u redu, pa sam morao da promenim raspored.
		 * Logika je ok, klasicno deljenje polinoma sa (x-k), moze se videti
		 * formula iz zadatka pa se vidi da je ok.
		 */
		int p[] = new int[10001];
		String input;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			while ((input = in.readLine()) != null) {
				StringBuilder rezult = new StringBuilder();
				/*
				 * if (prvalinija == true) { prvalinija = false; } else
				 * rezult.append("\n");
				 */
				int k = Integer.parseInt(input);
				Scanner line = new Scanner(in.readLine());
				int n = 0;
				while (line.hasNextInt()) {
					p[n++] = line.nextInt();
				}

				rezult.append("q(x):");
				if (n > 1)
					rezult.append(" " + p[0]);
				for (int i = 1; i < n - 1; i++) {
					p[i] = p[i] + p[i - 1] * k;
					rezult.append(" " + p[i]);

				}
				if (n > 1)
					p[n - 1] = p[n - 1] + p[n - 2] * k;
				rezult.append("\n");
				rezult.append("r = " + p[n - 1]);
				rezult.append("\n");
				System.out.println(rezult.toString());
				line.close();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
