#include <iostream>
#include <cstdlib>

using namespace std;

int main() {
  int n, b, ball;
  int balls[100];
  bool canBePulled[100];
  while (true) {
    cin >> n >> b;
    if (!n && !b) break;
    for (int i = 0; i < b; i++) {
      cin >> ball;
      balls[i] = ball;
    }
    for (int i = 0; i < n + 1; i++) {
      canBePulled[i] = false;
    }

    for (int i = 0; i < b; i++) {
      for (int j = 0; j < b; j++) {
        int d = abs(balls[i] - balls[j]);
        canBePulled[d] = true;
      }
    }
    bool canCoverAllBalls = true;
    for (int i = 0; i < n + 1; i++) {
      if (canBePulled[i] == false) {
        canCoverAllBalls = false;
        break;
      }
    }
    char res = canCoverAllBalls?'Y':'N';
    cout << res << endl;
  }
}
