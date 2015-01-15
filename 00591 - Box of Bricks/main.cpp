#include<stdio.h>
int main()
{
 int a[150],b,c,d,e,f=1;

 while(1)
 {
  scanf("%d",&b);
  d=0;
  e=0;

  if (b==0)
  {
   break;
  }

  for (c=0;c<b;c++)
  {
   scanf("%d",&a[c]);
   d+=a[c];
  }
  
  d/=b;

  for (c=0;c<b;c++)
  {
   if (a[c]>d)
   {
    e+=a[c]-d;
   }
  }
  printf("Set #%d\nThe minimum number of moves is %d.\n\n",f++,e);
 }
 return 0;
}