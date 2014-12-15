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

	int calculateScore(String name) {
		int res = 0;
		for (int i = 0; i < name.length(); i++) {
			if (Character.isLetter(name.charAt(i))) {
				 res += Character.toLowerCase(name.charAt(i)) - 'a' + 1;
			}
		}
		//System.out.println(res);
		while (res >= 10) {
			int sum = 0;
			while (res != 0) {
				sum += res % 10;
				res /=  10;
			}
			res = sum;
		}
		return res;
	}
	
    void Begin()
    {
        String input;
        StringTokenizer idata;
        String word;
		
        while ((input = Main.ReadLn (500)) != null)
        {
			
			idata = new StringTokenizer(input);
			int numOfCourses = Integer.parseInt(idata.nextToken());
			//System.out.println(input + " " +numOfCourses);
			if (numOfCourses == 0) {
				break;
			}
			int numOfCategories = Integer.parseInt(idata.nextToken());
			Set<Integer> courses = new HashSet<Integer>();
			
			for (int i = 0; i < numOfCourses;) {
				input = Main.ReadLn(500);
				idata = new StringTokenizer(input);
				while (idata.hasMoreTokens()) {
					courses.add(Integer.parseInt(idata.nextToken()));
					i++;
				}
			}
			
			boolean possible = true;
			for (int i = 0; i < numOfCategories; i++) {
				input = Main.ReadLn(10000);
				idata = new StringTokenizer(input);
				int coursesInCategorie = Integer.parseInt(idata.nextToken());
				int coursesNeeded = Integer.parseInt(idata.nextToken());
				
				int coursesTakenFromThisCategorie = 0;
				for (int j = 0; j < coursesInCategorie; j++) {
					int course = Integer.parseInt(idata.nextToken());
					if (courses.contains(course))
						coursesTakenFromThisCategorie++;
				}
				if (coursesTakenFromThisCategorie < coursesNeeded) {
					possible = false;
					}
			}
			if (possible)
				System.out.println("yes");
			else 
				System.out.println("no");
		}
    }
}