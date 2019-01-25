#include <iostream>
//#include <fstream>

void process(char grid[64][64], int i, int j, char c, int L, int C, int &minx, int &maxx, int &miny, int &maxy, int &partCount);

using namespace std;

int main() {
    //std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/00830 - Shark/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    int T;
    char grid[64][64];
    string input;
    int L, C;
    int minx, maxx, miny, maxy, partCount, w, h;
    int sardines, mackerels, salmons, turtles, groupers, dolphins, sharks, whales;
    cin >> T;
    while (T--) {
        cin >> L >> C;
        cin.ignore(100, '\n');
        for (int i = 0; i < L; i++) {
            getline(cin, input);
            for (int j = 0; j < input.size(); j++) {
                grid[i][j] = input[j];
            }
        }

        sardines = 0; mackerels = 0, salmons = 0, turtles = 0, groupers = 0, dolphins = 0, sharks = 0, whales = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] >= 'a' && grid[i][j] <= 'z') {
                    minx = 100; maxx = -1; miny = 100; maxy = -1; partCount = 0;
                    process(grid, i, j, grid[i][j], L, C, minx, maxx, miny, maxy, partCount);
                    w = maxy - miny + 1;
                    h = maxx - minx + 1;
                    if (w == 1 && h == 1) {
                        sardines++;
                    }
                    if ((w == 1 && h == 2) || (w == 2 && h == 1)) {
                        mackerels++;
                    }
                    if ((w == 1 && h > 2) || (w > 2 && h == 1)) {
                        salmons++;
                    }
                    if (w > 1 && h > 1 && w == h) {
                        turtles++;
                    }
                    if ((w == 2 && h > 2) || (w > 2 && h == 2)) {
                        groupers++;
                    }
                    if ((w == 3 && h > 3) || (w > 3 && h == 3)) {
                        (w * h == partCount) ? dolphins++ : sharks++;
                    }
                    if ((w == 4 && h > 4) || (w > 4 && h == 4)) {
                        whales++;
                    }
                }
            }
        }
        cout << sardines << ' ' << mackerels << ' ' << salmons << ' ' << groupers << ' ' << turtles << ' '
            << dolphins << ' ' << whales << ' ' << sharks << endl;
        if (T) {
            cout << endl;
        }
    }
    return 0;
}

void process(char grid[64][64], int i, int j, char c, int L, int C, int &minx, int &maxx, int &miny, int &maxy, int &partCount) {
    if (i >=0 && i < L && j >= 0 && j < C && grid[i][j] == c) {
        partCount++;
        grid[i][j] = '.';
        minx = min(minx, i);
        maxx = max(maxx, i);
        miny = min(miny, j);
        maxy = max(maxy, j);
        process(grid, i + 1, j, c, L, C, minx, maxx, miny, maxy, partCount);
        process(grid, i - 1, j, c, L, C, minx, maxx, miny, maxy, partCount);
        process(grid, i, j + 1, c, L, C, minx, maxx, miny, maxy, partCount);
        process(grid, i, j - 1, c, L, C, minx, maxx, miny, maxy, partCount);
    }
}