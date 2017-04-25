import sys

while True:
    line = sys.stdin.readline().strip()
    n = int(line)
    if n == 0:
        break
    for i in range(n):
        line = sys.stdin.readline().strip()
        data = line.split()
