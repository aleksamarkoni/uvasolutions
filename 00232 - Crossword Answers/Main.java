import java.util.Scanner;
import java.io.*;

/* The hardest part of this problem was to read input with Java */
/* This will only work with ANSI encoded files used for input */

class Item {
  public char c;
  public int number;
  Item() {
    this.c = '-';
    this.number = -1;
  }
}

class Board {
  public int m, n;
  Item map[][];

  public Board(int m, int n) {
    this.m = m;
    this.n = n;
    map = new Item[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = new Item();
  }

  public Item getItem(int x, int y) {
    return map[x][y];
  }

  public void setItem(int x, int y, char item) {
    map[x][y].c = item;
  }

  private boolean isOnTheBoard(int i, int j) {
    return i >= 0 && j >= 0 && i < m && j < n;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        builder.append("[" + map[i][j].c + "," + map[i][j].number + "]");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public void doTheCalculation() {
    calculateEligible();
    System.out.println("Across");
    calculateWords(0, 1);
    System.out.println("Down");
    calculateWords(1, 0);
  }

  private void calculateEligible() {
    int count = 1;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j].c != '*'
          && (!isOnTheBoard(i-1, j)
          || !isOnTheBoard(i, j-1)
          || map[i-1][j].c == '*'
          || map[i][j-1].c == '*')) {
              map[i][j].number = count++;
          }
      }
    }
  }

  private void calculateWords(int x, int y) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j].number != -1) {
          if (!isOnTheBoard(i - x, j - y) || map[i - x][j - y].c == '*') {
            printWord(i, j, x, y);
          }
        }
      }
    }
  }

  private void printWord(int i, int j, int x, int y) {
    StringBuilder builder = new StringBuilder();
    int ni, nj;
    for (int k = 0; k < 10; k++) {
      ni = i + x * k;
      nj = j + y * k;
      if (!isOnTheBoard(ni, nj) || map[ni][nj].c == '*')
        break;
      builder.append(map[ni][nj].c);
    }
    System.out.printf("%3d.%s\n",map[i][j].number, builder.toString());
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
    String line;
    int tc = 0, m, n;
    while (true) {
      m = in.nextInt();
      if (m == 0)
        break;
      n = in.nextInt();
      in.nextLine();
      if (++tc != 1) {
        System.out.println();
      }
      Board board = new Board(m, n);
      for (int i = 0; i < m; i++) {
        line = in.nextLine();
        for (int j = 0; j < n; j++) {
          board.setItem(i, j, line.charAt(j));
        }
      }
      System.out.println("puzzle #" + tc + ":");
      board.doTheCalculation();
    }
	}
}
