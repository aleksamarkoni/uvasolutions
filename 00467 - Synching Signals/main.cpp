#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <queue>

/*
We could do naive aproach to check after every second.
But to optimaze this, we are using priority queue. 
For every semaphore we calculate the nextChange time, and then add that Semaphore 
to the priority queue. In this way we are skipping time and doing checks
only on seconds when there is a change in semaphore.

We would need to optimaze this code a bit, to use pointers, instead fo coping
structs when we pop/push things from priority queue
*/

using namespace std;

enum Light { Green, Yellow, Red };

struct Semaphore {
  int nextChange;
  Light curLight;
  int cycle;
  
  Semaphore(int cycle) {
    nextChange = cycle - 5;
    curLight = Green;
    this->cycle = cycle;
  }
  
  void calculateNextChange() {
    switch (curLight) {
      case Green:
        nextChange += 5;
        curLight = Yellow;
        break;
      case Yellow:
        nextChange += cycle;
        curLight = Red;
        break;
      case Red:
        nextChange += cycle - 5;
        curLight = Green;
        break;
    }
  }
};

struct SemaphoreComparator {  
  bool operator() (const Semaphore &t1, const Semaphore &t2) const {
    return t1.nextChange > t2.nextChange;
  }
};

ostream& operator<<(ostream& os, const Semaphore& s)
{
    return os << '(' << s.cycle << ", " << s.curLight << "," << s.nextChange << ')';
}

template<typename A> void print_queue(A& pq)
{
	while (!pq.empty())
		{
			cout << pq.top() << ',';
			pq.pop();
		}
    cout << endl;
}

int main() {
  string line;
  int totalLights;
  int totalGreen;
  int set = 0;
  while (getline(cin, line)) {
    cout << "Set " << ++set << " ";
    istringstream iss(line);
    priority_queue<Semaphore, vector<Semaphore>, SemaphoreComparator> queue;
    int cycle;
    totalLights = 0;
    while (iss >> cycle) {
      totalLights++;
      Semaphore s(cycle);
      queue.push(s);
    }
    totalGreen = totalLights;
    while (true) {
      Semaphore s = queue.top();
      //cout << "Processing: " << s << endl;
      if (s.nextChange > 3600) {
        cout << "is unable to synch after one hour." << endl;
        break;
      }
      int semaphoreCurrTime = s.nextChange;
      s.calculateNextChange();
      if (s.curLight == Green) {
        totalGreen++;
      } else if (s.curLight == Yellow) {
        totalGreen--;
      }
      //cout << "After nextChange " << s << endl;
      //cout << "Total Green: " << totalGreen << endl;
      queue.pop();
      queue.push(s);
      if (semaphoreCurrTime != queue.top().nextChange) {
        if (totalGreen == totalLights) {
          cout << "synchs again at " << (semaphoreCurrTime / 60) << " minute(s) and " 
              << (semaphoreCurrTime % 60) << " second(s) after all turning green." << endl;
          break;
        }
      }
    }
    //print_queue(queue);
    //cout << "Total Green: " << totalGreen <<  endl;
  }
}
