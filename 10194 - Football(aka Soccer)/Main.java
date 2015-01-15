// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        
        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if (lg == 0 && car == ' ') continue;
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
    	try {
    	System.setIn(new FileInputStream("ulaz.txt"));
    	Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    void Begin() throws Exception
    {	
    	Scanner cin = new Scanner(System.in, "ISO-8859-1");
    	StringBuffer buf = new StringBuffer();
    	OutputStreamWriter cout = new OutputStreamWriter(System.out, "ISO-8859-1");
        //String input;
        int n;
        //System.out.println("Pocelo");
        //input = Main.ReadLn(20);
        //buf.append(input.replace("\r", "").replace("\n", ""));
        //n = Integer.parseInt(input.trim());
        n = cin.nextInt();
        cin.nextLine();
        //System.out.println(n);
        for (int i = 0; i < n; i++) {
        	//String tournamentname = Main.ReadLn(105).replace("\n", "").replace("\r","");
        	String tournamentname = cin.nextLine();
        	if (i > 0)
        		buf.append("\n");
        	//int numberOfTeams = Integer.parseInt(Main.ReadLn(10).trim());
        	int numberOfTeams = cin.nextInt();
        	cin.nextLine();
        	ArrayList<Tim> timovi = new ArrayList<Tim>(numberOfTeams);
        	for (int j = 0; j < numberOfTeams; j++) {
        		//timovi.add(new Tim(Main.ReadLn(50).replace("\n", "").replace("\r", "")));
        		timovi.add(new Tim(cin.nextLine()));
            	
        	}
        	int brojOdigranihUtakmicaDoSada;
        	//brojOdigranihUtakmicaDoSada = Integer.parseInt(Main.ReadLn(10).trim());
        	brojOdigranihUtakmicaDoSada = cin.nextInt();
        	cin.nextLine();
        	//System.out.println(brojOdigranihUtakmicaDoSada);
        	for (int j = 0; j<brojOdigranihUtakmicaDoSada; j++) {
        		 //String[] result = Main.ReadLn(100).replace("\n", "").replace("\r","").split("[#@]");
        		 String[] result = cin.nextLine().split("[#@]");
        	     //for (int x=0; x<result.length; x++)
        	     //    System.out.println(result[x]);
        		 int a = Integer.parseInt(result[1]);
        		 int b = Integer.parseInt(result[2]);
        		 
        		 for (int x=0; x<numberOfTeams; x++) {
        	    	 //System.out.println("Uporedjujem " + timovi.get(x).ime + " " + result[0]);
        	         if (timovi.get(x).ime.equals(result[0])) {
        	        	timovi.get(x).golovidati += a;
        	         	timovi.get(x).goloviprimljeni += b;
        	         	if (a>b) {
        	         		timovi.get(x).poeni+=3;
        	         		timovi.get(x).pobede+=1;
        	         		}
        	         	else if (a==b) {
        	         		timovi.get(x).poeni+=1;
        	         		timovi.get(x).neresene+=1;
        	         	}
        	         	else {
        	         		timovi.get(x).izgubljene+=1;
        	         		}
        	         	}
        	         if (timovi.get(x).ime.equals(result[3])) {
        	        	timovi.get(x).golovidati += b;
        		        timovi.get(x).goloviprimljeni += a;
        		        if (a>b) {
        	         		timovi.get(x).izgubljene+=1;
        	         		}
        	         	else if (a==b) {
        	         		timovi.get(x).poeni+=1;
        	         		timovi.get(x).neresene+=1;
        	         	}
        	         	else {
        	         		timovi.get(x).poeni+=3;
        	         		timovi.get(x).pobede+=1;
        	         		}
        	         	}
        	     }
        	}
        	Collections.sort(timovi, Tim.TimComparator);
        	buf.append(tournamentname + "\n");
        	for (int j = 0; j < numberOfTeams; j++) {
        		buf.append((j+1) + ") " + timovi.get(j) + "\n");
        	}
        }
        cout.write(buf.toString());
        cout.flush();
    }
    public static class Tim{
    	public String ime;
    	public int poeni;
    	public int pobede;
    	public int neresene;
    	public int izgubljene;
    	public int golovidati;
    	public int goloviprimljeni;
    	
    	public Tim(String ime) {
    		this.ime = ime;
    	}
    	public String toString() {
    		StringBuilder pom = new StringBuilder();
    		int brojutakmica = pobede+neresene+izgubljene;
    		pom.append(ime + " "+ poeni + "p, " + brojutakmica + "g "+ 
    		"(" + pobede + "-" + neresene + "-" + izgubljene + "), " + (golovidati-goloviprimljeni)+ "gd " + 
    		"(" + golovidati + "-" + goloviprimljeni + ")");
    		return pom.toString();
    	}
    	public static Comparator<Tim> TimComparator 
        = new Comparator<Tim>() {

    		public int compare(Tim t1, Tim t2) {
    			if (t1.poeni>t2.poeni) 	
    				return -1;
    			else if (t1.poeni<t2.poeni)
    				return 1;
    			else if (t1.pobede>t2.pobede)
    				return -1;
    			else if (t1.pobede<t2.pobede)
    				return 1;
    			else { 
    				int gd1 = t1.golovidati-t1.goloviprimljeni;
    				int gd2 = t2.golovidati-t2.goloviprimljeni;
    				if (gd1>gd2)
    					return -1;
    				else if (gd1<gd2)
    					return 1;
    				else if (t1.golovidati>t2.golovidati)
    					return -1;
    				else if (t1.golovidati<t2.golovidati)
    					return 1;
    				else {
    					int gp1 = t1.pobede + t1.neresene + t1.izgubljene;
    					int gp2 = t2.pobede + t2.neresene + t2.izgubljene;
    					if (gp1>gp2)
    						return 1;
    					else if (gp1<gp2)
    						return -1;
    					else  {
    						char s1[] = t1.ime.toCharArray();
    					    char s2[] = t2.ime.toCharArray();
    					    /*for (int i=0; i<s1.length; i++) {
    					        if ((s1[i] <= 'z') && (s1[i] >='a'))
    					            s1[i] = Character.toUpperCase(s1[i]);
    					    }
    					    for (int i=0; i<s2.length; i++)
    					        if (s2[i] <='z' && s2[i] >='a')
    					            s2[i] = Character.toUpperCase(s2[i]);
    					    String ss1 = new String(s1);
    					    String ss2 = new String(s2);
    					    return ss1.compareTo(ss2);
    					    */
    					    int first1 = 0, last1 = s1.length;
    					    int first2 = 0, last2 = s2.length;
    						while (first1!=last1)
    					    {
    					      if (first2==last2) return 1;
    					      if ((s1[first1]<='z' && s1[first1]>='a')||(s1[first1]<='Z' && s1[first1]>='A')) {
    					    	  if (s1[first1]<='Z' && s1[first1]>='A')
    					    		  s1[first1] = (char)(s1[first1] + 32);
    					      }
    					      if ((s2[first2]<='z' && s2[first2]>='a')||(s2[first2]<='Z' && s2[first2]>='A')) {
    					    	  if (s2[first2]<='Z' && s2[first2]>='A')
    					    		  s2[first2] = (char)(s2[first2] + 32);
    					      }
    					      if (s2[first2]<s1[first1]) return 1;
    					      else if (s1[first1]<s2[first2]) return -1;
    					      ++first1; ++first2;
    					    }
    					    return (first2-last2);
    					    
    					}
    				}
    			}
        		}
    	};
    } 
}