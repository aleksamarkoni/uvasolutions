#include <iostream>
#include <string>

using namespace std;

int main() {
  int i = 0, t = 0, sp = 0;
  char ins, prev = 0;
  bool reg = false;
  string instruction;
  while (cin >> instruction) {
    if (t != 0)
      cout << endl;
    t++;
    reg = false;
    sp = 0;
    prev = 0;

    for (i = 0; i < instruction.size(); i++) {
      ins = instruction[i];
      switch(ins) {
        case '+':
          if (prev) {
            cout << "A " << prev << endl;
            prev = 0;
          } else {
            cout << "A $" << --sp << endl;
          }
          break;
        case '-':
          if (prev) {
            cout << "S " << prev << endl;
            prev = 0;
          } else {
            cout << "N" << endl;
            cout << "A $" << --sp << endl;
          }
          break;
        case '*':
          if (prev) {
            cout << "M " << prev << endl;
            prev = 0;
          } else {
            cout << "M $" << --sp << endl;
          }
          break;
        case '/':
          if (prev) {
            cout << "D " << prev << endl;
            prev = 0;
          } else {
            cout << "ST $" << sp++ << endl;
            cout << "L $" << sp - 2 << endl;
            cout << "D $" << --sp << endl;
            sp --;
          }
          break;
        case '@':
          if (prev) {
            if (reg)
              cout << "ST $" << sp++ << endl;
            cout << "L " << prev << endl;
            prev = 0;
            reg = true;
          }
          cout << "N" << endl;
          break;
        default:
          if (prev) {
            //if there is something in register, store on stack
            if (reg) {
              cout << "ST $" << sp++ << endl;
            }
            cout << "L " << prev << endl;
            //since we load, there is something in register
            reg = true;
          }
          prev = ins;
          break;
      }
    }
    if (instruction.size() < 2) {
      cout << "L " << instruction[0] << endl;
    }
  }
}
