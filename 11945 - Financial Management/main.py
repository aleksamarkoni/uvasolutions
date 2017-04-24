import sys

line = sys.stdin.readline().strip()
tc = int(line)
for i in range(tc):
    s = 0
    for j in range(12):
        line = sys.stdin.readline().strip()
        s += float(line)
    print("{} ${:,.2f}".format((i+1), s/12))
