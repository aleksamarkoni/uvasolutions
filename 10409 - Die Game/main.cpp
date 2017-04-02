#include <iostream>

using namespace std;

int main() {
  //We could simplify and use only 2 rows, because south is reverse of north
  // and ease is reverse of west, but that will complicate the logic
  int mat[4][6] = {
    {5, 1, 3, 4, 6, 2}, // north
    {3, 2, 6, 1, 5, 4}, // east
    {2, 6, 3, 4, 1, 5}, // south
    {4, 2, 1, 6, 5, 3} // west
  };
  int position[] = {1, 2, 3, 4, 5, 6};
  int newposition[] = {0, 0, 0, 0, 0, 0};
  int moves;
  string move;
  while (true) {
    cin >> moves;
    if (!moves)
      break;
    for (int i = 0; i < 6; i++)
      position[i] = i+1;
    while (moves--) {
      cin >> move;
      int row;
      if (move == "north") row = 0;
      if (move == "east") row = 1;
      if (move == "south") row = 2;
      if (move == "west") row = 3;
      for (int i = 0; i < 6; i++) {
        newposition[i] = position[mat[row][i]-1];
      }
      for (int i = 0; i < 6; i++) {
        position[i] = newposition[i];
      }
    }
    cout <<  position[0] << endl;
  }
}
