#include <iostream>
#include <utility>
#include <fstream>
#include <iomanip>

using namespace std;

void printMatrix(int **mat, int rows, int cols);

pair<int, int> leftPosition(pair<int, int> rd);

pair<int, int> rightPosition(pair<int, int> rd);

void printWithRobotPosition(int **matrix, int rows, int cols, pair<int, int> rp);

pair<int, int> EAST(0, 1);
pair<int, int> NORTH(-1, 0);
pair<int, int> SOUTH(1, 0);
pair<int, int> WEST(0, -1);

std::pair<int, int> operator+(const std::pair<int, int> &x, const std::pair<int, int> &y) {
    return std::make_pair(x.first + y.first, x.second + y.second);
}

int main() {
    //std::ifstream in("/home/aleksamarkoni/CLionProjects/untitled/input.txt");
    //std::cin.rdbuf(in.rdbuf());
    int rows, cols;
    int **matrix;
    int **resmat;
    pair<int, int> start;
    pair<int, int> rp; //robot position
    pair<int, int> rd; //robot direction
    pair<int, int> sum;
    pair<int, int> rightDirection;
    int res[5];
    char square;
    while (true) {
        cin >> rows >> cols;
        if (rows == 0 && cols == 0) break;
        rows += 2; //to include surrounding wall
        cols += 2; //to include surrounding wall
        matrix = new int *[rows];
        resmat = new int *[rows];
        for (int i = 0; i < rows; ++i) {
            matrix[i] = new int[cols];
            resmat[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 1;
                resmat[i][j] = 0;
            }
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                cin >> square;
                matrix[i][j] = (square == '0') ? 0 : 1;
            }
        }

        for (int i = 0; i < 5; i++) {
            res[i] = 0;
        }

        //printMatrix(matrix, rows, cols);

        start.first = rows - 2;
        start.second = 1;
        rp = start;
        rd = EAST;
        do {
            //printWithRobotPosition(matrix, rows, cols, rp);
            while (true) {
                sum = rp + rd;
                if (matrix[sum.first][sum.second] == 0) {
                    resmat[rp.first][rp.second]++;
                    rp = rp + rd;
                    rightDirection = rightPosition(rd);
                    sum = rp + rightDirection;
                    if (matrix[sum.first][sum.second] == 0) {
                        rd = rightDirection;
                    }
                    break;
                }
                rd = leftPosition(rd);
            }
        } while (rp != start);

        //printMatrix(resmat, rows, cols);

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (matrix[i][j] != 1) {
                    int numOfVisits = resmat[i][j];
                    if (numOfVisits >= 0 && numOfVisits < 5) {
                        res[numOfVisits]++;
                    }
                }
            }
        }

        for (int re : res) {
            cout << right << setw(3) << re;
        }

        cout << endl;

        for (int i = 0; i < rows; ++i) {
            delete[] matrix[i];
            delete[] resmat[i];
        }
        delete[] matrix;
        delete[] resmat;
    }
}

void printMatrix(int **matrix, int rows, int cols) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            cout << matrix[i][j];
        }
        cout << endl;
    }
}

void printWithRobotPosition(int **matrix, int rows, int cols, pair<int, int> rp) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (rp.first == i && rp.second == j) {
                cout << 'x';
            } else {
                cout << matrix[i][j];
            }
        }
        cout << endl;
    }
}

pair<int, int> leftPosition(pair<int, int> rd) {
    if (rd == EAST) {
        return NORTH;
    }
    if (rd == NORTH) {
        return WEST;
    }
    if (rd == WEST) {
        return SOUTH;
    }
    if (rd == SOUTH) {
        return EAST;
    }
    return NORTH;
}

pair<int, int> rightPosition(pair<int, int> rd) {
    if (rd == EAST) {
        return SOUTH;
    }
    if (rd == NORTH) {
        return EAST;
    }
    if (rd == WEST) {
        return NORTH;
    }
    if (rd == SOUTH) {
        return WEST;
    }
    return NORTH;
}
