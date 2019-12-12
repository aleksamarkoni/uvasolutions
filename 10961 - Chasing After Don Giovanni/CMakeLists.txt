cmake_minimum_required(VERSION 3.14)
project(10961___Chasing_After_Don_Giovanni)

set(CMAKE_CXX_STANDARD 14)

find_package(Qt5Widgets REQUIRED)

include_directories(.)

add_executable(10961___Chasing_After_Don_Giovanni
        Location.cpp
        Location.h
        main.cpp
        Vector.cpp
        Vector.h GameGui.cpp GameGui.h)

target_link_libraries(${PROJECT_NAME} Qt5::Widgets)
