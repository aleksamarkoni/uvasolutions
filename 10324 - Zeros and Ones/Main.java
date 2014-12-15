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

    void Begin()
    {
        String input;
        StringTokenizer idata;
        String word;
		int n, m, c;
		int caseCount = 1;
		
        while ((input = Main.ReadLn (1000009)) != null)
        {
			if (input == "")
				break;
			
			int data[] = new int[input.length()];
			int counter = 0;
			
			data[0] = counter;
			
			for (int i = 1; i < input.length(); i++) {
				if (input.charAt(i) == input.charAt(i-1)) {
					data[i] = counter;
				} else {
					data[i] = ++counter;
				}
			}
			
			System.out.println("Case " + caseCount++ + ":");
			input = Main.ReadLn(20);
			
			int numOfQuestions = Integer.parseInt(input.trim());
			//System.out.println("Num of questions " + numOfQuestions);
			for (int k = 0; k < numOfQuestions; k++) {
				input = Main.ReadLn(50);
				idata = new StringTokenizer(input);
				int i = Integer.parseInt(idata.nextToken());
				int j = Integer.parseInt(idata.nextToken());
				if (j < i) {
					int pom = j;
					j = i;
					i = pom;
				}
				if (j < data.length && i < data.length) {
					if (data[i] == data[j])
						System.out.println("Yes");
					else 
						System.out.println("No");
				} else {
					System.out.println("No");
				}
			}
		}
    }
}