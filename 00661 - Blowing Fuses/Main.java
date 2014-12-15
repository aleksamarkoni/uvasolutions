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
        String word;
		int n, m, c;
		int sequance = 1;
		
        while ((input = Main.ReadLn (255)) != null)
        {
          idata = new StringTokenizer (input);
		  n = Integer.parseInt(idata.nextToken());
		  m = Integer.parseInt(idata.nextToken());
		  c = Integer.parseInt(idata.nextToken());
	      if (n == 0 && m == 0 && c == 0)
			break;
		  
		  Device[] devices = new Device[n];
		  
		  for (int i = 0; i < n; i++) {
			input = Main.ReadLn(255);
			devices[i] = new Device(Integer.parseInt(input.trim()));
		  }
		  
		  int currentPower = 0;
		  int maxPower = 0;
		  boolean blown = false;
		  for (int i = 0; i < m; i++) {
			// do the thing
			input = Main.ReadLn(255);
			if (!blown) {
				int d = Integer.parseInt(input.trim());
				if (devices[d-1].turned) {
					currentPower -= devices[d-1].capacity;
					devices[d-1].turned = false;
				} else {
					if (currentPower + devices[d-1].capacity > c) {
						blown = true;
					} else {
						currentPower += devices[d-1].capacity;
						devices[d-1].turned = true;
						if (currentPower > maxPower)
							maxPower = currentPower;
					}
				}
			}
		  }
		  
		  System.out.println("Sequence " + sequance++);
		  if (blown) 
			System.out.println("Fuse was blown.");
		  else {
			System.out.println("Fuse was not blown.");
			System.out.println("Maximal power consumption was " + maxPower + " amperes.");
		  }
		  System.out.println();
		}
    }
	private class Device {
		public int capacity;
		public boolean turned;
		public Device(int capacity) {
			this.capacity = capacity;
			turned = false;
		}
		
	}
}