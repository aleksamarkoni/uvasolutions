#include <iostream>
#include <sstream>
#include <string>

using namespace std;

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

  void clear() {
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        board[i][j] = ' ';
      }
  }

  void doTheSimulation(int numYears) {
    char **tboard = new char*[m];
    for (int i = 0; i < m; i++) {
      tboard[i] = new char[n];
    }
    print();
    int bs;
    for (int y = 1; y < numYears; y++) {
      //clears the tboard
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
          tboard[i][j] = ' ';

      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          bs = numNeighbours(i, j);

          if (board[i][j] == ' ') {
            if (bs == 3)
              tboard[i][j] = 'O';
            else
              tboard[i][j] = ' ';
          } else {
            if (bs < 2)
              tboard[i][j] = ' ';
					  else if (bs == 2 || bs == 3)
						  tboard[i][j] = 'O';
					  else if (bs > 3)
						  tboard[i][j] = ' ';
          }
        }
      }
      char **p = tboard;
      tboard = board;
      board = p;

      print();
    }

    for (int i = 0; i < m; i++) {
      delete [] tboard[i];
    }
    delete [] tboard;
  }

  int numNeighbours(int i, int j) {
	   int br = 0;
	   for (int k = -1; k <= 1; k++) {
       for (int t = -1; t <= 1; t++) {
         if (isOnBoard(i+k, j+t) && board[i+k][j+t] == 'O')
          br++;
      }
    }
	  if (board[i][j] == 'O')
		    br--;
	  return br;
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
    for (int i = 0; i < n; i++)
      cout << '*';
    cout << endl;
  }

  void print(char **board) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        cout << board[i][j];
      }
      cout << endl;
    }
    for (int i = 0; i < n; i++)
      cout << '*';
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
  int tc, numYears, x, y;
  string line;
  Board board(20, 20);
  getline(cin, line);
  stringstream ss(line);
  ss >> tc;
  getline(cin, line);
  while (tc--) {
    getline(cin, line);
    stringstream ss(line);
    ss >> numYears;
    board.clear();
    while (getline(cin, line) && !line.empty() && line != "\r") {
        stringstream ss(line);
        ss >> x >> y;
        board.set(--x, --y, 'O');
    }
    for (int i = 0; i < 20; i++)
      cout << '*';
    cout << endl;
    board.doTheSimulation(numYears);
    if (tc > 0)
      cout << endl;
  }
}
