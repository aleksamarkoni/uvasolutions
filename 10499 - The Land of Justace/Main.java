#include <stdio.h>
int main()
{
    long long int n;
    scanf("%lld",&n);
    while(n>=0)
    {
         
        printf("%lld",(n>1)?n*25:0);
        putchar('%');
        putchar('\n');
        scanf("%lld",&n);
    }
    return 0;
}


/* posto je nesto java zajebavala, kao i uvek u ovim resenjima sa testova, morao sam da skinem ovaj c program, rade istu stvar
ali opet ovaj u c-u radi a u javi ne. 
Objasnjenje i java neuspelo resenje nalaze se ispod*/


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
    	/*
    	 * Ovo je resenje, povrsina lopte je 4r^2pi,
    	 * e sada ovako, svaki isecak se sastoji od jednog celog kruga, to su unutrasnji delovi 
    	 * kriske, i onog dela spolja, koji u stvari i ne moramo racunati, jer kada saberemo 
    	 * sve delove dobijemo opet loptu, cija je povrsina 4r^2pi
    	 * pa je ukupan prihod u stvari prodati delovi = (4r^2pi+nr^2pi) ulozen novac (4r^2pi)
    	 * pa je prihod (prodatidelovi - ulozennovac) / ulozennovac * 100
    	 * to jest kada se skrati ostaje n/4 * 100 sto je 25 * n
    	 * */
        String input;
        while ((input = Main.ReadLn (255)) != null)
        {
        	BigInteger n = new BigInteger(input.trim());
        	if (n.equals(new BigInteger("1")))
        		System.out.println(0 + "%");
        	else if (n.equals(new BigInteger("-1")))
        		break;
        	else
        		System.out.println(n.multiply(new BigInteger("25")) + "%");
        }
    }
}

