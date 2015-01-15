// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class BigChocolate10970
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
        BigChocolate10970 myWork = new BigChocolate10970();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
        String input;
        StringTokenizer idata;
        int m, n;

        while ((input = BigChocolate10970.ReadLn (20)) != null)
        {
          idata = new StringTokenizer (input);
          m = Integer.parseInt (idata.nextToken());
          n = Integer.parseInt (idata.nextToken());
          /*int odg;
          if (m > n) {
        	  if (n == 1) {
        		  odg = m-1;
        	  }
        	  else {
        		  odg = (m) * (n-1) + (m - 1);
        	  }
          }
          else if (m == n) {
        	  odg = (m) * (n - 1) + m - 1;
          }
          else {
        	  if (m == 1) {
        		  odg = n - 1;
        	  }
        	  else  {
        		  odg = (n) * (m - 1) + (n - 1);
        	  }
          }
          System.out.println (odg);
        */
          // Proba uproscenog resenja 
          System.out.println(m*n -1);
        }
    }
}