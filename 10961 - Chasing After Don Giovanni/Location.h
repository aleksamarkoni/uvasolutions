//
// Created by aleksamarkoni on 1/25/19.
//

#ifndef INC_10961_CHASING_AFTER_DON_GIOVANNI_LOCATION_H
#define INC_10961_CHASING_AFTER_DON_GIOVANNI_LOCATION_H


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


#endif //INC_10961_CHASING_AFTER_DON_GIOVANNI_LOCATION_H
