//#include <iostream>
//using namespace std;
//
//int main() {
//    int t;
//    int totalPositions;
//    int position;
//    int emptyPosition;
//    int initialPositions[20];
//    cin >> t;
//    while (t--) {
//        for (int &initialPosition : initialPositions)
//            initialPosition = 0;
//        totalPositions = 0;
//        while (true) {
//            cin >> position;
//            if (position == 99)
//                break;
//            initialPositions[position - 1] = 1;
//            totalPositions++;
//        }
//        for (int i = 0; i < totalPositions; i++) {
//            cin >> emptyPosition;
//            emptyPosition--;
//            if (initialPositions[emptyPosition] != 0) {
//
//            }
//        }
//    }
//    return 0;
//}

#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

void sortFunc(vector<int> &orginal, vector<int> &copy) {
    int temp, i, j;
    long size = orginal.size();
    for (i = 0; i < size; i++) {
        for (j = 0; j < size - 1; j++) {
            if (orginal[j] > orginal[i]) {
                temp = orginal[i]; //swap them
                orginal[i] = orginal[j];
                orginal[j] = temp;
                temp = copy[i];
                copy[i] = copy[j];
                copy[j] = temp;
            }
        }
    }
}

int findNumber(int currentNum, vector<int> copy) {

    for (int i = copy.size() - 1; i > 0; i--) {
        if (copy[i] <= currentNum) {
            return i;
        }
    }
    return (int) copy.size() -1;
}

int main() {
    std::ifstream in("/Users/markonni/CLionProjects/00603 - Parking Lot/input.txt");
    std::cin.rdbuf(in.rdbuf());

    int numOfCases;
    cin >> numOfCases;
    cout << endl;
    vector<int> original;
    vector<int> copy;
    vector<int> currentPosition;
    vector<int> parkingMesta;

    int carPosition;

    while (true) {
        cin >> carPosition;
        if (carPosition == 99)
            break;
        original.push_back(carPosition);
    }

    copy = original;
    currentPosition = original;

    int emptySpot;
    while (cin >> emptySpot) {
        sortFunc(currentPosition, copy);

    }

    //Tests
    cout << findNumber(8, currentPosition) << endl;
    cout << findNumber(17, currentPosition) << endl;
    cout << findNumber(2, currentPosition) << endl;
    cout << findNumber(13, currentPosition) << endl;

    return 0;
}