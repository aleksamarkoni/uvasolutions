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
		int numOfRequests, numOfProposals;
		int num = 1;
		
        while ((input = Main.ReadLn (255)) != null)
        {
			idata = new StringTokenizer (input);
			numOfRequests = Integer.parseInt(idata.nextToken());
			numOfProposals = Integer.parseInt(idata.nextToken());
			
			if (numOfRequests == 0 && numOfProposals == 0)
				break;
			
			if (num > 1)
				System.out.println();
			
			for (int i = 0; i < numOfRequests; i++) {
				input = Main.ReadLn(255);
			}
			
			String bestProposalName = "";
			double bestProposal = Double.MAX_VALUE;
			double mostRequestsDone = 0.0;
		
			
			for (int i = 0; i < numOfProposals; i++) {
				input = Main.ReadLn(255);
				String proposalName = input.trim();
				input = Main.ReadLn(255);
				idata = new StringTokenizer(input);
				double amount = Double.parseDouble(idata.nextToken());
				int requestsDone = Integer.parseInt(idata.nextToken());
				for (int j = 0; j < requestsDone; j++) {
					input = Main.ReadLn(255);
				}
				
				double procentOfRequestsDone = (double) requestsDone / numOfRequests;
				if ( procentOfRequestsDone > mostRequestsDone ) {
						bestProposal = amount;
						bestProposalName = proposalName;
						mostRequestsDone = procentOfRequestsDone; 
				} else if ((double)requestsDone / numOfRequests == mostRequestsDone) {
					if (amount < bestProposal) {
						bestProposal = amount;
						bestProposalName = proposalName;
						mostRequestsDone = procentOfRequestsDone;
					}
				}
				
			}
			System.out.println("RFP #" + num++);
			System.out.println(bestProposalName);
	    }
	}
}