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

  public void caluculate() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == '.')
          doTheSquere(i, j);
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

}


class Main
{
    public static void main (String args[]) {
      Scanner scanner = new Scanner(System.in);
      int T = 0;
      while (scanner.hasNext()) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        if (n == 0 && m == 0)
          break;
        if (T != 0)
          System.out.println();
        T++;
        System.out.println("Field #" + T + ":");
        Board board = new Board(n, m, scanner);
        board.caluculate();
        System.out.print(board);
      }
    }
}
