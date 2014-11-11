#include <stdio.h>

int main() {
	char c;
	char left = 1;
	
	while ((c = getchar()) != EOF){
		
		if (c == '"') {
			if (left == 1) {
				putchar('`');
				putchar('`');
			} else if (left = -1) {
				putchar('\'');
				putchar('\'');
			}
			left = -left;
		} else {
			putchar(c);
		}
	}
}