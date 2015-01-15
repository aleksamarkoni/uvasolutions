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
        	float H = Integer.parseInt(tokenizer[0]);
        	float U = Integer.parseInt(tokenizer[1]);
        	float D = Integer.parseInt(tokenizer[2]);
        	float F = Integer.parseInt(tokenizer[3]);
        	
        	if (H == 0)
        		break;
        	// prvo izracunamo koliko puz gubi na penjanju svakog dana,
        	// to je 10% od onoga koliko moze da se popne prvog dana, i to je konsatntno od tada
        	float fatuage = U/100*F;
        	int brojdana = 1;
        	float kolikosepopeo = 0;
        	while (true) {
        		
        		// izracunamo koliko se popeo
        		kolikosepopeo += U;
        		// ako je presao (strogo presao) preko H(visine zida) to je to
        		if (kolikosepopeo > H) {
        			System.out.println("success on day " + brojdana);
        			break;
        		}
        		// izracunamo koliko se spustio na dole tokom noci
        		kolikosepopeo -= D;
        		// ako je pao ispod nula, strogo ispod nula to je to
        		if (kolikosepopeo < 0) {
        			System.out.println("failure on day " + brojdana);
        			break;
        		}
        		
        		// racunamo koliko manje ce se popeti sledeceg dana, ako udje u minus, ostaje na nuli
        		// to je po uslovu zadatka
        		if (U - fatuage <0) {
        			U = 0;
        		}
        		else {
        			U = U - fatuage;
        		}
        		// i na kraju povecavamo broj dana
        		brojdana++;
        	}
        }
    }
}
