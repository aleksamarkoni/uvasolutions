
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
        /* ova deo koda nam pomaze da napravimo sve moguce rotacije koje postoje
         * ima ih tacno 24, i dalje  mi nije jasno zasto, ali uzeo sam napravio sve moguce kombinacije
         * koje postoje, znaci rotacije po x po y i po z osi, i onda sve njihove kombinacije, ima ih ukupno 64
         * i onda odbacio one koje se ponavaljaju, pa je na kraju osstalo 24.
         * To mi je sve jasno, ali matematicki ne znam koju formulu da primenim da bih stigao do tog resenja.
         * Posle toga je lako, to je drugi deo zadatka
         */
        
        /*
        ArrayList<int[]> niz1 = new ArrayList<int[]>();
        for (int i = 0; i<4; i++) {
        	for (int j = 0; j<4; j++) {
        		for (int k = 0; k<4; k++) {
        			int cube[] = {1, 2, 3, 4, 5, 6};
        			rotatex(cube, i);
        			rotatey(cube, j);
        			rotatez(cube, k);
        			dodajuniz(niz1, cube);
        		}
        	}
        }

		int cube[] = {1, 2, 3, 5, 4, 6};
        rotatex(cube, 2);
        print(cube);
        
        
		odstampajniz(niz1);
        */
        
        /*ovo nije moja tabela, koja se dobija mojim, programom, sjebao sam neku paremutaciju
         * ne znam zasto, ali nije ni bitno, isti postupak si i oni koristitili.*/
        int niz[][] = {
        	    {1, 2, 3, 4, 5, 6},
        	    {1, 4, 2, 5, 3, 6},
        	    {1, 5, 4, 3, 2, 6},
        	    {1, 3, 5, 2, 4, 6},
        	    {2, 6, 3, 4, 1, 5},
        	    {2, 4, 6, 1, 3, 5},
        	    {2, 1, 4, 3, 6, 5},
        	    {2, 3, 1, 6, 4, 5},
        	    {3, 1, 2, 5, 6, 4},
        	    {3, 5, 1, 6, 2, 4},
        	    {3, 6, 5, 2, 1, 4},
        	    {3, 2, 6, 1, 5, 4},
        	    {6, 5, 3, 4, 2, 1},
        	    {6, 3, 2, 5, 4, 1},
        	    {6, 2, 4, 3, 5, 1},
        	    {6, 4, 5, 2, 3, 1},
        	    {5, 1, 3, 4, 6, 2},
        	    {5, 3, 6, 1, 4, 2},
        	    {5, 6, 4, 3, 1, 2},
        	    {5, 4, 1, 6, 3, 2},
        	    {4, 6, 2, 5, 1, 3},
        	    {4, 2, 1, 6, 5, 3},
        	    {4, 1, 5, 2, 6, 3},
        	    {4, 5, 6, 1, 2, 3}
        	};

        
        while ((input = Main.ReadLn (255)) != null)
        {
        	input = input.trim();
        	String prvakocka = input.substring(0,6);
        	String drugakocka = input.substring(6);
        	boolean istesu = false;
        	for (int i = 0; i < niz.length; i++) {
        		char rotacija[] = new char[6];
        		for (int j = 0; j < niz[i].length; j++) {
        			rotacija[j] = prvakocka.charAt(niz[i][j]-1);
        		}
        		if (drugakocka.equalsIgnoreCase(new String(rotacija))) {
        			istesu = true;
        			break;
        		}
        	}
        	if (istesu) {
        		System.out.println("TRUE");
        	} else {
        		System.out.println("FALSE");
        	}
        }
    }

	private void odstampajniz(ArrayList<int[]> niz) {
		System.out.println("{ ");
		for (int i = 0; i < niz.size(); i++) {
			System.out.print("{ ");
			for (int j = 0; j<niz.get(i).length; j++) {
				System.out.print(niz.get(i)[j]);
				if (j!= niz.get(i).length-1)
					System.out.print(", ");
			}
			System.out.println("} , ");
		}
		System.out.println("}");
	}

	private void dodajuniz(ArrayList<int[]> niz, int[] cube) {
		boolean nasao = true;
		for (int i = 0; i < niz.size(); i++) {
			if (dalisuiste(niz.get(i), cube)) {
				nasao = false;
				break;
			}
		}
		if (nasao) {
			niz.add(cube);
		}
	}

	private boolean dalisuiste(int[] is, int[] cube) {
		for (int i = 0; i < is.length; i++) {
			if (is[i] != cube[i])
				return false;
		}
		return true;
	}

	private void print(int[] cube) {
		for (int i = 0; i<cube.length; i++)
			System.out.print(cube[i] + " ");
		System.out.println();
	}

	

	

	private void rotatex(int[] cube, int numofrotations) {
		for (int i = 0; i < numofrotations; i++) {
			int pom[] = new int[6];
			for (int j = 0; j < pom.length; j++)
				pom[j] = cube[j];		
			cube[0] = pom[4];
			cube[1] = pom[1];
			cube[2] = pom[0];
			cube[3] = pom[3];
			cube[4] = pom[5];
			cube[5] = pom[2];
		}
	}
	
	private void rotatey(int[] cube, int numofrotations) {
		
		
		for (int i = 0; i < numofrotations; i++) {
			int pom[] = new int[6];
			for (int j = 0; j < pom.length; j++)
				pom[j] = cube[j];
			cube[0] = pom[1];
			cube[1] = pom[5];
			cube[2] = pom[2];
			cube[3] = pom[0];
			cube[4] = pom[4];
			cube[5] = pom[3];
		}	
	}
	
	private void rotatez(int[] cube, int numofrotations) {
		
		for (int i = 0; i < numofrotations; i++) {
			int pom[] = new int[6];
			for (int j = 0; j < pom.length; j++)
				pom[j] = cube[j];
			cube[0] = pom[0];
			cube[1] = pom[2];
			cube[2] = pom[3];
			cube[3] = pom[4];
			cube[4] = pom[1];
			cube[5] = pom[5];
		}			
	}
}