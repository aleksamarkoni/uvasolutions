import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
  public static void main(String args[]) {

    Scanner in = new Scanner(System.in);

    int numOfCases = in.nextInt();
    int numOfInstructions = 0;
    int[] registers = new int[10];
    int instruction = 0;
    int[] ram = new int[1000];

    for (int i = 0; i < ram.length; i++) {
      ram[i] = i;
    }

    while (true) {

      instruction = in.nextInt();
      in.nextLine();

      int three = instruction / 100;
      int remainder2 = instruction % 100;
      int two = remainder2 / 10;
      int zero = instruction % 10;

        if (three == 2) {
          registers[two] = zero;
          numOfInstructions++;
        } else if (three == 3) {
          registers[two] = registers[two] + zero;
          numOfInstructions++;
        } else if (three == 4) {
          registers[two] = registers[two] * zero;
          numOfInstructions++;
        } else if (three == 5) {
          registers[two] = registers[zero];
          numOfInstructions++;
        } else if (three == 6) {
          registers[two] += registers[zero];
          numOfInstructions++;
        } else if (three == 7) {
          registers[two] = registers[two] * registers[zero];
          numOfInstructions++;
        } else if (three == 8){
          registers[two] = ram[registers[zero]];
          numOfInstructions++;
        } else if (three == 9) {
          ram[registers[zero]] = registers[two];
          numOfInstructions++;
        } else if (three == 100) {
          numOfInstructions++;
          break;
        }


      System.out.println(Arrays.toString(registers));

      System.out.println( three + " " + two + " " + zero);

    }
  }
}
