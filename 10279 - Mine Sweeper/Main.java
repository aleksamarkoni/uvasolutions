import java.io.*;
import java.util.*;

class Board {
  int n, m;
  private char[][] board;

  public Board(int n, int m, Scanner scanner) {
    this.n = n;
    this.m = m;
    board = new char[n][m];

    for (int i = 0; i < n; i++) {
      String row = scanner.nextLine().trim();
      for (int j = 0; j < m; j++) {
        board[i][j] = row.charAt(j);
      }
    }
  }

  public void calculate(Board movesBoard) {
    boolean bombsTouched = false;
    for (int i = 0; i < movesBoard.getWidth(); i++) {
      for (int j = 0; j < movesBoard.getHeight(); j++) {
        if (movesBoard.get(i, j) == 'x') {
          if (board[i][j] != '*')
            doTheSquere(i, j);
          if (board[i][j] == '*')
            bombsTouched = true;
          }
        }
      }
    toogleBombs(bombsTouched);
  }

  private void toogleBombs(boolean hide) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == '*' && !hide)
          board[i][j] = '.';
      }
    }
  }

  private boolean isValid(int i, int j) {
    return i >= 0 && i < n && j >= 0 && j < m;
  }

  private void doTheSquere(int i, int j) {
    int bombs = 0;
    for (int k = -1; k <= 1; k++) {
      for (int l = -1; l <=1; l++) {
        if (isValid(i+k, j+l) && board[i+k][j+l] == '*')
          bombs++;
      }
    }
    board[i][j] = (char)(bombs + '0');
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        stringBuilder.append(board[i][j]);
      }
      stringBuilder.append('\n');
    }
    return stringBuilder.toString();
  }

  public int getWidth() {
    return n;
  }

  public int getHeight() {
    return m;
  }

  public char get(int i, int j) {
    return board[i][j];
  }

}


class Main
{
    public static void main (String args[]) {
      Scanner scanner = new Scanner(System.in);
      int T = Integer.parseInt(scanner.nextLine().trim());
      while (T-- != 0) {
        scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine().trim());
        Board minesBoard = new Board(n, n, scanner);
        //System.out.print(minesBoard);
        Board movesBoard = new Board(n, n, scanner);
        //System.out.print(movesBoard);
        minesBoard.calculate(movesBoard);
        System.out.print(minesBoard);
        if (T != 0) {
          System.out.println();
        }
      }
    }
}
