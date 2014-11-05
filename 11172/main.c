#include <stdio.h>

int main() {
	int TC, a, b;
	
	scanf("%d", &TC);
	while (TC--) {
		scanf("%d %d", &a, &b);
		// do the logic here
		if (a > b)
			printf(">\n");
		else if (a < b)
			printf("<\n");
		else 
			printf("=\n");
	}
}