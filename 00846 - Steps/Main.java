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
		/* ovo je vrlo ok zadatak, vrlo malo treba da se zna, u sustini imamo sumu 1 + 2 + 3 + 4 + 5 + ... = n*(n-1)/2 */
        String input;
        StringTokenizer idata;
        long a, b;
        int case_num = Integer.parseInt(Main.ReadLn(10).trim());
        int i = 0;
        while (i++<case_num)
        {
        	input = Main.ReadLn(60);
          idata = new StringTokenizer (input);
          a = Integer.parseInt (idata.nextToken());
          b = Integer.parseInt (idata.nextToken());
          long diff = b-a;
          long root = (long)Math.sqrt(diff);
          if (diff == 0)
        	  System.out.println(0);
          else if (root * root == diff) {
        	  System.out.println(2 * root - 1);
          }
          else if (root * root + root < diff) {
        	  System.out.println(2 * root + 1);
          }
          else {
        	  System.out.println(2 * root);
          }
        }
    }
}
