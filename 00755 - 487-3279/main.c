#include <map>
#include <string>
#include <stdio.h>
using namespace std;

map<int,int> T;

int main() {
    int phonenumber,occurrences;
    int numcases;
    scanf("%d", &numcases);
    while(numcases--) {
        T.clear();
        int input;
        scanf("%d", &input);
        for (int i=0; i< input; i++ {
            char broj[50];
            scanf("%s", broj);
            phonenumber = obradi(broj);
            T[phonenumber]++;
        }
        map<int,int>::iterator ptr=T.begin();
        map<int,int>::iterator end=T.end();
        do {
            phonenumber=ptr->first;
            occurrences=ptr->second;
            printf("%03d-%04d ",phonenumber/10000,phonenumber%10000);
            printf("%d\n", occurrences);
        } while(++ptr!=end);
    }
}
