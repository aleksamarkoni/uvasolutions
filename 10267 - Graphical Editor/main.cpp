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
        switch(c) {
            case 'I':
                cin >> n >> m;
                break;
            case 'L':
                cin >> x >> y >> color;
                mat[y][x] = color;
                break;
            case 'C':
                break;
            case 'S':
                cin >> fileName;
                cout << fileName << endl;
                //todo odstampati matricu
                break;
            case 'X':
                running = false;
                break;
        }
    }
    return 0;
}