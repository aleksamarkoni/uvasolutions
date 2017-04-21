#include <iostream>

using namespace std;

class mat {
public:
  int m, n;
  char **board;
  mat(int m, int n) {
    this->m = m;
    this->n = n;
    board = new char*[m];
    for (int i = 0; i < m; i++) {
      board[i] = new char[n];
    }
  }

  void set(int i, int j, char c) {
    board[i][j] = c;
  }

  void doTheMagic(int days) {
    char **pmat = new char*[m];
    for (int i = 0; i < m; i++) {
      pmat[i] = new char[n];
    }
    for (int i = 0; i < days; i++) {
      afterOneDay(&pmat);
    }
    for (int i = 0; i < m; i++) {
      delete [] pmat[i];
    }
    delete [] pmat;
  }

  void afterOneDay(char ***pmat) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        (*pmat)[i][j] = getLetter(i, j, board[i][j]);
      }
    }
    char **pom;
    pom = *pmat;
    *pmat = board;
    board = pom;
  }

  char getLetter(int i, int j, char c) {
    switch(c) {
      case 'R':
        return isNearBy(i, j, 'P')?'P':'R';
      case 'P':
        return isNearBy(i, j, 'S')?'S':'P';
      case 'S':
        return isNearBy(i, j, 'R')?'R':'S';
    }
  }

  bool isNearBy(int i , int j, char c) {
    if (isOnBoard(i-1, j) && board[i-1][j] == c)
      return true;
    if (isOnBoard(i+1, j) && board[i+1][j] == c)
      return true;
    if (isOnBoard(i, j-1) && board[i][j-1] == c)
      return true;
    if (isOnBoard(i, j+1) && board[i][j+1] == c)
      return true;
    return false;
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
  }

  ~mat() {
    for (int i = 0; i < m; i++) {
      delete [] board[i];
    }
    delete [] board;
  }
};

int main() {
  int tc, m, n, days;
  char c;
  cin >> tc;
  while (tc--) {
    cin >> m >> n >> days;
    mat b(m, n);
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        cin >> c;
        b.set(i, j, c);
      }
    b.doTheMagic(days);
    b.print();
    if (tc != 0)
      cout << endl;
  }
}
