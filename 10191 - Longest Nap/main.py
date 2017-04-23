import sys

test = 0
while True:
    line = sys.stdin.readline().strip()
    if not line:
        break
    available = [True] * 480
    n = int(line)
    for i in range(n):
        line = sys.stdin.readline().strip()
        data = line.split()
        t1, t2 = data[0], data[1]
        t1h, t1m = t1.split(":")
        t2h, t2m = t2.split(":")
        tstart = (int(t1h) * 60 + int(t1m)) - 600
        tend = (int(t2h) * 60 + int(t2m)) - 600
        for i in range(tstart, tend):
            available[i] = False
    m = 0
    tc = 0
    start = 0
    for i in range(480):
        if available[i]:
            tc += 1
            continue
        else:
            if tc > m:
                m = tc;
                start = i - tc
            tc = 0
    if tc > m:
        m = tc
        start = 480 - tc
    test += 1
    resh = (start + 600) // 60
    resm = (start + 600) % 60
    print("Day #{}: the longest nap starts at {:02d}:{:02d} and will last for ".format(test, resh, resm), end='')
    if m >= 60:
        print("{} hours and ".format(m//60),end='')
    print("{} minutes.".format(m%60))
