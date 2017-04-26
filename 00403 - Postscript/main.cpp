#include <iostream>
#include <string>

using namespace std;

char c1[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .*";

char C5[][5][7] = {
    {".***..", "*...*.", "*****.", "*...*.", "*...*."},//A
    {"****..", "*...*.", "****..", "*...*.", "****.."},//B
    {".****.", "*...*.", "*.....", "*.....", ".****."},//C
    {"****..", "*...*.", "*...*.", "*...*.", "****.."},//D
    {"*****.", "*.....", "***...", "*.....", "*****."},//E
    {"*****.", "*.....", "***...", "*.....", "*....."},//F
    {".****.", "*.....", "*..**.", "*...*.", ".***.."},//G
    {"*...*.", "*...*.", "*****.", "*...*.", "*...*."},//H
    {"*****.", "..*...", "..*...", "..*...", "*****."},//I
    {"..***.", "...*..", "...*..", "*..*..", ".**..."},//J
    {"*...*.", "*..*..", "***...", "*..*..", "*...*."},//K
    {"*.....", "*.....", "*.....", "*.....", "*****."},//L
    {"*...*.", "**.**.", "*.*.*.", "*...*.", "*...*."},//M
    {"*...*.", "**..*.", "*.*.*.", "*..**.", "*...*."},//N
    {".***..", "*...*.", "*...*.", "*...*.", ".***.."},//O
    {"****..", "*...*.", "****..", "*.....", "*....."},//P
    {".***..", "*...*.", "*...*.", "*..**.", ".****."},//Q
    {"****..", "*...*.", "****..", "*..*..", "*...*."},//R
    {".****.", "*.....", ".***..", "....*.", "****.."},//S
    {"*****.", "*.*.*.", "..*...", "..*...", ".***.."},//T
    {"*...*.", "*...*.", "*...*.", "*...*.", ".***.."},//U
    {"*...*.", "*...*.", ".*.*..", ".*.*..", "..*..."},//V
    {"*...*.", "*...*.", "*.*.*.", "**.**.", "*...*."},//W
    {"*...*.", ".*.*..", "..*...", ".*.*..", "*...*."},//X
    {"*...*.", ".*.*..", "..*...", "..*...", "..*..."},//Y
    {"*****.", "...*..", "..*...", ".*....", "*****."},//Z
    {"......", "......", "......", "......", "......"},//BLANK
};

class Board {
public:
  int m, n;
  char **board;
  Board(int m, int n) {
    this->m = m;
    this->n = n;
    board = new char*[m];
    for (int i = 0; i < m; i++) {
      board[i] = new char[n];
    }
  }

  void write(string &command, string &font, string &word, int r, int c = 0) {
    int startCol = calculateStartColumn(command, font, word.length(), c);
    if (font == "C1") {
      writec1(r - 1, startCol, word);
    } else {
      writec5(r - 1, startCol, word);
    }
  }

  void writec1(int r, int c, string &word) {
    for (int i = 0; i < word.length(); i++) {
      if (word[i] != ' ' && isOnBoard(r, c+i))
        board[r][c+i] = word[i];
    }
  }

  void writec5(int r, int c, string &word) {
    for (int i = 0; i < word.length(); i++) {
      int pos = word[i] == ' '?26:(word[i] - 'A');
      writeC5Letter(r, c + i * 6, pos);
    }
  }

  void writeC5Letter(int r, int c, int pos) {
    char ch;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6; j++) {
        ch = C5[pos][i][j];
        if (ch != ' ' && ch != '.' && isOnBoard(r+i, c+j))
          board[r+i][c+j] = ch;
      }
    }
  }

  int calculateStartColumn(string &command, string &font, int len, int currentC) {
    if (command == ".L")
      return 0;
    if (command == ".R") {
      if (font == "C1")
        return 60 - len;
      else
        return 60 - len * 6; // for the last letter there is no empty colum at the end
    }
    if (command == ".C") {
      if (font == "C1")
        return 30 - len / 2;
      else
        return 30 - (len * 6 ) / 2;
    }
    return currentC - 1;
  }

  void clear() {
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
  }

  void set(int i, int j, char c) {
    board[i][j] = c;
  }

  bool isOnBoard(int i, int j) {
    return i >= 0 && i < m && j >= 0 && j < n;
  }

  void print() {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        cout << board[i][j];
      }
      cout << endl;
    }
    cout << endl;
    for (int i = 0; i < 60; i++)
      cout << '-';
    cout << endl;
  }

  ~Board() {
    for (int i = 0; i < m; i++) {
      delete [] board[i];
    }
    delete [] board;
  }
};

int main() {
  string command, font, word;
  int r, c, end;
  Board board(60, 60);
  board.clear();
  while(cin >> command) {
    if (command == ".P") {
       cin >> font >> r >> c;
       getline(cin, word);
       end = word.find_last_of('|');
       string trim_word = word.substr(2, end - 2);
       board.write(command, font, trim_word, r, c);
    }
    else if (command == ".L" || command == ".R" || command == ".C") {
      cin >> font >> r;
      getline(cin, word);
      end = word.find_last_of('|');
      string trim_word = word.substr(2, end - 2);
      board.write(command, font, trim_word, r);
    }
    else if (command == ".EOP") {
      board.print();
      cout << endl;
      board.clear();
    }
    else if (command == ".PRINT") {
      board.print();
      break;
    }
  }
}
