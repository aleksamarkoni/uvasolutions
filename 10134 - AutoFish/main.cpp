#include <iostream>
#include <cstring>
//#include <fstream>

using namespace std;

int main() {
    //std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/10134 - AutoFish/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    string str;
    int t;
    cin >> t;
    getline(cin, str);
    getline(cin, str);
    while (t--) {
        int totalFishCatched = 0;
        int baitCount = 0;
        int commandFromLastCatch = 0;
        bool isFirstCatch = true;
        int fishCommandsAfterLastCatch = 0;
        while (getline(cin, str)) {
            if (str.length() == 0)
                break;
            if (str == "fish") {
                if (baitCount > 1) {
                    if (isFirstCatch || (commandFromLastCatch >= 6 && fishCommandsAfterLastCatch >= 2)) {
                        isFirstCatch = false;
                        baitCount -= 2;
                        commandFromLastCatch = 0;
                        fishCommandsAfterLastCatch = 0;
                        totalFishCatched++;
                    } else {
                        fishCommandsAfterLastCatch++;
                        commandFromLastCatch++;
                    }
                } else {
                    commandFromLastCatch++;
                }
            } else if (str == "bait") {
                if (baitCount < 6) {
                    baitCount++;
                }
                commandFromLastCatch++;
            } else if (str == "lunch") {
                commandFromLastCatch++;
            }
        }
        cout << totalFishCatched << endl;
        if (t > 0) {
            cout << endl;
        }
    }
    return 0;
}