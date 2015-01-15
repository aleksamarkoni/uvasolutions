import java.io.*;
import java.math.BigInteger;
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
    	String[] tokenizer;
        while ((input = Main.ReadLn (255)) != null)
        {
        	tokenizer = input.split("\\s+");
        	int flase[] = new int[9];
        	int rez[][] = new int[3][3];
        	for (int i = 0; i<9; i ++) {
        		flase[i] = Integer.parseInt(tokenizer[i]);
        	}
        	rez[0][0] = flase[3] + flase[6];
        	rez[0][1] = flase[0] + flase[6];
        	rez[0][2] = flase[0] + flase[3];
        	
        	rez[1][0] = flase[4] + flase[7];
        	rez[1][1] = flase[1] + flase[7];
        	rez[1][2] = flase[1] + flase[4];
        	
        	rez[2][0] = flase[5] + flase[8];
        	rez[2][1] = flase[2] + flase[8];
        	rez[2][2] = flase[2] + flase[5];
        	
        	long min = rez[0][0] + rez[2][1] + rez[1][2];
        	String order = "BCG";
        	
        	long pom = rez[0][0] + rez[1][1] + rez[2][2];
        	if (pom < min) {
        		min = pom;
        		order = "BGC";
        	}
        	
        	pom = rez[2][0] + rez[0][1] + rez[1][2];
        	if (pom < min) {
        		min = pom;
        		order = "CBG";
        	}
        	
        	pom = rez[2][0] + rez[1][1] + rez[0][2];
        	if (pom < min) {
        		min = pom;
        		order = "CGB";
        	}
        	
        	pom = rez[1][0] + rez[0][1] + rez[2][2];
        	if (pom < min) {
        		min = pom;
        		order = "GBC";
        	}
        	
        	pom = rez[1][0] + rez[2][1] + rez[0][2];
        	if (pom < min) {
        		min = pom;
        		order = "GCB";
        	}
        	
        	System.out.println(order + " " + min);
        }
    }
}
