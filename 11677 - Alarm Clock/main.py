import sys

while True:
    line = sys.stdin.readline().strip()
    h1, m1, h2, m2 = [int(x) for x in line.split()]
    if h1 == 0 and m1 == 0 and h2 == 0 and m2 == 0:
        break
    t1, t2 = h1 * 60 + m1, h2 * 60 + m2
    if t2 < t1:
        t2 = t2 + 24 * 60
    print(t2 - t1)
