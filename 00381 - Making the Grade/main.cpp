#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;

int main() {
    int n = 0, s, t, min, mini, sum, grade, gradesum;
    double avr[31], adjavr[31], mean, diff, sdsum, sd, tsum;
    int scores[31][14];
    int grades[31];
    cout << "MAKING THE GRADE OUTPUT" << endl;
    cin >> n;
    while (n--) {
        cin >> s >> t;
        sdsum = 0;
        tsum = 0;
        for (int i = 0; i < s; i++) {
            min = 101, sum = 0;
            mini = -1;
            for (int j = 0; j < t + 2; j++) {
                cin >> grade;
                if (j < t) {
                    if (grade < min) {
                        min = grade;
                        mini = j;
                    }
                    sum += grade;
                }
                scores[i][j] = grade;
            }
            if (t > 2) {
                avr[i] = (sum - min) / (t - 1.0);
                int temp = scores[i][0];
                scores[i][0] = scores[i][mini];
                scores[i][mini] = temp;
            } else {
                avr[i] = (double) sum / t;
            }
            tsum += avr[i];
            adjavr[i] = avr[i] + 3.0 * (int) (scores[i][t] / 2);
        }
        mean = (double) tsum / s;

        for (int i = 0; i < s; i++) {
            diff = avr[i] - mean;
            sdsum += diff * diff;
        }
        sd = sqrt(sdsum / s);
        if (sd < 1) {
            sd = 1;
        }
        gradesum = 0;
        for (int i = 0; i < s; i++) {
            if (adjavr[i] >= (mean + sd)) grades[i] = 4;
            else if (adjavr[i] >= mean) grades[i] = 3;
            else if (adjavr[i] >= mean - sd) grades[i] = 2;
            else grades[i] = 1;
            if (scores[i][t + 1] > 0) {
                grades[i] -= scores[i][t + 1] / 4;
            } else {
                grades[i]++;
            }
            if (grades[i] > 4) grades[i] = 4;
            if (grades[i] < 0) grades[i] = 0;
            gradesum += grades[i];
        }
        cout << fixed << setprecision(1) << ((double) gradesum / s) << endl;
//        for (int i = 0; i < s; i++) {
//            for (int j = 0; j < t + 2; j++) {
//                cout << scores[i][j] << " ";
//            }
//            cout << avr[i] << " " << grades[i] << endl;
//        }
//        cout << mean << " " << sd << endl;
    }
    cout << "END OF OUTPUT" << endl;
    return 0;
}