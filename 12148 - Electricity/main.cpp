#include <iostream>

using namespace std;

int days_in_month[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

class date {
public:
  int day;
  int month;
  int year;
  int power;
  void nextDay() {
    int dayInMonth = days_in_month[month];
    if (month == 2 && isLeap())
      dayInMonth++;
    day += 1;
    if (day > dayInMonth) {
      day = 1;
      month += 1;
      if (month > 12) {
        month = 1;
        year += 1;
      }
    }
  }
  bool operator == (const date &d) const{
    return d.day == day && d.month == month && d.year == year;
  }
private:
  bool isLeap() {
    return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
  }
};

int main() {
  int num;
  while (true) {
    cin >> num;
    if (num == 0)
      break;
    date prev;
    cin >> prev.day >> prev.month >> prev.year >> prev.power;
    if (num == 1) {
      cout << "0 0" << endl;
      continue;
    }
    int sum = 0;
    int numOfConsDay = 0;
    while (--num > 0) {
      date curr;
      cin >> curr.day >> curr.month >> curr.year >> curr.power;
      prev.nextDay();
      if (curr == prev) {
        sum += curr.power - prev.power;
        numOfConsDay++;
      }
      prev = curr;
    }
    cout << numOfConsDay << " " << sum << endl;
  }
}
