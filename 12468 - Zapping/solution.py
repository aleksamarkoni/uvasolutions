import sys
while True:
    line = sys.stdin.readline().strip()
    data = line.split()
    a = int(data[0])
    b = int(data[1])
    if a == -1 and b == -1:
        break
    diff = abs(a - b)
    if (100 - diff) < diff:
        print(str(100 - diff))
    else:
        print(str(diff))
