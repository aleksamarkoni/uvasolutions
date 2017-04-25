import sys
from operator import itemgetter
from collections import OrderedDict

tc = 0
while True:
    line = sys.stdin.readline().strip()
    n, t = [int(x) for x in line.split()]
    if n == 0 and t == 0:
        break
    travelers = {}
    tc += 1
    for i in range(n):
        line = sys.stdin.readline().strip()
        travelers[line] = 0
    for i in range(t):
        line = sys.stdin.readline().strip()
        n1, n2, amount = line.split()
        travelers[n1] -= int(amount)
        travelers[n2] += int(amount)
    d =  OrderedDict(sorted(travelers.items(), key=itemgetter(1)))
    st = list(d.keys()) #sorted travelers
    first, last = 0, len(st) - 1
    print("Case #" + str(tc))
    while first < last:
        n1, n2 = st[first], st[last]
        a1, a2 = d[n1], d[n2]
        diff = a1 + a2
        res = 0
        if diff < 0:
            res = a2
            last -= 1
            d[st[first]] = diff
        elif diff > 0:
            res = -a1
            first += 1
            d[st[last]] = diff
        else:
            res = a2
            first += 1
            last -= 1
        if res != 0:
            print(n2, n1, res)
    print()
