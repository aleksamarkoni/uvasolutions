#include <iostream>

using namespace std;

int num_of_operands[] = {2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1 };
char opcode_names[][5] = {
  "ADD", "SUB", "MUL", "DIV", 
  "MOV", "BREQ", "BRLE", "BRLS", 
  "BRGE", "BRGR", "BRNE", "BR",
  "AND", "OR", "XOR", "NOT"
};

char mode_names[][4] = {
  "R", "$", "PC+", ""
};

void printOperand() {
  int i = 0;
  char c;
  unsigned int value = 0, convert;
  while (i < 4) {
    cin >> c;
    if (c == '\n' || c == '\r' || c == ' ')
      continue;
    convert = (c >= '0' && c <= '9')?c-'0':c-'A' + 10;
    if (i == 0) {
      cout << mode_names[convert >> 2];
      //convert &= 3; saves only first 2 bits
      value |= (convert & 3); // this will move first two bits from convert to value
    } else {
      value <<= 4;
      value |= convert;
    }
    i++;
  }
  cout << value;
}

int main() {
  unsigned char c;
  while (cin >> c) {
    if (c == '\n' || c == '\r' || c == ' ')
      continue;
    unsigned int opcode = (c >= '0' && c <= '9')?c-'0':c-'A' + 10;
    cout << opcode_names[opcode] << " ";
    for (int i = 0; i < num_of_operands[opcode]; i++) {
      if (i != 0)
        cout << ",";
      printOperand();
      }
    cout << endl;
  }
}
