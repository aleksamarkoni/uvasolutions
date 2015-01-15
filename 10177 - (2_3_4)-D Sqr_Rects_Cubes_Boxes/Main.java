// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class DSqr_Rects_Cubes_Boxes10177
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
        DSqr_Rects_Cubes_Boxes10177 myWork = new DSqr_Rects_Cubes_Boxes10177();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
        String input;
        StringTokenizer idata;

        while ((input = DSqr_Rects_Cubes_Boxes10177.ReadLn (255)) != null)
        {
        	long n = Integer.parseInt(input.trim());
        	long prvi = (n*(n+1)*(2*n+1))/6;
        	
        	long k = (n*(n+1))/2;
        	long treci = k * k;
        	// ovde treba cetvrti
        	long cetvrti = k*k*k-treci;
        	long peti = (n*(n+1)*(2*n+1)*(3*n*n+3*n-1))/30;
        	// ovde treba sesti
        	long sesti = k*k*k*k - peti;      	
        	System.out.println(prvi + " " + (treci - prvi) + " " + treci + " " + cetvrti + " " + peti + " " + sesti);
        }
    }
}