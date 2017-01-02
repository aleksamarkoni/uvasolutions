#include <iostream>
using namespace std;
/*
This is internet explanation
Let T[n] be the nth triangular number. Let i be the smallest number such that T[i] >= k. Obviously i is a lower bound for n.

Now, if T[i] - k is even, then we can negate some of the numbers from 1...i to make the formula work. If T[i] - k isn't even, then we can try T[i+1], and T[i+2]. The triangular number sequence goes {even, odd, odd, even, even, odd, odd, etc.} so we never need to check more than 2 triangular numbers above i.

Note that if k is negative, you can just negate the entire expression, so there is no difference in n.

Note the special case k = 0. n must be >= 1, so you cannot say n = 0 if k = 0.
*/

/* this is my explanation
broj koji|      min n za sum|  suma n| s-suman| min n
k = 1                   n = 1   s = 1   d = 0   rez = n
k = 2 = 1-2+3           n = 2   s = 3   d = 1   rez = n+1
k = 3 = 1+2             n = 2   s = 3   d = 0   rez = n
k = 4 =-1+2+3           n = 3   s = 6   d = 2   rez = n
k = 5 = 1+2+3+4-5       n = 3   s = 6   d = 1   rez = n+2
i kada bi ovako u nedogled resenje bi bilo ocigledno
*/

int main(){
    int N;
    cin >> N;
    while (N--) {
        long long in;
        long long rez;
        cin >> in;
        if (in == 0) rez = 3;
        else {
            if (in < 0) in = - in;
            long long d = 1;
            while (in > (d*(d+1)/2)) ++d;
            while ((in&1) != ((d*(d+1)/2)&1)) ++d;
            rez = d;
        }
        cout << rez << endl;
        if (N!=0) cout << endl;
    }
    return 0;
}
