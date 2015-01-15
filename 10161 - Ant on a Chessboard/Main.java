// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class Main
{    
	public static class Token implements Comparable<Token> {
		public String name;
		public int pocetak;
		public int recenica;
		public Token(String name, int pocetak, int recenica) {
			this.name = name;
			this.pocetak = pocetak;
			this.recenica = recenica;
		}
		public String toString() {
			return name + " " + pocetak + " " + recenica;
		}
		@Override
		public int compareTo(Token t) {
			if (name.equals(t.name)) 
				if (t.recenica == recenica)
					return pocetak-t.pocetak;
				else
					return recenica-t.recenica;
			else
				return name.compareTo(t.name);
		}
	}
	public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
    	Scanner in = new Scanner(System.in);
    	while (in.hasNextLine()) {
    		int broj = Integer.parseInt(in.nextLine().trim());
    		if (broj == 0) break;
    		int red, kol;
    		if (broj == 1) {
    			red = 1;
    			kol = 1;
    		}
    		else {
				int low = (int)(Math.sqrt(broj-1));
				int high = low + 1;
				int mid = (high*high)-high+1;
				//System.out.println(mid);
				if (broj <= mid) {
					red = high;
					kol = broj - (low*low);
				}
				else {
					kol = high;
					red = high*high-broj+1;
				}
				if (high % 2 == 0) {
					int pom = kol;
					kol = red;
					red = pom;
				}
    		}
    		System.out.println(red + " " + kol);
    	}
    	in.close();
    }
}