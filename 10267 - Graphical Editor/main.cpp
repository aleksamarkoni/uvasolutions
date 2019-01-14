#include <iostream>
#include <fstream>

using namespace std;

int main() {
    std::ifstream in("/home/aleksamarkoni/gits/uvasolutions/10267 - Graphical Editor/input.txt");
    std::cin.rdbuf(in.rdbuf());
    char mat[250][250];
    char c;
    int m, n;
    int x, y;
    int y1, y2;
    int x1, x2;
    int red;
    int kolona;
    char color;
    string fileName;
    for (int i = 0; i < 250; i++) {
        for (int j = 0; j < 250; j++) {
            mat[i][j] = '0';
        }
    }
    bool running = true;
    while (running) {
        cin >> c;
        switch (c) {
            case 'I':
                cin >> m >> n;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = '0';
                    }
                }
                break;
            case 'L':
                cin >> x >> y >> color;
                mat[y][x] = color;
                break;
            case 'C':
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mat[i][j] = '0';
                    }
                }
                break;
            case 'V':
                cin >> kolona >> y1 >> y2 >> color;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == kolona && j >= y1 && j <= y2) {
                            mat[j - 1][kolona - 1] = color;
                        }
                    }
                }
                break;
            case 'H':
                cin >> x1 >> x2 >> red >> color;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i >= x1 && i <= x2 && j == red) {
                            mat[red - 1][i - 1] = color;
                        } else if (j <= x2 && j >= y2) {
                        }
                    }
                }
                break;
            case 'K':
                cin >> x1 >> y1 >> x2 >> y2 >> color;
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {

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
                //todo odstampati matricu
                break;
            case 'X':
                running = false;
                break;
        }
    }
    return 0;
}