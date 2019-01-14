#include <iostream>
using namespace std;

int main() {
    int t;
    int totalPositions;
    int position;
    int emptyPosition;
    int initialPositions[20];
    cin >> t;
    while (t--) {
        for (int &initialPosition : initialPositions)
            initialPosition = 0;
        totalPositions = 0;
        while (true) {
            cin >> position;
            if (position == 99)
                break;
            initialPositions[position - 1] = 1;
            totalPositions++;
        }
        for (int i = 0; i < totalPositions; i++) {
            cin >> emptyPosition;
            emptyPosition--;
            if (initialPositions[emptyPosition] != 0) {

            }
        }
    }
    return 0;
}