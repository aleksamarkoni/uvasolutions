import java.util.Scanner;
import java.io.*;


class Board {
  public int m, n;
  int map[][];
  int colum[];
  int columLen;

  public Board(int m, int n) {
    this.m = m;
    this.n = n;
    map = new int[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = -1;

    colum = new int[n];
    for (int i = 0; i < n; i++)
      colum[i] = m;
    columLen = n;
  }

  public int getItem(int x, int y) {
    return map[x][y];
  }

  public void setItem(int x, int y, int item) {
    map[x][y] = item;
  }

  private boolean isOnTheBoard(int i, int j) {
    return i >= 0 && j >= 0 && i < m && j < n;
  }

  @Override
  public String toString() {
    //int max = 0;
    //for (int i = 0; i < columLen; i++)
      //if (colum[i] > max)
        //max = colum[i];
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < m; i++) {
      builder.append("    ");
      for (int j = 0; j < n; j++) {
        builder.append((map[m - i - 1][j] == -1?" ":map[m-i-1][j]) + (j < (n-1)?" ":""));
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public void removeRegion(int x, int y) {
    //for (int i = 0; i < columLen; i++) {
    //  System.out.print(colum[i] + " ");
    //}
    //System.out.println();
    if (isValidRegion(x, y)) {
      boolean compress = removeCells(x, y, map[x][y]);
      //System.out.println("Remove");
      //System.out.println(this);
      compressRows();
      //System.out.println("c rows: ");
      //System.out.println(this);
      if (compress) { // if after this removal there are no items inside some colum
        //System.out.println("Compress col");
        compressColums();
      }
    }
  }

  private boolean removeCells(int x, int y, int value) {
    if (!isOnTheBoard(x, y) || map[x][y] != value)
      return false;
    boolean result = false;
    map[x][y] = -1;
    if (--colum[y] == 0) { // now we have one less number in this colm
      //System.out.println("No more items inside colum");
      result = true; // if no items left in coulum, do the copress of colums
    }
    result = removeCells(x-1, y, value) || result;
    result = removeCells(x+1, y, value) || result;
    result = removeCells(x, y-1, value) || result;
    result = removeCells(x, y+1, value) || result;
    return result;
  }

  private boolean isValidRegion(int x, int y) {
    if  (!isOnTheBoard(x, y))
      return false;
    if (map[x][y] == -1)
      return false;
    // Check if there is one more number same as one on x, y in the connected cells
    // this is to check if reagon has at least 2 elements.
    if (isOnTheBoard(x-1, y) && map[x-1][y] == map[x][y])
      return true;
    if (isOnTheBoard(x+1, y) && map[x+1][y] == map[x][y])
      return true;
    if (isOnTheBoard(x, y-1) && map[x][y-1] == map[x][y])
      return true;
    if (isOnTheBoard(x, y+1) && map[x][y+1] == map[x][y])
      return true;
    //This is reagon with single value
    return false;
  }

  private void compressColums() {
    int k = 0;
    for (int i = 0; i < columLen; i++) {
      if (colum[i] != 0) {
        if (i != k) {
          colum[k] = colum[i];
          //move colum
          for (int j = 0; j < m; j++) {
            map[j][k] = map[j][i];
            map[j][i] = -1;
          }
        }
        k++;
      }
    }
    columLen = k;
  }

  private void compressRows(){
    for (int j = 0; j < columLen; j++) {
      if (colum[j] == 0)
        continue;
      int k = 0;
      for (int i = 0; i < m; i++) {
        if (map[i][j] != -1) {
          if (i != k) {
            map[k][j] = map[i][j];
            map[i][j] = -1;
          }
          k++;
        }
      }
    }
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
    String line;
    int tc = 0, m, n;
    while (true) {
      m = in.nextInt();
      n = in.nextInt();
      if (m == 0 || n == 0)
        break;
      if (++tc != 1) {
        System.out.println();
      }
      Board board = new Board(m, n);
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          board.setItem(i, j, in.nextInt());
        }
      }
      System.out.println("Grid " + tc + ".");
      //System.out.println(board);
      int x, y;
      while (true) {
        x = in.nextInt();
        y = in.nextInt();
        if (x == 0 && y == 0)
          break;
        //System.out.println("Move: " + (x-1) + " " + (y-1));
        board.removeRegion(x-1, y-1);
        //System.out.println("After move");
        //System.out.println(board);
      }
      if (board.columLen != 0) {
        System.out.print(board);
      }
      else
        System.out.println("    Game Won");
    }
	}
}
