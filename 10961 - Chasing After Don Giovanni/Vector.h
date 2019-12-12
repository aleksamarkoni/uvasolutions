//
// Created by aleksamarkoni on 1/25/19.
//

#ifndef INC_10961_CHASING_AFTER_DON_GIOVANNI_VECTOR_H
#define INC_10961_CHASING_AFTER_DON_GIOVANNI_VECTOR_H

#include <iostream>
#include "Location.h"


enum Direction {
    NORTH, WEST, SOUTH, EAST
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
                return westIntersect(v);
            case SOUTH:
                return southIntersect(v);
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

    bool southIntersect(const Vector &v) const {
        switch (v.direction) {
            case NORTH: {
                if (a.y != v.a.y)
                    return false;
                //intersection of 2 line segments
                //Ia = [as, ac], Ib = [bs, bc], Io = [os, oc]
                int as = v.b.x, ac = v.a.x, bs = a.x, bc = b.x;
                if (bs > ac || as > bc)
                    return false;
                else {
                    int os = std::max(as, bs);
                    int oc = std::min(ac, bc);
                    int diff = oc - os;
                    return diff % 2 == 0;
                }
            }
            case SOUTH:
                return a == v.a;
            case WEST:
                if (a.y >= v.b.y && a.y <= v.a.y && v.a.x >= a.x && v.a.x <= b.x) {
                    Location intersection(v.a.x, a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case EAST:
                if (a.y <= v.b.y && a.y >= v.a.y && v.a.x >= a.x && v.a.x <= b.x) {
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
                int as = v.b.y, ac = v.a.y, bs = a.y, bc = b.y;
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

    bool westIntersect(const Vector &v) const {
        switch (v.direction) {
            case NORTH:
                if (v.a.y >= b.y && v.a.y <= a.y && v.a.x >= a.x && v.b.x <= a.x) {
                    Location intersection(a.x, v.a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case SOUTH:
                if (v.a.y >= b.y && v.a.y <= a.y && v.a.x <= a.x && v.b.x >= a.x) {
                    Location intersection(a.x, v.a.y);
                    return Vector(a, intersection).distance == Vector(v.a, intersection).distance;
                }
                return false;
            case WEST: {
                return a == v.a;
            }
            case EAST:
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
    }
};


#endif //INC_10961_CHASING_AFTER_DON_GIOVANNI_VECTOR_H
