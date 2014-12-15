// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

	int calculateScore(String name) {
		int res = 0;
		for (int i = 0; i < name.length(); i++) {
			if (Character.isLetter(name.charAt(i))) {
				 res += Character.toLowerCase(name.charAt(i)) - 'a' + 1;
			}
		}
		//System.out.println(res);
		while (res >= 10) {
			int sum = 0;
			while (res != 0) {
				sum += res % 10;
				res /=  10;
			}
			res = sum;
		}
		return res;
	}
	
    void Begin()
    {
        String input;
        StringTokenizer idata;
        String word;
		int n, m, c;
		int caseCount = 1;
		
        while ((input = Main.ReadLn (1000009)) != null)
        {
			String namea = input;
			input = Main.ReadLn(50);
			String nameb = input;
			
			int scorea = calculateScore(namea);
			int scoreb = calculateScore(nameb);
			if (scorea > scoreb) {
				int pom = scorea;
				scorea = scoreb;
				scoreb = pom;
			}
			
			if (scorea == 0 && scoreb == 0) {
				System.out.println();
				continue;
			}
				
			if (scoreb != 0)
				System.out.printf("%.2f %%\n", (double)scorea/
			scoreb * 100);
			else 
				System.out.println("0.00 %");
		}
    }
}