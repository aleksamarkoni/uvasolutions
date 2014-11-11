#include <stdio.h>

int main() {
	int T, a, b, c;
	
	scanf("%d", &T);
	int i = 1;
	while (T--) {
		scanf("%d %d %d", &a, &b, &c);
		if ((a >= b && a <= c) || (a>=c && a<=b))
			printf("Case %d: %d\n", i, a);
		else if ((b>=a && b<=c) || (b>=c && b<=a))
			printf("Case %d: %d\n", i, b);
		else 
			printf("Case %d: %d\n",i,  c);
		i++;
	}
}