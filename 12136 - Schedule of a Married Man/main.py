import sys

tc = int(sys.stdin.readline().strip())

def readTime():
    line = sys.stdin.readline().strip()
    start, end = line.split()
    h1, m1 = [int(x) for x in start.split(':')]
    h2, m2 = [int(x) for x in end.split(':')]
    t1 = h1 * 60 + m1
    t2 = h2 * 60 + m2
    return t1, t2

for i in range(tc):
    print("Case {}: ".format(i+1), end='')
    wts, wte = readTime()
    mts, mte = readTime()
    #simple intersection range problem
    if (max(wts, mts) <= min(wte, mte)):
        print("Mrs Meeting")
    else:
        print("Hits Meeting")
