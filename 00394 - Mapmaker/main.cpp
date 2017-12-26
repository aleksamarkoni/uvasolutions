#include <iostream>
#include <string>
#include <map>

using namespace std;

struct Array {
  string name;
  int D;
  long C[10];
  long C0;
};

int main() {
  int N, R;
  map<string, Array> arrays;
  string name;
  unsigned int B;
  unsigned int CD;
  int dimslow[10];
  int dimshigh[10];
  int a;
  int sum;
  Array cur;
  
  cin >> N >> R;
  
  while (N--) {
    cin >> cur.name >> B >> CD >> cur.D;
    for (int i = 0; i < cur.D; i++) {
      cin >> dimslow[i] >> dimshigh[i];
    }
    cur.C[cur.D - 1] = CD;
    sum = -CD * dimslow[cur.D - 1];
    for (int i = cur.D - 2; i >= 0; i--) {
      cur.C[i] = cur.C[i + 1] * (dimshigh[i + 1] - dimslow[i + 1] + 1);
      sum -= cur.C[i] * dimslow[i];
    }
    cur.C0 = B + sum;
    arrays[cur.name] = cur;
  }
  while (R--) {
    cin >> name;
    cur = arrays[name];
    sum = cur.C0;
    cout << cur.name << "[";
    for (int i = 0; i < cur.D; i++) {
      cin >> a;
      cout << a;
      if (i < cur.D - 1) {
        cout << ", ";
      }
      sum += cur.C[i] * a; 
    }
    cout << "] = " << sum << endl;
  }
}
