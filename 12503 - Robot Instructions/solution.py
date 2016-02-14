import sys

line = sys.stdin.readline()
t = int(line.strip())
for i in range(t):
    line = sys.stdin.readline()
    n = int(line.strip())
    pos = 0
    instList = [0] * n
    for j in range(n):
        inst = sys.stdin.readline().strip()
        if inst == "LEFT":
            pos = pos - 1
            instList[j] = -1
        elif inst == "RIGHT":
            pos = pos + 1
            instList[j] = 1
        else:
            data = inst.split()
            sameAs = int(data[2])
            pos = pos + instList[sameAs - 1]
            instList[j] = instList[sameAs - 1]
    print(pos)

