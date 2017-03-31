#include <iostream>
#include <algorithm>

using namespace std;

int main() {
  char piece;
  int m, n;
  long TC;
  cin >> TC;
  while (TC--) {
    cin >> piece >> m >> n;
    switch(piece) {
      case 'r':
        // min numbers of row/cols
        cout << min(m, n) << endl;
        break;
      case 'k':
        /* this case is tricky but if you generalize nxn knight board problem:
        'i think the number is 32. because when you place first knight( horse!) on chess board, it will occupy 1 square and at least attack/control 2 squares. when second  one is placed, it will occupy one square and control at least 1 new square. so max possible no. is 32 .
you can do it by
placing knights on all white/black squares.'
        you get this solution*/
        cout << ((m*n+1)/2) << endl;
        break;
      case 'Q':
        // min number of rows/cols
        cout << min(m, n) << endl;
        break;
      case 'K':
        //you can put kings on all the black or white squares
        cout << (((m+1)/2) * ((n+1)/2)) << endl;
        break;
    }
  }
}
