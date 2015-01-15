/* Ne znam sta sam ovo radio, sklonicu na stranu pa cemo videti :)
 * */
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			
			int n = in.nextInt();
			int max = 0;
			String filenames[] = new String[n];
			for (int i = 0; i < n; i++) {
				filenames[i] = in.next().trim();
				if (filenames[i].length()>max)
					max = filenames[i].length();
			}
			Arrays.sort(filenames);
			int col = 62 / (max+2);
			int row = n/col;
			if ((n % col) > 0) row++;
			for (int i = 0; i < 60; i++)
				System.out.print('-');
			System.out.println();
			String matrica[][] = new String[row][col];
			for (int i = 0; i < col; i++)
				for (int j = 0; j < row; j++)
					if ((i*row+j)<filenames.length)
						matrica[j][i] 
								= filenames[i*row+j];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (matrica[i][j]!=null) {
					System.out.print(matrica[i][j]);
					for (int k = 0; k < max-matrica[i][j].length(); k++)
						System.out.print(" ");
					}
					if (j!=(col-1))
						System.out.print("  ");
				}
				System.out.println();
			}
		}
	}
}