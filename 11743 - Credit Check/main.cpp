#include <iostream>
#include <string>

using namespace std;

int main() {
  int tc, i, s, d;
  char c;
  cin >> tc;
  while (tc--) {
    i = 0;
    s = 0;
    while (i < 16) {
      cin >> c;
      if (c == ' ')
        continue;
      if (i % 2 == 1)
        s += c - '0';
      else {
        d = (c - '0') * 2;
        s += d % 10;
        s += d / 10;
      }
      i++;
    }
    if (s % 10 == 0)
      cout << "Valid" << endl;
    else
      cout << "Invalid" << endl;
    cin.ignore();
  }
}
