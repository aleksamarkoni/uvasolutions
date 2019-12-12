#define CATCH_CONFIG_MAIN

#include "catch.hpp"
#include "Location.h"
#include "Vector.h"

TEST_CASE("Testing Intersections", "[Intersections]") {
    SECTION("check intersection with the vector that has north direction") {
        Location a(10, 5);
        Location b(6, 5);
        Vector north(a, b);
        SECTION("Checks with the north vectors") {
            REQUIRE(!north.intersects(Vector(10, 6, 5, 6)));
            REQUIRE(north.intersects(Vector(10, 5, 6, 5)));
        }
        SECTION("Checks with west vectors") {
            REQUIRE(!north.intersects(Vector(15, 15, 15, 10)));
            REQUIRE(!north.intersects(Vector(8, 15, 8, 10)));
            REQUIRE(!north.intersects(Vector(3, 15, 3, 10)));
            REQUIRE(!north.intersects(Vector(15, 3, 15, 1)));
            REQUIRE(!north.intersects(Vector(8, 3, 8, 1)));
            REQUIRE(!north.intersects(Vector(3, 3, 3, 1)));
            REQUIRE(north.intersects(Vector(10, 5, 10, 1)));
            REQUIRE(north.intersects(Vector(8, 7, 8, 3)));
            REQUIRE(north.intersects(Vector(7, 8, 7, 4)));
            REQUIRE(north.intersects(Vector(6, 9, 6, 5)));
        }
        SECTION("Check with east vectors") {
            REQUIRE(!north.intersects(Vector(15, 10, 15, 15)));
            REQUIRE(!north.intersects(Vector(8, 10, 8, 15)));
            REQUIRE(!north.intersects(Vector(3, 10, 3, 15)));
            REQUIRE(!north.intersects(Vector(15, 1, 15, 3)));
            REQUIRE(!north.intersects(Vector(8, 1, 8, 3)));
            REQUIRE(!north.intersects(Vector(3, 1, 3, 3)));
            REQUIRE(north.intersects(Vector(10, 5, 10, 9)));
            REQUIRE(north.intersects(Vector(8, 3, 8, 7)));
            REQUIRE(north.intersects(Vector(7, 2, 7, 6)));
            REQUIRE(north.intersects(Vector(6, 1, 6, 5)));
        }
        SECTION("Check with the south vector") {
            REQUIRE(!north.intersects(Vector(6, 6, 10, 6)));
            REQUIRE(!north.intersects(Vector(6, 4, 10, 4)));
            REQUIRE(!north.intersects(Vector(0, 5, 4, 5)));
            REQUIRE(!north.intersects(Vector(11, 5, 15, 5)));
            REQUIRE(north.intersects(Vector(2, 5, 6, 5)));
            REQUIRE(north.intersects(Vector(10, 5, 14, 5)));
            REQUIRE(north.intersects(Vector(6, 5, 10, 5)));
            REQUIRE(!north.intersects(Vector(5, 5, 9, 5)));
            REQUIRE(north.intersects(Vector(8, 5, 12, 5)));
        }
    }
    SECTION("check intersection with the vector that has south direction") {
        Location a(6, 5);
        Location b(10, 5);
        Vector south(a, b);
        SECTION("Checks with the north vectors") {
            REQUIRE(!south.intersects(Vector(10, 6, 6, 6)));
            REQUIRE(!south.intersects(Vector(10, 4, 6, 4)));
            REQUIRE(!south.intersects(Vector(4, 5, 0, 5)));
            REQUIRE(!south.intersects(Vector(15, 5, 11, 5)));
            REQUIRE(south.intersects(Vector(6, 5, 2, 5)));
            REQUIRE(south.intersects(Vector(14, 5, 10, 5)));
            REQUIRE(south.intersects(Vector(10, 5, 6, 5)));
            REQUIRE(!south.intersects(Vector(9, 5, 9, 5)));
            REQUIRE(south.intersects(Vector(12, 5, 8, 5)));
        }
        SECTION("Checks with west vectors") {
            REQUIRE(!south.intersects(Vector(15, 15, 15, 10)));
            REQUIRE(!south.intersects(Vector(8, 15, 8, 10)));
            REQUIRE(!south.intersects(Vector(3, 15, 3, 10)));
            REQUIRE(!south.intersects(Vector(15, 3, 15, 1)));
            REQUIRE(!south.intersects(Vector(8, 3, 8, 1)));
            REQUIRE(!south.intersects(Vector(3, 3, 3, 1)));
            REQUIRE(south.intersects(Vector(10, 9, 10, 5)));
            REQUIRE(south.intersects(Vector(8, 7, 8, 3)));
            REQUIRE(south.intersects(Vector(7, 6, 7, 2)));
            REQUIRE(south.intersects(Vector(6, 5, 6, 1)));
        }
        SECTION("Check with east vectors") {
            REQUIRE(!south.intersects(Vector(15, 10, 15, 15)));
            REQUIRE(!south.intersects(Vector(8, 10, 8, 15)));
            REQUIRE(!south.intersects(Vector(3, 10, 3, 15)));
            REQUIRE(!south.intersects(Vector(15, 1, 15, 3)));
            REQUIRE(!south.intersects(Vector(8, 1, 8, 3)));
            REQUIRE(!south.intersects(Vector(3, 1, 3, 3)));
            REQUIRE(south.intersects(Vector(10, 1, 10, 5)));
            REQUIRE(south.intersects(Vector(9, 2, 9, 6)));
            REQUIRE(south.intersects(Vector(8, 3, 8, 7)));
            REQUIRE(south.intersects(Vector(7, 4, 7, 7)));
            REQUIRE(south.intersects(Vector(6, 5, 6, 9)));
        }
        SECTION("Check with the south vector") {
            REQUIRE(!south.intersects(Vector(7, 5, 11, 5)));
            REQUIRE(south.intersects(Vector(6, 5, 10, 5)));
        }
    }
    SECTION("check intersection with the vector that has east direction") {
        Location a(5, 6);
        Location b(5, 10);
        Vector east(a, b);
        SECTION("Checks with the north vectors") {
            REQUIRE(!east.intersects(Vector(10, 3, 6, 3)));
            REQUIRE(!east.intersects(Vector(10, 8, 6, 8)));
            REQUIRE(!east.intersects(Vector(4, 8, 0, 8)));
            REQUIRE(!east.intersects(Vector(15, 5, 11, 5)));
            REQUIRE(east.intersects(Vector(5, 6, 1, 6)));
            REQUIRE(east.intersects(Vector(6, 7, 2, 7)));
            REQUIRE(east.intersects(Vector(7, 8, 3, 8)));
            REQUIRE(east.intersects(Vector(8, 9, 4, 9)));
            REQUIRE(east.intersects(Vector(9, 10, 5, 10)));
            REQUIRE(!east.intersects(Vector(8, 6, 4, 6)));
            REQUIRE(!east.intersects(Vector(7, 10, 3, 10)));
        }
        SECTION("Checks with west vectors") {
            REQUIRE(!east.intersects(Vector(4, 10, 4, 6)));
            REQUIRE(!east.intersects(Vector(6, 10, 6, 6)));
            REQUIRE(!east.intersects(Vector(5, 4, 5, 0)));
            REQUIRE(!east.intersects(Vector(5, 15, 5, 11)));
            REQUIRE(east.intersects(Vector(5, 6, 5, 2)));
            REQUIRE(east.intersects(Vector(5, 14, 5, 10)));
            REQUIRE(east.intersects(Vector(5, 10, 5, 6)));
            REQUIRE(!east.intersects(Vector(5, 9, 5, 5)));
            REQUIRE(east.intersects(Vector(5, 8, 5, 4)));
        }
        SECTION("Check with east vectors") {
            REQUIRE(east.intersects(Vector(5, 6, 5, 10)));
            REQUIRE(!east.intersects(Vector(4, 6, 8, 6)));
        }
        SECTION("Check with the south vector") {
            REQUIRE(!east.intersects(Vector(6, 3, 10, 3)));
            REQUIRE(!east.intersects(Vector(6, 8, 10, 8)));
            REQUIRE(!east.intersects(Vector(0, 8, 4, 8)));
            REQUIRE(!east.intersects(Vector(11, 5, 15, 5)));
            REQUIRE(east.intersects(Vector(5, 6, 9, 6)));
            REQUIRE(east.intersects(Vector(4, 7, 8, 7)));
            REQUIRE(east.intersects(Vector(3, 8, 7, 8)));
            REQUIRE(east.intersects(Vector(2, 9, 6, 9)));
            REQUIRE(east.intersects(Vector(1, 10, 5, 10)));
            REQUIRE(!east.intersects(Vector(4, 6, 8, 6)));
            REQUIRE(!east.intersects(Vector(3, 10, 7, 10)));
        }
    }
    SECTION("check intersection with the vector that has west direction") {
        Location a(5, 10);
        Location b(5, 6);
        Vector west(a, b);
        SECTION("Checks with the north vectors") {
            REQUIRE(!west.intersects(Vector(10, 3, 6, 3)));
            REQUIRE(!west.intersects(Vector(10, 8, 6, 8)));
            REQUIRE(!west.intersects(Vector(4, 8, 0, 8)));
            REQUIRE(!west.intersects(Vector(15, 5, 11, 5)));
            REQUIRE(west.intersects(Vector(9, 6, 5, 6)));
            REQUIRE(west.intersects(Vector(8, 7, 4, 7)));
            REQUIRE(west.intersects(Vector(7, 8, 3, 8)));
            REQUIRE(west.intersects(Vector(6, 9, 2, 9)));
            REQUIRE(west.intersects(Vector(5, 10, 1, 10)));
            REQUIRE(!west.intersects(Vector(4, 6, 8, 6)));
            REQUIRE(!west.intersects(Vector(3, 10, 7, 10)));
        }
        SECTION("Checks with west vectors") {
            REQUIRE(west.intersects(Vector(5, 10, 5, 6)));
            REQUIRE(!west.intersects(Vector(8, 6, 4, 6)));
        }
        SECTION("Check with east vectors") {
            REQUIRE(!west.intersects(Vector(4, 10, 4, 6)));
            REQUIRE(!west.intersects(Vector(6, 10, 6, 6)));
            REQUIRE(!west.intersects(Vector(5, 4, 5, 0)));
            REQUIRE(!west.intersects(Vector(5, 15, 5, 11)));
            REQUIRE(west.intersects(Vector(5, 6, 5, 2)));
            REQUIRE(west.intersects(Vector(5, 14, 5, 10)));
            REQUIRE(west.intersects(Vector(5, 10, 5, 6)));
            REQUIRE(!west.intersects(Vector(5, 9, 5, 5)));
            REQUIRE(west.intersects(Vector(5, 8, 5, 4)));
        }
        SECTION("Check with the south vector") {
            REQUIRE(!west.intersects(Vector(6, 3, 10, 3)));
            REQUIRE(!west.intersects(Vector(6, 8, 10, 8)));
            REQUIRE(!west.intersects(Vector(0, 8, 4, 8)));
            REQUIRE(!west.intersects(Vector(11, 5, 15, 5)));
            REQUIRE(west.intersects(Vector(9, 6, 5, 6)));
            REQUIRE(west.intersects(Vector(8, 7, 4, 7)));
            REQUIRE(west.intersects(Vector(7, 8, 3, 8)));
            REQUIRE(west.intersects(Vector(6, 9, 2, 9)));
            REQUIRE(west.intersects(Vector(5, 10, 1, 10)));
            REQUIRE(!west.intersects(Vector(4, 6, 8, 6)));
            REQUIRE(!west.intersects(Vector(3, 10, 7, 10)));
        }
    }
}

TEST_CASE("Testing Intersections 1", "[Intersections]") {
    SECTION("check intersection with the vector that has north direction") {
        Location a(6, 5);
        Location b(10, 5);
        Vector south(a, b);
        REQUIRE(south.intersects(Vector(6, 5, 2, 5)));
    }
}

