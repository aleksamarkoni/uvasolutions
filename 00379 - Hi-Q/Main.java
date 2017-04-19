import java.util.Scanner;
import java.io.*;

/*
Seems hard but really straight forword:
1) Use matrix with 0 on borders so you don't have to check if the move is out of the Board
2) Make a list of x, y cordinates for every position from 1..33 so you can
  traverse from 33..1 and not use double loop.
*/

class Board {
  public int m, n;
  boolean map[][];

  public Board(int m, int n) {
    this.m = m;
    this.n = n;
    map = new boolean[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = false;
  }

  public boolean getItem(int x, int y) {
    return map[x][y];
  }

  public void setItem(int x, int y, boolean item) {
    map[x][y] = item;
  }

  public void doTheThing() {
    boolean hasMove = true;
    while (hasMove) {
      hasMove = doTheMove();
    }
  }

  private boolean doTheMove() {
    for (int i = 33; i > 0; i--) {
      int bestFit = -1;
      int bestx = -1, besty = -1;
      int x = Main.rconvertx[i];
      int y = Main.rconverty[i];
      if (map[x][y] == true)
        continue;

      //UP
      if (map[x-1][y] == true && map[x-2][y] == true) {
        int p = Main.convert[x-2][y];
        if (p > bestFit) {
          bestFit = p;
          bestx = -1;
          besty = 0;
        }
      }

      //Down
      if (map[x+1][y] == true && map[x+2][y] == true) {
        int p = Main.convert[x+2][y];
        if (p > bestFit) {
          bestFit = p;
          bestx = 1;
          besty = 0;
        }
      }

      //Left
      if (map[x][y-1] == true && map[x][y-2] == true) {
        int p = Main.convert[x][y-2];
        if (p > bestFit) {
          bestFit = p;
          bestx = 0;
          besty = -1;
        }
      }

      //Right
      if (map[x][y+1] == true && map[x][y+2] == true) {
        int p = Main.convert[x][y+2];
        if (p > bestFit) {
          bestFit = p;
          bestx = 0;
          besty = 1;
        }
      }

      if (bestFit == -1)
        continue;

      map[x + bestx * 2][y + besty * 2] = false;
      map[x + bestx][y + besty] = false;
      map[x][y] = true;
      return true;
    }
    return false;
  }

  public int getScore() {
    int sum = 0;
    for (int i = 33; i > 0; i--) {
      if (map[Main.rconvertx[i]][Main.rconverty[i]] == true)
        sum += i;
    }
    return sum;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        builder.append(map[i][j]?"1":"0");
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}

public class Main {

  public static int convert[][] = {
    {0,  0,  0,  0,  0,  0,  0,  0,  0},
    {0,  0,  0,  1,  2,  3,  0,  0,  0},
    {0,  0,  0,  4,  5,  6,  0,  0,  0},
    {0,  7,  8,  9, 10, 11, 12, 13,  0},
    {0, 14, 15, 16, 17, 18, 19, 20,  0},
    {0, 21, 22, 23, 24, 25, 26, 27,  0},
    {0,  0,  0, 28, 29, 30,  0,  0,  0},
    {0,  0,  0, 31, 32, 33,  0,  0,  0},
    {0,  0,  0,  0,  0,  0,  0,  0,  0}
  };

  public static int rconvertx[] = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3,
    4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7};
  public static int rconverty[] = {0, 3, 4, 5, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7,
    1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 3, 4, 5, 3, 4, 5};

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
    int tc = in.nextInt();
    int n;
    System.out.println("HI Q OUTPUT");
    while (tc-- != 0) {
      Board board = new Board(9, 9);
      while (true) {
        n = in.nextInt();
        if (n == 0)
          break;
        board.setItem(rconvertx[n], rconverty[n], true);
      }
      board.doTheThing();
      System.out.println(board.getScore());
    }
    System.out.println("END OF OUTPUT");
	}
}
