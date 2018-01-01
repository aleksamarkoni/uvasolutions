#include <iostream>
#include <vector>

using namespace std;

int main() {
  int T, N, L, ball;
  int total_value;
  int values[4];
  int ticket[5][5];
  /*
  Vector for drawn numbers, if the element on place i is 0 the num is not drawn
  and if it's different then 0, that means that the ball is drawn in that turn
  */
  vector<int> balls(76, 128);
  cin >> T;
  for (int t = 1; t <= T; t++) {
    cout << "Case " << t << ":" << endl;

    fill(balls.begin(), balls.end(), 128); // set all elements of vector to 128
    cin >> N >> L;

    for (int i = 1; i <= N; i++) {
      cin >> ball;
      balls[ball] = i;
    }

    for (int i = 0; i < 4; i++) {
      cin >> values[i];
    }

    while (L--) {

      total_value = 0;
      bool game_won = true;
      int won_after = 0;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          cin >> ticket[i][j];
          if (balls[ticket[i][j]] == 128) {
            game_won = false;
          }
        }
      }

      // check corners
      if (balls[ticket[0][0]] <= 35 && balls[ticket[0][4]] <= 35 &&
          balls[ticket[4][0]] <= 35 && balls[ticket[4][4]] <= 35) {
        total_value += values[0];
      }

      // check mid line
      bool won_min_line = true;
      for (int i = 0; i < 5; i++) {
        if (balls[ticket[2][i]] > 40) {
          won_min_line = false;
          break;
        }
      }
      if (won_min_line) {
        total_value += values[1];
      }

      // check diagonals
      bool won_diagonal = true;
      for (int i = 0; i < 5; i++) {
        if (balls[ticket[i][i]] > 45) {
          won_diagonal = false;
          break;
        }
        if (balls[ticket[i][4 - i]] > 45) {
          won_diagonal = false;
          break;
        }
      }
      if (won_diagonal) {
        total_value += values[2];
      }

      if (game_won) {
        total_value += values[3];
      }

      cout << total_value << endl;
    }

    if (t < T) {
      cout << endl;
    }
  }
}
