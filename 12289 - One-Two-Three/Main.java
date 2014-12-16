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
	
	public int checkWord(String word) {
		
		String numbers[] = {"one", "two", "three"};
		
		for (int i = 0; i < 3; i++) {
			if (word.length() == numbers[i].length()) {
				int diff = 0;
				for (int j = 0; j < word.length(); j++) {
					if (word.charAt(j) == numbers[i].charAt(j)) {
						diff++;
					}
				}
				if (diff == word.length() || diff == (word.length() - 1))
					return i+1;
			}
		}
		return 0;
	}

    void Begin()
    {
        String input;
        StringTokenizer idata;
		
		input = Main.ReadLn(255);
		idata = new StringTokenizer(input);
		int count = Integer.parseInt(idata.nextToken());
		
		for (int i = 0; i < count; i++) {
			input = Main.ReadLn(255);
			idata = new StringTokenizer(input);
			String number = idata.nextToken();
			System.out.println(checkWord(number));
		}
    }
}