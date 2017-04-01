#include <iostream>
#include <algorithm>

using namespace std;

/* Relly simple problem, possible cases are:
1) queen did not move, moves 0
2) end position is on the same row/colum as the start position, moves 1
3) end position is on the same diagonal as the start position, moves 1
4) any other situation, moves 2
*/

int main() {
  int x, y, x1, y1;
  while (true) {
    cin >> x >> y >> x1 >> y1;
    if (!x && !y && !x1 && !y1)
      break;
    if (x == x1 && y == y1) {
      cout << 0 << endl;
      continue;
    }
    if (x == x1 || y == y1) {
      cout << 1 << endl;
      continue;
    }

    if (abs(x - x1) == abs(y - y1)) {
      cout << 1 << endl;
      continue;
    }
    cout << 2 << endl;
  }
}
