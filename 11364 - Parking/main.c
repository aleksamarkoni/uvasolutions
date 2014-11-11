#include <stdio.h>

int main(){
	int K, NP, min, max, p;
	scanf("%d", &K);
	while (K--) {
		scanf("%d", &NP);
		min = 100;
		max = 0;
		while (NP--) {
			scanf("%d", &p);
			if (p < min)
				min = p;
			if (p > max)
				max = p;
		}
		printf("%d\n", 2 * (max - min));
	}
}