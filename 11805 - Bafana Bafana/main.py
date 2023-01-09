import sys

line = sys.stdin.readline()
n = int(line.strip())
for i in range(n):
    n, k, p = [int(n) for n in sys.stdin.readline().strip().split()]
    out = (k + p) % n
    res = out if out != 0 else n
    print(f"Case {i+1}: {res}")
