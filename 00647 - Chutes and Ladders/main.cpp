#include <iostream>

using namespace std;

/* This is really a simple problem, but I overcomplicated it, because I thought
that solution should be the total number of players that can win the game.
That means when one player finishes by getting to square 100, game continues
for other players, until they all dont finish or until there is no more roools.

- After hours of struguling to see why first test case is giving 2 and not 1,
because only player number 2 can win the game, it got me after reading the
problem description one more time.
*/

class rools {
public:
  int size;
  int data[1001];
  void print() {
    cout << "rools: " << "(" << size << ") ";
    for (int i = 0; i < size; i++)
      cout << data[i] << " ";
    cout << endl;
  }
};

class players {
public:
  int size;
  int data[7];
  int status[7]; // 0 normal status, 1 game won, -1 skipping next move
  players() {
    for (int i = 0; i < 7; i++) {
      data[i] = 0;
      status[i] = 0;
    }
  }

  void print() {
    cout << "players: ";
    for (int i = 0; i < size; i++)
      cout << data[i] << ":" << status[i] << " ";
    cout << endl;
  }
};

class board {
public:
  int data[101];
  int special[101];
  board() {
    for (int i = 0; i < 101; i++)
      data[i] = i;
  }
  void print() {
    cout << "board: ";
    for (int i = 0; i < 101; i++)
      cout << i << ":" << data[i] << ":" << special[i] << " ";
    cout << endl;
  }
};

int main() {

  int currPlayer;

  int rool;
  rools r;
  r.size = 0;
  while (true) {
    cin >> rool;
    if (rool == 0)
      break;
    r.data[r.size++] = rool;
  }

  //r.print();

  while (true) {
    players p;
    cin >> p.size;
    if (p.size == 0)
      break;

    int start, end;
    board b;
    while (true) {
      cin >> start >> end;
      if (start == 0 && end == 0)
        break;
      b.data[start] = b.data[end];
    }

    int special;
    while (true) {
      cin >> special;
      if (special == 0)
        break;
      if (special < 0) {
        b.data[-special] = -1;
        b.special[-special] = special;
      } else {
        b.data[special] = -1;
        b.special[special] = special;
      }
    }

    //b.print();

    //Do the game
    int numOfWinners = 0;
    currPlayer = 0;
    int i = 0;
    while (i < r.size) {
      //check if everyone won the game


      bool newrool = false;
      int position = p.data[currPlayer];
      int newposition = position + r.data[i];
      //p.print();
      //cout << "r: " <<  r.data[i] << " cp: " << currPlayer << endl;
      while (true) {
        if (newposition > 100) {
          newposition = position;
          break;
        } else if (newposition == 100) {
          numOfWinners++;
          p.status[currPlayer] = 1;
          break;
        } else if (b.data[newposition] == -1) {
          if (b.special[newposition] < 0) {
            p.status[currPlayer] = -1;
            break;
          } else {
            newrool = true;
            break;
          }
        } else if (newposition != b.data[newposition]) {
          newposition = b.data[newposition];
        } else {
          break;
        }
      }
      i++;
      p.data[currPlayer] = newposition;

      if (numOfWinners == 1)
        break;

      if (!newrool) {
        currPlayer = (currPlayer+1) % p.size;
        while (true) {
          if (p.status[currPlayer] == 0)
            break;
          else if (p.status[currPlayer] == -1)
            p.status[currPlayer] = 0;
          currPlayer = (currPlayer + 1) % p.size; // skip all players that have to skip the move
        }
      }
    }
    cout << (currPlayer+1) << endl;
  }
}
