#include <iostream>
#include <fstream>
#include <vector>

enum Direction {
    NORTH, WEST, SOUTH, EAST
};

class Location {
public:
    int x;
    int y;

    Location() : x(0), y(0) {}

    Location(int x, int y) : x(x), y(y) {}

    bool operator==(const Location &l) {
        return x == l.x && y == l.y;
    }
    bool operator==(const Location &l) const {
        return x == l.x && y == l.y;
    }
};

class Vector {
public:
    Location a;
    Location b;
    Direction direction;
    int distance;

    Vector(Location a, Location b) : a(a), b(b) {
        //Points a and b are not the same
        //they are either on single vertical or horizontal line
        if (a.x == b.x) {
            direction = (a.y < b.y) ? EAST : WEST;
            distance = abs(a.y - b.y);
        } else if (a.y == b.y) {
            direction = (a.x < b.x) ? SOUTH : NORTH;
            distance = abs(a.x - b.x);
        }
    }

    Vector(int ax, int ay, int bx, int by) : Vector(Location(ax, ay), Location(bx, by)) {}

    void update(int d) {
        distance = d;
        switch (direction) {
            case NORTH:
                b.x = a.x - d;
                break;
            case SOUTH:
                b.x = a.x + d;
                break;
            case WEST:
                b.y = a.y - d;
                break;
            case EAST:
                b.y = a.y + d;
                break;
        }
    }

    bool intersects(const Vector &v) const {
        switch (direction) {
            case NORTH:
                return northIntersect(v);
            case EAST:
                return eastIntersect(v);
            case WEST:
                return eastIntersect(Vector(v.b, v.a));
            case SOUTH:
                return northIntersect(Vector(v.b, v.a));
            default:
                return false;
        }
    }

private:
    bool northIntersect(const Vector &v) const {
        switch (v.direction) {
            case NORTH:
                return a == v.a;
            case SOUTH: {
                if (a.y != v.a.y)
                    return false;
                //intersection of 2 line segments
                //Ia = [as, ac], Ib = [bs, bc], Io = [os, oc]
                int as = v.a.x, ac = v.b.x, bs = b.x, bc = a.x;
                if (bs > ac || as > bc)
                    return false;
                else {
                    int os = std::max(as, bs);
                    int oc = std::min(ac, bc);
                    int diff = oc - os;
                    return diff % 2 == 0;
                }
            }
            case WEST:
                if (a.y >= v.b.y && a.y <= v.a.y && v.a.x <= a.x && v.a.x >= b.x) {
                    Location intersection(v.a.x, a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case EAST:
                if (a.y <= v.b.y && a.y >= v.a.y && v.a.x <= a.x && v.a.x >= b.x) {
                    Location intersection(v.a.x, a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            default:
                return false;
        }
    }
    bool eastIntersect(const Vector &v) const {
        switch (v.direction) {
            case NORTH:
                if (v.a.y >= a.y && v.a.y <= b.y && v.a.x >= a.x && v.b.x <= a.x) {
                    Location intersection(a.x, v.a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case SOUTH:
                if (v.a.y >= a.y && v.a.y <= b.y && v.a.x <= a.x && v.b.x >= a.x) {
                    Location intersection(a.x, v.a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case WEST: {
                if (a.x != v.a.x)
                    return false;
                //intersection of 2 line segments
                //Ia = [as, ac], Ib = [bs, bc], Io = [os, oc]
                int as = v.a.y, ac = v.b.y, bs = b.y, bc = a.y;
                if (bs > ac || as > bc)
                    return false;
                else {
                    int os = std::max(as, bs);
                    int oc = std::min(ac, bc);
                    int diff = oc - os;
                    return diff % 2 == 0;
                }
            }
            case EAST:
                return a == v.a;
            default:
                return false;
        }
    }
};

using namespace std;

int main() {
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
    return 0;
}

