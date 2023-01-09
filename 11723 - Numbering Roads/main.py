import sys

i = 1
while True:
    n, r = [int(n) for n in sys.stdin.readline().strip().split()]
    if n == 0 and r == 0:
        break
    out = (n - 1) // r
    res = out if out <= 26 else "impossible"
    print(f"Case {i}: {res}")
    i = i + 1
