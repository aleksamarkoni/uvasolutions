#include <iostream>

using namespace std;

void printArray(int a[], int len) {
  for (int i = 0; i < len; i++) {
    cout << a[i];
  }
  cout << endl;
}

int main() {
  int t, p, i, j, guess, len, cp, wp;
  int count, ccp, wwp;
  bool end;
  int code[5];
  int codeCopy[5];
  int guessCode[5];
  int guessCopy[5];
  cin >> t;
  while (t--) {
    cin >> guess >> cp >> wp;
    //cout << guess << " " << cp << " " << wp << endl;
    len = 0;
    p = guess;
    while(p != 0) { len++; p /= 10; }
    for (i = 0; i < len; i++) {
      code[i] = 1;
      guessCode[len - i - 1] = guess % 10;
      guess /= 10;
    }

    //printArray(guessCode, len);
    //printArray(code, len);

    code[len - 1] = 0;
    count = 0;
    while (true) {

      end = false;
      i = len - 1;
      while (true) {
        if (code[i] + 1 <= 9) {
          code[i]++;
          break;
        }
        else {
          code[i] = 1;
          i--;
        }
        if (i < 0) {
          //cout << "done" << endl;
          end = true;
          break;
        }
      }

      if (end) break;

      for (i = 0; i < len; i++) {
        guessCopy[i] = guessCode[i];
        codeCopy[i] = code[i];
      }

      //cout << "beggins" << endl;
      //printArray(codeCopy, len);
      //printArray(guessCopy, len);

      ccp = 0; wwp = 0;

      for (i = 0; i < len; i++) {
        if (codeCopy[i] == guessCopy[i]) {
          ccp++;
          codeCopy[i] = 0;
          guessCopy[i] = 0;
        }
      }

      for (i = 0; i < len; i++) {
        if (codeCopy[i] != 0) {
          for (j = 0; j < len; j++) {
            if (codeCopy[i] == guessCopy[j]) {
              wwp++;
              codeCopy[i] = 0;
              guessCopy[j] = 0;
              break;
            }
          }
        }
      }
      //cout << ccp << " " << wwp << endl;
      if (cp == ccp && wp == wwp) {
        count++;
        //printArray(guessCode, len);
        //printArray(code, len);
        //printArray(codeCopy, len);
        //printArray(guessCopy, len);
        //cout << cp << ccp << wp << wwp << endl;
        //cout << "over" << endl;
      }
    }
    cout << count << endl;
  }
}
