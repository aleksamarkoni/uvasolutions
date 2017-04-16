import java.util.Scanner;
import java.io.*;

/* The hardest part of this problem was to read input with Java */
/* This will only work with ANSI encoded files used for input */

class Board {
  public int m, n;
  public int i, j; // empty square
  char map[][];

  public Board(int m, int n) {
    this.m = m;
    this.n = n;
    map = new char[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = ' ';
  }

  public char getItem(int x, int y) {
    return map[x][y];
  }

  boolean makeMove(char move) {
    switch(move) {
      case 'A':
        if (!isOnTheBoard(i-1, j))
          return false;
        map[i][j] = map[i-1][j];
        map[i-1][j] = ' ';
        i = i - 1;
        break;
      case 'B':
        if (!isOnTheBoard(i+1, j))
          return false;
        map[i][j] = map[i+1][j];
        map[i+1][j] = ' ';
        i = i + 1;
        break;
      case 'R':
        if (!isOnTheBoard(i, j+1))
          return false;
        map[i][j] = map[i][j+1];
        map[i][j+1] = ' ';
        j = j + 1;
        break;
      case 'L':
        if (!isOnTheBoard(i, j-1))
          return false;
        map[i][j] = map[i][j-1];
        map[i][j-1] = ' ';
        j = j - 1;
        break;
      default:
        return false;
    }
    return true;
  }

  public void setItem(int x, int y, char item) {
    //System.out.println(x + " " + y + ":" + item + ":");
    if (item == ' ') {
      i = x;
      j = y;
    }
    map[x][y] = item;
  }

  private boolean isOnTheBoard(int i, int j) {
    return i >= 0 && j >= 0 && i < m && j < n;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        builder.append(map[i][j] + (j<(n-1)?" ":""));
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int tc = 0;
    while (true) {
      char c = (char) in.read();
      if (c == 'Z')
        break;
      if (++tc != 1) {
        System.out.println();
      }
      Board board = new Board(5, 5);
      board.setItem(0, 0, c);
      for (int j = 1; j < 5; j++) {
        c = (char) in.read();
        //System.out.println("|c|");
        board.setItem(0, j, c);
      }
      //skip newline
      in.readLine();

      for (int i = 1; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          c = (char) in.read();
          //System.out.println("|" + c + "|");
          board.setItem(i, j, c);
        }
        in.readLine(); // skip newline
      }
      boolean legalMove = true;
      boolean done = false;
      while (!done) {
        line = in.readLine();
        for (int i = 0; i < line.length(); i++) {
          char move = line.charAt(i);
          if (move == '0') {
            done = true;
            break;
          }
          if (legalMove) {
            legalMove = board.makeMove(move);
          }
        }
      }
      System.out.println("Puzzle #" + tc + ":");
      if (legalMove) {
        System.out.print(board);
      } else {
        System.out.println("This puzzle has no final configuration.");
      }
    }
	}
}
