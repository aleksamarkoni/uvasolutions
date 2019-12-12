cmake_minimum_required(VERSION 3.14)
project(untitled)

set(CMAKE_CXX_STANDARD 14)

find_package(Qt5Core REQUIRED)
find_package(Qt5Gui REQUIRED)
find_package(Qt5Widgets REQUIRED)

add_executable(untitled main.cpp)

target_link_libraries(${PROJECT_NAME} Qt5::Core)
target_link_libraries(${PROJECT_NAME} Qt5::Gui)
target_link_libraries(${PROJECT_NAME} Qt5::Widgets)