import sys

tc = int(sys.stdin.readline().strip())

for i in range(tc):
    print("Case {}: ".format(i+1), end='')
    line = sys.stdin.readline().strip()
    k, time = line.split()
    k = int(k)
    h1, m1 = [int(x) for x in time.split(':')]
    t1 = h1 * 60 + m1
    min = 24 * 60 + 5000
    for j in range(k):
        line = sys.stdin.readline().strip()
        bus, time = line.split()
        time = int(time)
        h2, m2 = [int(x) for x in bus.split(':')]
        t2 = h2 * 60 + m2
        if t2 < t1:
            t2 = t2 + 24 * 60
        travel = (t2 - t1) + time
        if travel < min:
            min = travel
    print(min)
