#include <iostream>
#include <algorithm>

using namespace std;

/* Relly simple problem, possible cases are:
0) start square and end square are of diff color, no move
1) bishop did not move, moves 0
2) end position is on the same diagonal as the start position, moves 1
3) any other situation, moves 2
*/

int main() {
  int x, y, x1, y1;
  int TC, T, N;
  cin >> TC;
  while (TC--) {
    cin >> T;
    cin >> N;
    while (T--) {
      cin >> x >> y >> x1 >> y1;

      if (((x + y) % 2) != ((x1 + y1) % 2)) {
        cout << "no move" << endl;
        continue;
      }

      if (x == x1 && y == y1) {
        cout << 0 << endl;
        continue;
      }

      if (abs(x - x1) == abs(y - y1)) {
        cout << 1 << endl;
        continue;
      }

      cout << 2 << endl;
    }
  }
}
