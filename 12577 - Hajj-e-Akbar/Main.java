

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
		int countOfLines = 1;
        while ((input = Main.ReadLn (255)) != null)
        {
          if (input.trim().equals("*")) {
            break;
          }
          if (input.trim().equals("Hajj"))
            System.out.println("Case " + countOfLines + ": Hajj-e-Akbar");
          else if (input.trim().equals("Umrah"))
            System.out.println("Case " + countOfLines + ": Hajj-e-Asghar");
          countOfLines++;
        }
    }
}


