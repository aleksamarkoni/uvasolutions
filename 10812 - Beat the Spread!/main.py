import sys

line = sys.stdin.readline().strip()
num = int(line)
for i in range(num):
    data = sys.stdin.readline().strip().split()
    a, b = int(data[0]), int(data[1])
    if abs(a-b) % 2 != 0:
        print("impossible")
        continue
    x = (a + b) / 2
    y = abs(a - x)
    if (x + y) == a and abs(x-y) == b:
        print("{:.0f} {:.0f}".format(max(x, y), min(x,y)))
    else:
        print("impossible")
