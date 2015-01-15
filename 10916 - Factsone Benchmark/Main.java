// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class FactstoneBenchmark10916
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
        FactstoneBenchmark10916 myWork = new FactstoneBenchmark10916();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
    	int bits[] = {4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304};
    	int odg[] = {3, 5, 8, 12, 20, 34, 57, 98, 170, 300, 536, 966, 1754, 3210, 5910, 10944, 20366, 38064, 71421, 134480, 254016};
    	int i;
    	while ((i = Integer.parseInt(FactstoneBenchmark10916.ReadLn(10).trim()))!= 0) {
    		System.out.println(odg[(i-1960)/10]);
    	}
    }
}