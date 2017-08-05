import sys
from collections import deque

while True:
    line = sys.stdin.readline().strip()
    n, k = [int(x) for x in line.split()]
    if n == 0 and k == 0:
        break
    row = deque()
    for i in range(0, n):
        row.append(i)
    payment = [40] * n
    cash = 1
    dispense = cash
    while row:
        student = row.popleft()
        left = payment[student]
        if (dispense >= left):
            print("{:3d}".format(student + 1), end='')
            dispense = dispense - left
        else:
            payment[student] = left - dispense
            row.append(student)
            dispense = 0
        if dispense == 0:
            cash = 1 if cash + 1 > k else cash + 1
            dispense = cash
    print()
        
