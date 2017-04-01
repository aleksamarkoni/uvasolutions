import java.io.*;
import java.util.*;

class Board {
  private char[][] board;

  public Board(String line) {
    board = new char[8][8];

    String rows[] = line.split("/");
    for (int i = 0; i < rows.length; i++) {
      String row = rows[i];
      int k = 0;
      for (int j = 0; j < row.length(); j++) {
        char c = row.charAt(j);
        if (Character.isDigit(c)) {
          int value = c - '0';
          while(value-- != 0) board[i][k++] = '.';
        } else {
          board[i][k++] = c;
        }
      }
    }
  }

  public Board(Board b) {
    board = new char[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++)
        board[i][j] = b.get(i,j);
    }
  }

  public char get(int i, int j) {
    return board[i][j];
  }

  public void set(int i, int j, char c) {
    board[i][j] = c;
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        stringBuilder.append(board[i][j]);
      }
      stringBuilder.append('\n');
    }
    return stringBuilder.toString();
  }

  public int calculateNumOfNonAttackedSquares() {
    Board helpBoard = new Board(this);
    //System.out.println(helpBoard);
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        switch(board[i][j]) {
          case 'p':
            checkPawn(i, j, 1, helpBoard);
            break;
          case 'P':
            checkPawn(i, j, -1, helpBoard);
            break;
          case 'n':
          case 'N':
            checkKnight(i, j, helpBoard);
            break;
          case 'b':
          case 'B':
            checkBishop(i, j, helpBoard);
            break;
          case 'r':
          case 'R':
            checkRook(i, j, helpBoard);
            break;
          case 'q':
          case 'Q':
            checkBishop(i, j, helpBoard);
            checkRook(i, j, helpBoard);
            break;
          case 'k':
          case 'K':
            checkKing(i, j, helpBoard);
            break;
        }
      }
    }
    //System.out.println(helpBoard);
    //calculate number of unchecked squares
    int unChecked = 0;
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        if (helpBoard.get(i, j) == '.')
          unChecked++;
    return unChecked;
  }

  private boolean isValid(int i, int j) {
    return i >= 0 && i < 8 && j >= 0 && j < 8;
  }

  private void doTheSquere(int i, int j, Board helpBoard) {
    if (isValid(i, j) && board[i][j] == '.')
      helpBoard.set(i, j, 'X');
  }

  private void checkPawn(int i, int j, int direction, Board helpBoard) {
    doTheSquere(i+direction, j+1, helpBoard);
    doTheSquere(i+direction, j-1, helpBoard);
  }

  private void checkKnight(int i, int j, Board helpBoard) {
    doTheSquere(i-2, j+1, helpBoard);
    doTheSquere(i-2, j-1, helpBoard);
    doTheSquere(i+2, j+1, helpBoard);
    doTheSquere(i+2, j-1, helpBoard);
    doTheSquere(i+1, j-2, helpBoard);
    doTheSquere(i+1, j+2, helpBoard);
    doTheSquere(i-1, j-2, helpBoard);
    doTheSquere(i-1, j+2, helpBoard);
  }

  private void checkBishop(int i, int j, Board helpBoard) {
    for (int k = 1; k < 8; k++) {
      if (!isValid(i+k, j+k) || board[i+k][j+k] != '.')
        break;
      doTheSquere(i+k, j+k, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i-k, j+k) || board[i-k][j+k] != '.')
        break;
      doTheSquere(i-k, j+k, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i+k, j-k) || board[i+k][j-k] != '.')
        break;
      doTheSquere(i+k, j-k, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i-k, j-k) || board[i-k][j-k] != '.')
        break;
      doTheSquere(i-k, j-k, helpBoard);
    }
  }

  private void checkRook(int i, int j, Board helpBoard) {
    for (int k = 1; k < 8; k++) {
      if (!isValid(i, j+k) || board[i][j+k] != '.')
        break;
      doTheSquere(i, j+k, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i-k, j) || board[i-k][j] != '.')
        break;
      doTheSquere(i-k, j, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i, j-k) || board[i][j-k] != '.')
        break;
      doTheSquere(i, j-k, helpBoard);
    }
    for (int k = 1; k < 8; k++) {
      if (!isValid(i+k, j) || board[i+k][j] != '.')
        break;
      doTheSquere(i+k, j, helpBoard);
    }
  }

  private void checkKing(int i, int j, Board helpBoard) {
    for (int k = -1; k <= 1; k++) {
      for (int l = -1; l <=1; l++) {
        doTheSquere(i+k, j+l, helpBoard);
      }
    }
  }

}


class Main
{
    public static void main (String args[]) {
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        Board board = new Board(line);
        System.out.println(board.calculateNumOfNonAttackedSquares());
      }
    }
}
