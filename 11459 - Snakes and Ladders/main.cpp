#include <iostream>

using namespace std;

int main() {
  int TC, a, b, c, start, end, p, move;
  bool gameOver;
  int board[101];
  int players[1000001];
  cin >> TC;
  while (TC--) {
    cin >> a >> b >> c;
    //reset game
    gameOver = false;
    //reset players positions
    for (int i = 0; i < a; i++)
      players[i] = 1;
    //reset player who is on the move
    p = 0;
    // reset board
    for (int i = 0; i < 101; i++)
      board[i] = i;
    //insert snakes and leaders
    for (int i = 0; i < b; i++) {
      cin >> start >> end;
      board[start] = end;
    }
    for (int i = 0; i < c; i++) {
      cin >> move;
      if (!gameOver) {
        if (players[p] + move >= 100) {
          players[p] = 100;
          gameOver = true;
        } else {
          players[p] = board[players[p] + move];
        }
        //this is a check to see if end of leader of tail of the snake is on number 100
        if (players[p] == 100) {
          gameOver = true;
        }
        p = (p+1) % a;
      }
    }
    for (int i = 0; i < a; i++)
      cout << "Position of player " << (i + 1) << " is " << players[i] << "." << endl;
  }
}
