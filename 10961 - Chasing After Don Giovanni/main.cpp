#include <iostream>
#include <fstream>
#include <vector>
#include "Location.h"
#include "Vector.h"
#include <QApplication>
#include <QLabel>

using namespace std;

int main(int argc, char **argv) {

    QApplication app(argc, argv);
    QLabel hello("Hello world!");

    hello.show();

    std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/10961 - Chasing After Don Giovanni/input.txt");
    std::cin.rdbuf(in.rdbuf());
    int T;
    int x, y;
    int numOfDgStops, numOfLpStops;
    int currDg, currLp;
    vector<Location> dgStops;
    vector<Location> lpStops;
    Location *nextDg, *nextLp;
    Location dg, lp;
    cin >> T;
    while (T--) {
        dgStops.clear();
        lpStops.clear();
        cin >> dg.x >> dg.y; //Don Giovanni position
        cin >> lp.x >> lp.y; //Leporello position
        cin >> numOfLpStops;
        for (int i = 0; i < numOfLpStops; i++) {
            cin >> x >> y;
            lpStops.emplace_back(x, y);
        }
        cin >> numOfDgStops;
        for (int i = 0; i < numOfDgStops; i++) {
            cin >> x >> y;
            dgStops.emplace_back(x, y);
        }
        currDg = 0;
        currLp = 0;
        bool safeRoute = true;
        while (true) {
            if (currDg >= numOfDgStops || currLp >= numOfLpStops)
                break;
            nextDg = &dgStops[currDg];
            nextLp = &lpStops[currLp];
            if (dg == *nextDg) {
                currDg++;
                continue;
            }
            if (lp == *nextLp) {
                currLp++;
                continue;
            }
            Vector dgDir(dg, *nextDg);
            Vector lpDir(lp, *nextLp);
            int d = min(dgDir.distance, lpDir.distance);
            dgDir.update(d);
            lpDir.update(d);
            //last route
            if (dgDir.intersects(lpDir) && !((lpDir.b == lpStops[lpStops.size() - 1]) && (lpDir.b == dgDir.b))) {
                safeRoute = false;
                break;
            }
            dg = dgDir.b;
            lp = lpDir.b;
        }
        cout << (safeRoute ? "Yes" : "No") << endl;
        if (T) cout << endl;
    }

    return app.exec();
}