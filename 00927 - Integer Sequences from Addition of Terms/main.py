import sys

def horner(poly, n, x):
    result = poly[0]
    for i in range(0, n):
        result = result * x + poly[i + 1]
    #print("the value of polynomial at x = " + str(x) + " is " + str(result))
    return result

def position(d, k):
    pos = 0
    s = 0
    while(s < k):
        pos = pos + 1
        s += pos * d
    return pos

tc = int(sys.stdin.readline().strip())
for test in range(tc):
    line = [int(x) for x in sys.stdin.readline().strip().split()]
    n, poly = line[0], line[1:]
    d = int(sys.stdin.readline().strip())
    k = int(sys.stdin.readline().strip())
    pos = position(d, k)
    print(horner(poly[::-1], n, pos))
