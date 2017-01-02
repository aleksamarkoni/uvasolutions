import sys

def f91(n):
    if n <=100:
        return f91(f91(n + 11))
    else:
        return n - 10

while True:
    line = sys.stdin.readline().strip()
    n = int(line)
    if n is 0:
        break
    if n <= 100:
        print('f91({}) = 91'.format(n))
    else:
        print('f91({}) = {}'.format(n, n-10))
