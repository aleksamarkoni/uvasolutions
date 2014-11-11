#include <stdio.h>      /* printf */
#include <stdlib.h>     /* abs */

int calculateDiff(int t1, int t2){
	if (t1-t2 < 0)
		return 9 * ( 40 + t1 - t2);
	else 
		return 9 * (t1 - t2);
}

int main ()
{
  int s, t1, t2, t3;
  while (scanf("%d %d %d %d", &s, &t1, &t2, &t3), (s || t1 || t2 || t3)) {
	int d = 720;
	d += calculateDiff(s, t1);
	d += 360;
	d += calculateDiff(t2, t1);
	d += calculateDiff(t2, t3);
	printf("%d\n",  d);
  }
}