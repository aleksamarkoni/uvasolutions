#include <iostream> 
#include <sstream>
#include <vector>
#include <string>

using namespace std;

int main() {
  string line;
  vector<int> indexs;
  vector<string> values;
  vector<string> res;
  int n;
  string v;
  getline(cin, line);
  int t = stoi(line);
  while (t--) {
    indexs.clear();
    values.clear();
    res.clear();
    getline(cin, line);
    getline(cin, line);
    istringstream iss(line);
    while (iss >> n) {
      indexs.push_back(n);
    }
    getline(cin, line);
    istringstream vss(line);
    while (vss >> v) {
      values.push_back(v);
    }
    
    res.insert(res.begin(), indexs.size(), "");
    for (int i = 0; i < indexs.size(); i++) {
      res[indexs[i] - 1] = values[i];
    }
    for (auto d : res) {
      cout << d << endl;
    }
    if (t > 0) {
      cout << endl;
    }
  }
}
