import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        
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
        StringTokenizer idata;
        
        String line = Main.ReadLn(10);
        int t = Integer.parseInt(line.trim());
        
        for (int i = 0; i < t; i++) {
        	idata = new StringTokenizer(Main.ReadLn(20));
        	int n = Integer.parseInt(idata.nextToken());
        	int m = Integer.parseInt(idata.nextToken());
        	n = n - 2;
        	m = m - 2;
        	if (n%3 == 0) 
        		n = n / 3;
        	else 
        		n = n / 3 + 1;
        	if (m %3 == 0)
        		m = m / 3;
        	else
        		m = m / 3 + 1;
        	System.out.println(n*m);
        }
        
    }
}