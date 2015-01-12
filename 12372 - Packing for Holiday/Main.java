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
        String word, answer;
		int c = 1;
		int x, y, z;
		
		input = Main.ReadLn(255);
		idata = new StringTokenizer(input);
		int count = Integer.parseInt(idata.nextToken());
		
		for (int i = 0; i < count; i++) {
			input = Main.ReadLn(255);
			idata = new StringTokenizer(input);
			x = Integer.parseInt(idata.nextToken());
			y = Integer.parseInt(idata.nextToken());
			z = Integer.parseInt(idata.nextToken());	
			if (x <= 20 && y <= 20 && z <= 20) {
				System.out.println("Case " + c++ + ": good");
			} else {
				System.out.println("Case " + c++ + ": bad");
			}
		}
		
    }
}