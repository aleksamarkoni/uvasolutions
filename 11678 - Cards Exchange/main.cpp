#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
  vector<int> alice_cards;
  int a, b;
  int card, prev, b_len, num_of_same_cards;
  while (true) {
    cin >> a >> b;
    if (a == 0 && b == 0)
      break;

    num_of_same_cards = 0;

    prev = -1;
    alice_cards.clear();
    for (int i = 0; i < a; i++) {
      cin >> card;
      if (card != prev) {
        alice_cards.push_back(card);
        prev = card;
      }
    }
    //cout << alice_cards.size() << endl;

    b_len = 0;
    prev = -1;
    for (int i = 0; i < b; i++) {
      cin >> card;
      if (card != prev) {
        b_len++;
        if (binary_search(alice_cards.begin(), alice_cards.end(), card))
          num_of_same_cards++;
        prev = card;
      }
    }
    //cout << b_len << endl;

    cout << min(b_len - num_of_same_cards,
      static_cast<int>(alice_cards.size()) - num_of_same_cards) << endl;
  }
}
