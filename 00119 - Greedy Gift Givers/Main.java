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
        int count;
		int num = 0;
		
        while ((input = Main.ReadLn (255)) != null)
        {
			if (num > 0)
			System.out.println();
			num ++ ;
          idata = new StringTokenizer (input);
          count = Integer.parseInt(idata.nextToken());
		  
		  ArrayList<String> userNames = new ArrayList<String>();
		  HashMap<String, User> users = new HashMap<String, User>();
		  
		  input = Main.ReadLn(255);
		  idata = new StringTokenizer(input);
		  for (int i = 0; i < count; i++) {
			String name = idata.nextToken();
			users.put(name, new User(name));
			userNames.add(name);
		  }
		  
		  for (int i = 0; i < count; i++) {
			input = Main.ReadLn(255);
			idata = new StringTokenizer(input);
			String name = idata.nextToken();
			User user = users.get(name);
			int give = Integer.parseInt(idata.nextToken());
			int countGiven = Integer.parseInt(idata.nextToken());
			
			int amountToGive = 0;
			if (countGiven != 0) 
				amountToGive = give / countGiven;
			
			user.amountToGive += give;
			
			if (countGiven != 0)
				user.amountReceived += give % countGiven;
			else 
				user.amountReceived += give;
			
			//System.out.println(name + " " + "given: " + give + " countGiven: " + countGiven + " amountToGive: " + amountToGive);
			for (int j = 0; j < countGiven; j++) {
				String giveToName = idata.nextToken();
				users.get(giveToName).amountReceived += amountToGive;
				//System.out.println(giveToName + " " + amountToGive + users.get(giveToName).amountReceived);
			}
			
		  }
		  
		  
		  for (String userName: userNames) {
			User user = users.get(userName);
			System.out.println(user.name + " " + (user.amountReceived - user.amountToGive));
		  }
		  
        }
    }
	
	private class User {
		public String name;
		public String moneyGiven;
		public int amountToGive;
		public int amountReceived;
	
		public User(String name) {
			this.name = name;
		}
	}
}

