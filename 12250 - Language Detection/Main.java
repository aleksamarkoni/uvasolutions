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
		
        while ((input = Main.ReadLn (255)) != null)
        {
          idata = new StringTokenizer (input);
          word = idata.nextToken();
		  
		  if (word.equals("#"))
			break;
		  if (word.equals("HELLO"))
			answer = "ENGLISH";
		  else if (word.equals("HOLA"))
			answer = "SPANISH";
		  else if (word.equals("HALLO"))
			answer = "GERMAN";
		  else if (word.equals("BONJOUR"))
			answer = "FRENCH";
		  else if (word.equals("CIAO"))
			answer = "ITALIAN";
		  else if (word.equals("ZDRAVSTVUJTE"))
			answer = "RUSSIAN";
		  else 
			answer = "UNKNOWN";
		  System.out.println("Case " + c++ + ": " + answer);
        }
    }
}