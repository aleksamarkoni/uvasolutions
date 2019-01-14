#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;


void promena(char (&mat)[250][250], int m, int n, int i, int j, char color, char newColor) {

    if (i >= 0 && i < m && j >= 0 && j < n && mat[i][j] == color) {
        mat[i][j] = newColor;
        promena(mat, m, n, i, j + 1, color, newColor);
        promena(mat, m, n, i, j - 1, color, newColor);
        promena(mat, m, n, i - 1, j, color, newColor);
        promena(mat, m, n, i + 1, j, color, newColor);
    }
}

int main() {
    //std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/10267 - Graphical Editor/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    char mat[250][250];
    char c;
    int m, n;
    int x, y;
    int y1, y2;
    int x1, x2;
    int i, j;
    int red;
    int kolona;
    char newColor;
    char color;
    string fileName;
    int from, to;
    for (int i = 0; i < 250; i++) {
        for (int j = 0; j < 250; j++) {
            mat[i][j] = 'O';
        }
    }
    bool running = true;
    while (running) {
        cin >> c;
        switch (c) {
            case 'I':
                cin >> n >> m;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = 'O';
                    }
                }
                break;
            case 'L':
                cin >> x >> y >> color;
                mat[y - 1][x - 1] = color;
                break;
            case 'C':
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = 'O';
                    }
                }
                break;
            case 'V':
                cin >> kolona >> y1 >> y2 >> color;
                if (y1 < y2) {
                    from = y1;
                    to = y2;
                } else {
                    from = y2;
                    to = y1;
                }
                for (int i = from; i <= to; i++) {
                    mat[i - 1][kolona - 1] = color;
                }
                break;
            case 'H':
                cin >> x1 >> x2 >> red >> color;
                if (x1 < x2) {
                    from = x1;
                    to = x2;
                } else {
                    from = x2;
                    to = x1;
                }
                for (int i = from; i <= to; i++) {
                    mat[red - 1][i - 1] = color;
                }
                break;
            case 'K':
                cin >> x1 >> y1 >> x2 >> y2 >> color;
                int tx, ly;
                int dx, ry;

                if (y1 > y2) {
                    ly = y2;
                    ry = y1;
                    if (x1 > x2) {
                        tx = x2;
                        dx = x1;
                    } else {
                        tx = x1;
                        dx = x2;
                    }
                } else {
                    ry = y2;
                    ly = y1;
                    if (x1 > x2) {
                        tx = x2;
                        dx = x1;
                    } else {
                        tx = x1;
                        dx = x2;
                    }
                }

                for (int i = tx; i <= dx; i++) {
                    for (int j = ly; j <= ry; j++) {
                        mat[j - 1][i - 1] = color;
                    }
                }
                break;
            case 'S':
                cin >> fileName;
                cout << fileName << endl;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        cout << mat[i][j];
                    }
                    cout << endl;
                }
                break;
            case 'F':
                cin >> j >> i >> newColor;
                if (mat[i - 1][j - 1] == newColor)
                    break;
                promena(mat, m, n, i - 1, j - 1, mat[i - 1][j - 1], newColor);
                break;
            case 'X':
                running = false;
                break;
        }
    }
    return 0;
}