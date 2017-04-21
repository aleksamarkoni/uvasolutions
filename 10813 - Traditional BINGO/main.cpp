#include <iostream>

using namespace std;

class mat {
public:
  int m, n;
  int **board;
  int *rows;
  int *cols;
  int *diag;
  mat(int m, int n) {
    this->m = m;
    this->n = n;
    board = new int*[m];
    for (int i = 0; i < m; i++) {
      board[i] = new int[n];
    }
    rows = new int[m];
    cols = new int[n];
    diag = new int[2];
    reset();
  }

  void set(int i, int j, int c) {
    board[i][j] = c;
  }

  bool checkNumber(int number) {
    int col = (number -1) / 15;
    int i = 0;
    bool found = false;
    for (i = 0; i < m; i++) {
      if (board[i][col] == number) {
        rows[i]--;
        cols[col]--;
        if (i == col)
          diag[0]--;
        if (i == (n-col-1))
          diag[1]--;
        found = true;
        board[i][col] = 0;
        break;
      }
    }
    if (found) {
      if (rows[i] == 0)
        return true;
      if (cols[col] == 0)
        return true;
      if (diag[0] == 0)
        return true;
      if (diag[1] == 0)
        return true;
    }
    return false;
  }

  bool isOnBoard(int i, int j) {
    return i >= 0 && i < m && j >= 0 && j < n;
  }

  void reset() {
    for (int i = 0; i < m; i++) {
      rows[i] = m;
      cols[i] = n;
      for (int j = 0; j < n; j++)
        board[i][j] = 0;
    }
    rows[m/2]--; // remove the guess for the center square
    cols[m/2]--;
    diag[0] = diag[1] = 4;
  }

  void print() {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        cout << board[i][j] << " ";
      }
      cout << endl;
    }
    cout << "rows: " << endl;
    for (int i = 0; i < m; i++)
      cout << rows[i] << " ";
    cout << endl;
    cout << "cols: " << endl;
    for (int i = 0; i < n; i++)
      cout << cols[i] << " ";
    cout << endl;
    cout << "diag: " << diag[0] << " " << diag[1] << endl;
  }

  ~mat() {
    for (int i = 0; i < m; i++) {
      delete [] board[i];
    }
    delete [] board;
    delete [] rows;
    delete [] cols;
    delete [] diag;
  }
};

int main() {
  int tc, m = 5, n = 5, number;
  int c;
  mat ticket(m, n);
  cin >> tc;
  while (tc--) {
    ticket.reset();
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        if (!(i == 2 && j == 2)) {
          cin >> c;
          ticket.set(i, j, c);
        }
      }
    bool winner = false;
    int n = 0;
    for (int i = 0; i < 75; i++) {
      cin >> number;
      if (!winner) {
        winner = ticket.checkNumber(number);
        if (winner) {
          n = i + 1;
        }
      }
      //ticket.print();
    }
    cout << "BINGO after " << n << " numbers announced" << endl;
  }
}
