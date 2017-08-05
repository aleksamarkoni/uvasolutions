#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Board {
public:
  char board[10][10];
  int cx, cy;
  bool mode;
  Board() {
    reset();
  }
  void reset() {
    clearScreen();
    setCursorPosition(0, 0);
    mode = true;
  }
  
  void clearScreen() {
    for (int i = 0; i < 10; i++)
      for (int j = 0; j < 10; j++)
        board[i][j] = ' ';
  }
  
  void print() {
    //cout << cx << " " << cy << endl;
    cout << '+';
    for (int i = 0; i < 10; i++)
      cout << '-';
    cout << '+' << endl;
    for (int i = 0; i < 10; i++) {
      cout << '|';
      for (int j = 0; j < 10; j++)
        cout << board[i][j];
      cout << '|' << endl;
    }
    cout << '+';
    for (int i = 0; i < 10; i++)
      cout << '-';
    cout << '+' << endl;
  }
  
  void writeChar(char c) {
    if (!mode) {
      for (int i = 9; i > cy; i--)
        board[cx][i] = board[cx][i-1];
    }
    board[cx][cy] = c;
    moveCursorRight();
  }
  
  void moveCursorRight() {
    cy = (cy<9)?cy+1:cy;
  }
  
  void moveCursorLeft() {
    cy = (cy>0)?cy-1:cy;
  }
  
  void moveCursorUp() {
    cx = (cx>0)?cx-1:cx;
  }
  
  void moveCursorDown() {
    cx = (cx<9)?cx+1:cx;
  }
  
  void moveCursorToBeginingOfLine() {
    cy = 0;
  }
  
  void clearLineAfterCursorPosition() {
    for (int i = cy; i < 10; i++)
      board[cx][i] = ' ';
  }
  
  void setCursorPosition(int x, int y) {
    //no need to check if x and y are valid
    cx = x;
    cy = y;
  }
  
  void enterInsertMode() {
    mode = false;
  }
  void enterOverwriteMode() {
    mode = true;
  }
};

int main() {
  Board board;
  string line;
  int lines;
  char c;
  int caseNumber = 1;
  while (true) {
    cin >> lines;
    getline(cin, line); //skin the newline
    if (lines == 0) break;
    board.reset();
    cout << "Case " << caseNumber++ << endl;
    for (int i = 0; i < lines; i++) {
      getline(cin, line);
      istringstream iss(line);
      while (iss >> std::noskipws >> c) {
        if (c == '^') {
          iss >> c;
          //cout << "^" << c << endl;
          switch (c) {
            case 'b':
              board.moveCursorToBeginingOfLine();
              break;
            case 'c':
              board.clearScreen();
              break;
            case 'd':
              board.moveCursorDown();
              break;
            case 'e':
              board.clearLineAfterCursorPosition();
              break;
            case 'h':
              board.setCursorPosition(0, 0);
              break;
            case 'i':
              board.enterInsertMode();
              break;
            case 'l':
              board.moveCursorLeft();
              break;
            case 'o':
              board.enterOverwriteMode();
              break;
            case 'r':
              board.moveCursorRight();
              break;
            case 'u':
              board.moveCursorUp();
              break;
            case '^':
              board.writeChar('^');
              break;
            default:
              //we can asume this is the last command, since the input is well formated
              int x = c - '0';
              iss >> c;
              int y = c - '0';
              board.setCursorPosition(x, y);
              break;
          }
        } else if (c != '\n' && c != '\r') {
          //cout << "writing char " << c << endl;
          board.writeChar(c);
        }
        //board.print();
      }
    }
    board.print();
  }
}
