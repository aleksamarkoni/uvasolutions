import java.util.Scanner;

class Board {
  public int m, n;
  char map[][];

  public Board(int m, int n) {
    this.m = m;
    this.n = n;
    map = new char[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = '-';
  }

  public Board(Board board) {
    this.m = board.m;
    this.n = board.n;
    map = new char[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        map[i][j] = board.getItem(i, j);
  }

  public char getItem(int x, int y) {
    return map[x][y];
  }

  public void setItem(int x, int y, char item) {
    //this can be really strange, need to test it more
    map[x][y] = item;
  }

  public boolean makeMove(int i, int j, char player) {
    if (!isThePositionLegal(i, j, player))
      return false;
    char newMap[][] = new char[m][n];
    for (int k = 0; k < m; k++)
      for (int h = 0; h < n; h++)
        newMap[k][h] = map[k][h];
    for (int dirx = -1; dirx <= 1; dirx++)
      for (int diry = -1; diry <= 1; diry++)
        if (checkDirection(i, j, dirx, diry, player)) {
          for (int k = 1; k < 7; k++) {
            int x = i + dirx * k;
            int y = j + diry * k;
            if (map[x][y] == player)
              break;
            newMap[x][y] = player;
          }
        }
    newMap[i][j] = player;
    this.map = newMap;
    return true;
  }

  public void printStatus() {
    int black = 0, white = 0;
    for (int k = 0; k < m; k++)
      for (int h = 0; h < n; h++)
        if (map[k][h] == 'B')
          black++;
        else if (map[k][h] == 'W')
          white++;
    System.out.printf("Black -%3d White -%3d\n",black, white);
  }

  public void listPossibleMoves(char player) {
    StringBuilder builder = new StringBuilder();
    boolean legalMoveFound = false;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (isThePositionLegal(i, j, player)) {
          if (!legalMoveFound) {
            legalMoveFound = true;
          } else {
            builder.append(' ');
          }
          builder.append("(" + (i + 1) + "," + (j + 1) + ")");
        }
      }
    }
    if (legalMoveFound) {
      System.out.println(builder.toString());
    } else {
      System.out.println("No legal move.");
    }
  }

  public boolean isThePositionLegal(int i, int j, char player) {
    if (!isOnTheBoard(i, j) || map[i][j] != '-')
      return false;
    //System.out.println("is position legal " + i + " " + j);
    //System.out.println(this);
    for (int dirx = -1; dirx <= 1; dirx++)
      for (int diry = -1; diry <= 1; diry++) {
        //we are checking for dirx = 0 and diry = 0, but that will return correct result
        //System.out.println("dir " + dirx + " " + diry);
        if (checkDirection(i, j, dirx, diry, player))
          return true;
        }
    return false;
  }

  private boolean checkDirection(int i, int j, int dirx, int diry, char player) {
    int bracketNum = 0;
    char enemyPlayer = (player=='B')?'W':'B';
    int x, y;
    for (int k = 1; k < 7; k++) {
      x = i + dirx * k;
      y = j + diry * k;
      if (!isOnTheBoard(x, y) || map[x][y] == '-') {
        return false;
      }
      if (map[x][y] == enemyPlayer) {
        bracketNum++;
        }
      if (map[x][y] == player) {
        return bracketNum != 0;
      }
    }
    return false;
  }

  private boolean isOnTheBoard(int i, int j) {
    return i >= 0 && j >= 0 && i < m && j < n;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        builder.append(map[i][j]);
      }
      builder.append("\n");
    }
    return builder.toString();
  }
}

public class Main {

	public static void main(String[] args) throws Exception {
		//Scanner in = new Scanner (new FileReader(new File("proba.txt")));
		Scanner in = new Scanner(System.in);
    String line;
    int tc = Integer.parseInt(in.nextLine());
    int m = 8, n = 8;
    while (tc-- != 0) {
      Board board = new Board(m, n);
      for (int i = 0; i < m; i++) {
        line = in.nextLine().trim();
        for (int j = 0; j < n; j++) {
          char c = line.charAt(j);
          board.setItem(i, j, c);
        }
      }
      char curPlayer = in.nextLine().trim().charAt(0);
      boolean gameOver = false;
      while (!gameOver) {
        //System.out.println(board);
        line = in.nextLine().trim();
        switch (line.charAt(0)) {
          case 'L':
            board.listPossibleMoves(curPlayer);
            break;
          case 'Q':
            System.out.print(board);
            gameOver = true;
            break;
          case 'M':
            int i = (int)(line.charAt(1) - '0');
            int j = (int)(line.charAt(2) - '0');
            if (!board.makeMove(i - 1, j - 1, curPlayer)) {
              curPlayer = (curPlayer=='B')?'W':'B';
              board.makeMove(i - 1, j - 1, curPlayer);
            }
            board.printStatus();
            curPlayer = (curPlayer=='B')?'W':'B';
            break;
        }
      }
      if (tc != 0)
        System.out.println();
    }
	}
}
