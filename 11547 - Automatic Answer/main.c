#include <stdio.h>
#include <stdlib.h>

int main(){
	/*
	((n * 63) + 7492) * 5 - 498
	(315*n) + 37460 - 498
	((315*n + 36962)% 100) / 10
	*/
	int K, a;
	scanf("%d", &K);
	while (K--) {
		scanf("%d", &a);
		printf("%d\n", abs(((315*a + 36962) % 100) / 10));
	}
}