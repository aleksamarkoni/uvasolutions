#include <iostream>
#include <vector>
#include <algorithm>
//#include <fstream>

bool isAnagram(const int first[128], const int sec[128]);

using namespace std;

int main() {
    //std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/00454 - Anagrams/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    int T;
    int counts[100][128];
    cin >> T;
    cin.ignore();
    cin.ignore();
    while (T--) {
        string s;
        vector<string> words;
        while (getline(cin, s) && !s.empty())
            words.push_back(s);
        sort(words.begin(), words.end());
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < 128; j++) {
                counts[i][j] = 0;
            }
            for (char c: words[i]) {
                if (c != ' ')
                    counts[i][c]++;
            }
        }
        for (int i = 0; i < words.size(); ++i)
            for (int j = i + 1; j < words.size(); ++j) {
                if (isAnagram(counts[i], counts[j]))
                    cout << words[i] << " = " << words[j] << endl;
            }

        if (T)
            cout << endl;
    }
}

bool isAnagram(const int first[128], const int sec[128]) {
    for (int i = 0; i < 128; ++i)
        if (first[i] != sec[i])
            return false;
    return true;
}