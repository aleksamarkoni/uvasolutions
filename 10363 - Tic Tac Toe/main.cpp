#include <iostream>

using namespace std;

bool winner(char mat[][3], char c) {
  if (mat[0][0] == c && mat[0][1] == c && mat[0][2] == c)
    return true;
  if (mat[1][0] == c && mat[1][1] == c && mat[1][2] == c)
    return true;
  if (mat[2][0] == c && mat[2][1] == c && mat[2][2] == c)
    return true;
  if (mat[0][0] == c && mat[1][0] == c && mat[2][0] == c)
    return true;
  if (mat[0][1] == c && mat[1][1] == c && mat[2][1] == c)
    return true;
  if (mat[0][2] == c && mat[1][2] == c && mat[2][2] == c)
    return true;
  if (mat[0][0] == c && mat[1][1] == c && mat[2][2] == c)
    return true;
  if (mat[2][0] == c && mat[1][1] == c && mat[0][2] == c)
    return true;
  return false;
}

void print(char mat[][3]) {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      cout << mat[i][j];
    }
    cout << endl;
  }
}

int main() {
  char mat[3][3];
  int tc, xnum, onum;
  bool xwin, owin;
  cin >> tc;
  while (tc--) {
    xnum = onum = 0;
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++) {
        cin >> mat[i][j];
        if (mat[i][j] == 'X')
          xnum++;
        if (mat[i][j] == 'O')
          onum++;
      }
    //cout << xnum << " " << onum << endl;
    xwin = winner(mat, 'X');
    owin = winner(mat, 'O');
    //cout << xwin << " " << owin << endl;
    bool result = false;
    if (xwin && owin)
      result = false;
    else if (xwin && (xnum == (onum + 1)))
      result = true;
    else if (xwin && (xnum != (onum + 1))) // need to add this, because of the xwin and xnum == onum
      result = false;
    else if (owin && (xnum == onum))
      result = true;
    else if (owin && (xnum != onum)) // need to add this, because of the owin and xnum == onum
      result = false;
    else if ((xnum == onum) || (xnum == (onum + 1)))
      result = true;
    if (result)
      cout << "yes" << endl;
    else
      cout << "no" << endl;
  }
}
