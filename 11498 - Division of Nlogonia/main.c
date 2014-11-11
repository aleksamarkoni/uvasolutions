#include <stdio.h>

int main() {
	int K, xc, yc, x, y;
	
	while (scanf("%d", &K), K) {
		scanf("%d %d", &xc, &yc);
		while (K--) {
			scanf("%d %d", &x, &y);
			// do the logic here
			if (y > yc) {
				if ( x > xc) 
					printf("NE\n");
				else if (x < xc)
					printf("NO\n");
				else 
					printf("divisa\n");
			} else if ( y < yc) {
				if ( x > xc) 
					printf("SE\n");
				else if (x < xc)
					printf("SO\n");
				else 
					printf("divisa\n");
			} else 
				printf("divisa\n");
		}
	}
}