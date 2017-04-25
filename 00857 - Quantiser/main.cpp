#include <iostream>
#include <map>

using namespace std;

class Note {
public:
  int code;
  int note;
  int m;
  int b;
  int t;
  bool printing;
  Note() {
    printing = true;
  }
  void round() {
    t = ((t + 30) / 60) * 60;
    if (t == 480) {
      t = 0;
      if (b++ == 4) {
        b = 1;
        m++;
      }
    }
  }
  bool operator==(const Note &n) const {
    return m == n.m && b == n.b && t == n.t;
  }



  friend std::ostream& operator<< (std::ostream &out, const Note &note) {
    out << note.code << " " << note.note << " " << note.m << " "
      << note.b << " " << note.t;
    return out;
  }
};

void print(map<int, int> my_map) {
  for(map<int,int>::const_iterator it = my_map.begin(); it != my_map.end(); ++it)
    std::cout << it->first << "," << it->second << endl;
}

int main() {
  int n, np, i, not_printing;
  Note notes[2001];
  map<int, int> note_links;
  map<int, int>::iterator it;
  while(true) {
    cin >> n;
    if (n == -1)
      break;
    np = 0;
    not_printing = 0;
    note_links.clear();
    for (i = 0; i < n; i++) {
      Note note;
      cin >> note.code >> note.note >> note.m >> note.b >> note.t;
      note.round();
      if (note.code == 0) {
        //cout << "finding " << note.note << " " << note_links.size() << endl;
        //print(note_links);
        it = note_links.find(note.note);
        if (it != note_links.end()) {
          //cout << "found: " << it->first << " " << it->second << endl;
          int position = it->second;
          if (note == notes[position]) {
            notes[position].printing = false;
            note.printing = false;
            not_printing += 2;
          }
          note_links.erase(it);
        }
      } else {
        //cout << "inserting" << endl;
        note_links.insert(pair<int, int>(note.note, np));
      }
      notes[np++] = note;
    }
    cout << np - not_printing << endl;
    for (i = 0; i < np; i++) {
      if (notes[i].printing)
        cout << notes[i] << endl;
    }
  }
  cout << "-1" << endl;
}
