#include <iostream>
#include <cmath>

using namespace std;

double triangle_area(double ax, double ay, double bx, double by, double cx, double cy) {
    return abs(ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2;
}

double distance(double x1, double y1, double x2, double y2) {
    return sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
}

double my_round(double x) {
    double t = x * 1000;
    if (t < 0) return ceil(t - 0.5) / 1000;
    return floor(t + 0.5) / 1000;
}

int main() {
    double xa, ya, xb, yb, xc, yc, xd, yd, xe, ye, xf, yf;
    double t_area, p_area, p_base, p_h, h_should_be, k, vector_a_c_x, vector_a_c_y, final_xh, final_yh, final_xg, final_yg;

    while (true) {
        cin >> xa >> ya >> xb >> yb >> xc >> yc >> xd >> yd >> xe >> ye >> xf >> yf;
        if (xa == 0.0f && ya == 0.0f && xb == 0.0f && yb == 0.0f && xc == 0.0f && yc == 0.0f && xd == 0.0f && yd == 0.0f && xe == 0.0f && ye == 0.0f && xf == 0.0f && yf == 0.0f)
            break;
        t_area = triangle_area(xd, yd, xe, ye, xf, yf) / 2;
        p_area = triangle_area(xa, ya, xb, yb, xc, yc);
        p_base = distance(xa, ya, xb, yb);
        p_h = p_area / p_base;
        h_should_be = t_area / p_base;
        k = h_should_be / p_h;
        vector_a_c_x = xc - xa;
        vector_a_c_y = yc - ya;
        final_xh = xa + vector_a_c_x * k;
        final_yh = ya + vector_a_c_y * k;
        final_xg = xb + vector_a_c_x * k;
        final_yg = yb + vector_a_c_y * k;
        cout.setf(ios::fixed,ios::floatfield);
        cout.precision(3);
        cout << my_round(final_xg) << " " << my_round(final_yg) << " " << my_round(final_xh) << " " << my_round(final_yh) << endl;
    }
}