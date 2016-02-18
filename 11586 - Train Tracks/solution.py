import sys

line = sys.stdin.readline().strip()
t = int(line)
for testCase in range(t):
   line = sys.stdin.readline().strip()
   pices = line.split()
   totalM = 0
   totalF = 0
   for pice in pices:
       if pice == "MF" or pice == "FM":
           totalM = totalM + 1
           totalF = totalF + 1
       elif pice == "FF":
           totalF = totalF + 1
       else:
           totalM = totalM + 1
   #if there is only one pice there should not be a loop
   if len(pices) > 1 and totalM == totalF:
       print("LOOP")
   else:
       print("NO LOOP")
