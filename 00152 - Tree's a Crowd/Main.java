import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		int x[] = new int[5001];
		int y[] = new int[5001];
		int z[] = new int[5001];
		int dist[] = new int[5001];
		int brojtacaka = 0;
		int x1, y1, z1;
		while (in.hasNext()) {
			x1 = in.nextInt();
			y1 = in.nextInt();
			z1 = in.nextInt();
			if (x1 == 0 && y1 == 0 && z1 == 0) break;
			x[brojtacaka]=x1;
			y[brojtacaka]=y1;
			z[brojtacaka]=z1;
			brojtacaka++;
		}
		for (int i = 0; i < brojtacaka; i++) {
			int max = 255*255*255;
			for (int j = 0; j < brojtacaka; j++) {
				int d = (x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j])+(z[i]-z[j])*(z[i]-z[j]);
				if (i!=j && d<max) max=d; 
			}
			dist[i]=max;
		}
		int resenje[] = new int[10];
		for (int i = 0; i < brojtacaka; i++) {
			if (dist[i]<100)
				resenje[(int)Math.floor(Math.sqrt(dist[i]))]++; 
		}
		for (int i=0; i<10; i++)
			System.out.printf("%4d",resenje[i]);
		System.out.println();
	}
}