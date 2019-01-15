#include <iostream>
//#include <fstream>

using namespace std;

enum State {
    ACTIVE, INIACTIVE, BOOTING_UP
};

class MC {
private:
    int iniactiveChangeCount;
public:
    int getIniactiveChangeCount() const {
        return iniactiveChangeCount;
    }

private:
    int i,k;
    State s;
    int nextStateChange;
public:
    MC(int i, int k): i(i), k(k), s(ACTIVE), nextStateChange(i), iniactiveChangeCount(0) {}
    bool calculateEverything(int it) {
        if (s == ACTIVE) {
            if (it < nextStateChange) {
                nextStateChange = it + i;
                return true;
            } else {
                iniactiveChangeCount++;
                s = BOOTING_UP;
                nextStateChange = it + k;
                return true;
            }
        } else if (s == BOOTING_UP) {
            if (it < nextStateChange) {
                return false;
            } else if (it < (nextStateChange + i)) {
                s = ACTIVE;
                nextStateChange = it + i;
                return true;
            } else {
                iniactiveChangeCount++;
                s = BOOTING_UP;
                nextStateChange = it + k;
                return true;
            }
        } else {
            s = BOOTING_UP;
            nextStateChange = it + k;
            return true;
        }
    }
};

int main() {
    //std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/11717 - Energy Saving Microcontroller/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    int t;
    cin >> t;
    int n, i, k;
    int it; // instruction time
    int miss, processed;
    for (int test = 1; test <= t; test++) {
        cin >> n >> i >> k;
        miss = 0; processed = 0;
        MC mc = MC(i, k);
        while (n--) {
            cin >> it;
            mc.calculateEverything(it) ? processed++ : miss++;
        }
        cout << "Case " << test << ": " << mc.getIniactiveChangeCount() << " " << miss << endl;
    }
}