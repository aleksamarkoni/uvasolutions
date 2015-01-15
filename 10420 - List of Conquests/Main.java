import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		HashMap<String, Integer> mapa = new HashMap<String, Integer>();
		Scanner in = new Scanner(System.in);
		in.nextLine();
		while (in.hasNext()) {
			String drzava = in.next();
			drzava.trim();
			if (mapa.containsKey(drzava))
				mapa.put(drzava, mapa.get(drzava)+1);
			else mapa.put(drzava, 1);
			in.nextLine();
		}
		ArrayList<String> drzave = new ArrayList<String>(mapa.keySet());
		Collections.sort(drzave);
		for (String p: drzave) {
			System.out.println(p + " " + mapa.get(p));
		}
	}
}