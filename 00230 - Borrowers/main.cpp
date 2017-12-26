#include <iostream>
#include <string>
#include <algorithm>
#include <map>
#include <vector>
#include <sstream>

using namespace std;

enum bookStatusEnum { available, borrowed, returned };

struct book {
  string title, author; 
  bookStatusEnum bookStatus;
  inline bool operator < (const book & other) const {
    if (author != other.author) {
      return author < other.author;
    }
    return title < other.title;
  }
};

// trim from start (in place)
static inline void ltrim(std::string &s) {
    s.erase(s.begin(), find_if(s.begin(), s.end(), [](int ch) {
        return !std::isspace(ch);
    }));
}

// trim from end (in place)
static inline void rtrim(std::string &s) {
    s.erase(find_if(s.rbegin(), s.rend(), [](int ch) {
        return !std::isspace(ch);
    }).base(), s.end());
}

// trim from both ends (in place)
static inline void trim(std::string &s) {
    ltrim(s);
    rtrim(s);
}

int main() {
  string line;
  string title;
  string author;
  string command;
  book pomBook;
  vector<book> allBooks;
  map<string, int> shelfPosition;
  
  while (getline(cin, line)) {
    trim(line);
    if (line.compare("END") == 0)
      break;
    size_t start_title_quotes = line.find_first_of("\"");
    size_t end_title_quotes = line.find_first_of("\"", start_title_quotes + 1);
    title = line.substr(start_title_quotes, end_title_quotes + 1);
    size_t start_author_quotes = line.find_first_of("by", end_title_quotes + 1);
    author = line.substr(start_author_quotes + 3);
    trim(author);
    pomBook.title = title;
    pomBook.author = author;
    pomBook.bookStatus = available;
    allBooks.push_back(pomBook);
  }
  sort(allBooks.begin(), allBooks.end());
  
  for (int i = 0; i < allBooks.size(); i++) {
    shelfPosition[allBooks[i].title] = i;
  }
  
  while (getline(cin, line)) {
    trim(line);
    if (line.compare("END") == 0)
      break;
    istringstream iss(line);
    iss >> command;
    if (command == "BORROW") {
      getline(iss, title);
      trim(title);
      allBooks[shelfPosition[title]].bookStatus = borrowed;
    } else if (command == "RETURN") {
      getline(iss, title);
      trim(title);
      allBooks[shelfPosition[title]].bookStatus = returned;
    } else {
      int pos = -1;
      for (int i = 0; i < allBooks.size(); i++) {
        if (allBooks[i].bookStatus == returned) {
          if (pos == -1) {
            cout << "Put " << allBooks[i].title << " first" << endl;
          } else {
            cout << "Put " << allBooks[i].title << " after " << allBooks[pos].title << endl;
          }
          pos = i;
          allBooks[i].bookStatus = available;
        } else if (allBooks[i].bookStatus == available) {
          pos = i;
        }
      }
      cout << "END" << endl;
    }
  }
}
