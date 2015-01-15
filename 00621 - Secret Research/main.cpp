// problem je prelak, mrzelo me da radim
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
char num[1000];
int main()
{
int n;
scanf("%d",&n);
while(n--)
{
scanf("%s",num);
int i=atoi(num);
int len=strlen(num);
if(i==1 || i==4 || i==78)
printf("+\n");
else if(num[len-1]==(5+'0')&&num[len-2]==(3+'0'))
printf("-\n");
else if(num[len-1]==(4+'0')&&num[0]==(9+'0'))
printf("*\n");
else
printf("?\n");
}
return 0;
}