import sys

tc = 0

while True:
    line = sys.stdin.readline().strip()
    data = line.split()
    n = int(data[0])
    if n == 0:
        break;
    if tc != 0:
        print()
    tc += 1
    pwon = [0] * (n+1)
    plost = [0] * (n+1)
    k = int(data[1])
    g = k * ((n * (n - 1)) // 2)
    for i in range(g):
        line = sys.stdin.readline().strip()
        data = line.split()
        p1 = int(data[0])
        m1 = data[1]
        p2 = int(data[2])
        m2 = data[3]
        if m1 != m2:
            if m1 == "rock":
                if m2 == "scissors":
                    pwon[p1] += 1
                    plost[p2] += 1
                else:
                    plost[p1] += 1
                    pwon[p2] += 1
            if m1 == "scissors":
                if m2 == "paper":
                    pwon[p1] += 1
                    plost[p2] += 1
                else:
                    plost[p1] += 1
                    pwon[p2] += 1
            if m1 == "paper":
                if m2 == "rock":
                    pwon[p1] += 1
                    plost[p2] += 1
                else:
                    plost[p1] += 1
                    pwon[p2] += 1
    pwon.pop(0)
    plost.pop(0)
    for w, l in zip(pwon, plost):
        if (w+l) != 0:
            score = w / (w + l)
            print("{:.3f}".format(score))
        else:
            print("-")
