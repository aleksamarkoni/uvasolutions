#include <iostream>
#include <set>

using namespace std;

int main() {
  int n;
  int x, y;
  char move;
  bool winner;
  int playerWin;
  int playerWinMove;
  while(true) {
    cin >> n;
    if (n == 0) break;

    string grid(n * n, '0');
    string grid90(n * n, '0');
    string grid180(n * n, '0');
    string grid270(n * n, '0');
    winner = false;
    set<string> movesSoFar;

    for (int i = 0; i < 2 * n; i++) {
      cin >> x >> y >> move;
      if (winner) continue;
      --x, --y;
      char m = (move == '+')? '1' : '0';
      grid[x * n + y] = m;


      if (movesSoFar.find(grid) != movesSoFar.end()) {
        winner = true;
        playerWin = 2 - (i % 2);
        playerWinMove = i + 1;
        continue;
      }

      grid90[y * n + (n - x - 1)] = m;
      grid180[(n - x - 1) * n + (n - y - 1)] = m;
      grid270[(n - y - 1) * n + x] = m;
      movesSoFar.insert(grid);
      movesSoFar.insert(grid90);
      movesSoFar.insert(grid180);
      movesSoFar.insert(grid270);
    }
    if (winner)
      cout << "Player " << playerWin << " wins on move " << playerWinMove << endl;
    else
      cout << "Draw" << endl;
  }
}
