#include <iostream>
#include <string>

using namespace std;

#define WIDTH 8
#define HEIGHT 8

enum PLAYER {
  WHITE,
  BLACK
};

enum UNDER_CHECK {
  BLACK_KING,
  WHITE_KING,
  NO_KING
};

class Board {
public:
  char board[8][8];
  bool isEmpty() {
    for (int i = 0; i < WIDTH; i++)
      for (int j = 0; j < HEIGHT; j++)
        if (board[i][j] != '.')
          return false;
    return true;
  }

  char enemyKingChar(PLAYER player) {
    if (player == WHITE)
      return 'K';
    else
      return 'k';
  }

  bool checkKnight(int i, int j, PLAYER player) {
    char enemyKing = enemyKingChar(player);
    if (isValid(i-2, j+1) && board[i-2][j+1] == enemyKing)
      return true;
    if (isValid(i-2, j-1) && board[i-2][j-1] == enemyKing)
      return true;
    if (isValid(i+2, j+1) && board[i+2][j+1] == enemyKing)
      return true;
    if (isValid(i+2, j-1) && board[i+2][j-1] == enemyKing)
      return true;
    if (isValid(i+1, j-2) && board[i+1][j-2] == enemyKing)
      return true;
    if (isValid(i+1, j+2) && board[i+1][j+2] == enemyKing)
      return true;
    if (isValid(i-1, j-2) && board[i-1][j-2] == enemyKing)
      return true;
    if (isValid(i-1, j+2) && board[i-1][j+2] == enemyKing)
      return true;
    return false;
  }

  bool checkBishop(int i, int j, PLAYER player) {
    char enemyKing = enemyKingChar(player);
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i+k, j+k) && board[i+k][j+k] == enemyKing)
        return true;
      if (board[i+k][j+k] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i-k, j+k) && board[i-k][j+k] == enemyKing)
        return true;
      if (board[i-k][j+k] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i+k, j-k) && board[i+k][j-k] == enemyKing)
        return true;
      if (board[i+k][j-k] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i-k, j-k) && board[i-k][j-k] == enemyKing)
        return true;
      if (board[i-k][j-k] != '.')
        break;
    }
    return false;
  }

  bool checkRook(int i, int j, PLAYER player) {
    char enemyKing = enemyKingChar(player);
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i, j+k) && board[i][j+k] == enemyKing)
        return true;
      if (board[i][j+k] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i-k, j) && board[i-k][j] == enemyKing)
        return true;
      if (board[i-k][j] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i, j-k) && board[i][j-k] == enemyKing)
        return true;
      if (board[i][j-k] != '.')
        break;
    }
    for (int k = 1; k < WIDTH; k++) {
      if (isValid(i+k, j) && board[i+k][j] == enemyKing)
        return true;
      if (board[i+k][j] != '.')
        break;
    }
    return false;
  }

  bool checkPawn(int i, int j, PLAYER player) {
    char enemyKing = enemyKingChar(player);
    int pawnDirection = player == WHITE?1:-1;
    if (isValid(i+pawnDirection, j+1) && board[i+pawnDirection][j+1] == enemyKing)
      return true;
    if (isValid(i+pawnDirection, j-1) && board[i+pawnDirection][j-1] == enemyKing)
      return true;
    return false;
  }

  bool whiteUnderCheck() {
    PLAYER player = WHITE;
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        switch(board[i][j]) {
          case 'p':
            if (checkPawn(i, j, player))
              return true;
            break;
          case 'n':
            if (checkKnight(i, j, player))
              return true;
            break;
          case 'b':
            if (checkBishop(i, j, player))
              return true;
            break;
          case 'r':
            if (checkRook(i, j, player))
              return true;
            break;
          case 'q':
            if (checkBishop(i, j, player))
              return true;
            if (checkRook(i, j, player))
              return true;
          break;
        }
      }
    }
    return false;
  }

  bool blackUnderCheck() {
    PLAYER player = BLACK;
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        switch(board[i][j]) {
          case 'P':
            if (checkPawn(i, j, player))
              return true;
            break;
          case 'N':
            if (checkKnight(i, j, player))
              return true;
            break;
          case 'B':
            if (checkBishop(i, j, player))
              return true;
            break;
          case 'R':
            if (checkRook(i, j, player))
              return true;
            break;
          case 'Q':
            if (checkBishop(i, j, player))
              return true;
            if (checkRook(i, j, player))
              return true;
          break;
        }
      }
    }
    return false;
  }

  UNDER_CHECK whoIsUnderCheck() {
    if (whiteUnderCheck())
      return WHITE_KING;
    if (blackUnderCheck())
      return BLACK_KING;
    return NO_KING;
  }

  bool isValid(int i, int j) {
    return i >= 0 && i < 8 && j >= 0 && j < 8;
  }

  void clear() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        board[i][j] = '.';
      }
    }
  }
  friend ostream& operator<<(ostream& os, const Board& board);
  friend istream& operator>>(istream& in, Board& board);
};

ostream& operator<<(ostream& os, const Board& board) {
  for (int i = 0; i < WIDTH; i++) {
    for (int j = 0; j < HEIGHT; j++) {
      os << board.board[i][j];
    }
    os << endl;
  }
}

istream& operator>>(istream& in, Board& board) {
  string line;
  for (int i = 0; i < WIDTH; i++) {
    getline(in, line);
    for (int j = 0; j < HEIGHT; j++) {
      board.board[i][j] = line[j];
    }
  }
  getline(in, line);
}

int main() {
  Board board;
  int TC = 0;
  while (true) {
    cin >> board;
    if (board.isEmpty())
      break;
    TC++;
    UNDER_CHECK underCheck = board.whoIsUnderCheck();
    cout << "Game #" << TC << ": ";
    switch (underCheck) {
      case WHITE_KING:
        cout << "white king";
        break;
      case BLACK_KING:
        cout << "black king";
        break;
      case NO_KING:
        cout << "no king";
        break;
    }
    cout << " is in check." << endl;
    board.clear();
  }
}
