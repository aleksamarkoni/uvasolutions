import sys
import math

tc = int(sys.stdin.readline().strip())

for t in range(tc):
    amount = int(sys.stdin.readline().strip())
    total = 0
    if amount > 180000:
        amount -= 180000
    else:
        amount = 0
    if amount > 300000:
        amount -= 300000
        total += 30000
    else:
        total += amount / 10
        amount = 0
    if amount > 400000:
        amount -= 400000
        total += 4000 * 15
    else:
        total += (amount / 100) * 15
        amount = 0
    if amount > 300000:
        amount -= 300000
        total += 60000
    else:
        total += amount / 5
        amount = 0
    total += amount / 4
    if total > 0 and total < 2000:
        total = 2000
    total = math.ceil(total)
    print("Case {}: {:.0f}".format(t+1, total))
