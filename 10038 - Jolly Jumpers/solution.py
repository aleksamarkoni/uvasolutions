import sys

for line in sys.stdin:
    data = line.split()
    n = int(data[0])
    #print(n)
    if n == 1:
        print("Jolly")
        continue
    sol = [0] * (n-1)
    #print(sol)
    for i in range(2, len(data)):
        diff = abs(int(data[i]) - int(data[i-1]))
        if diff > (n-1):
            break
        sol[diff-1] = 1
    #print(sol)
    if sum(sol) == (n-1):
        print("Jolly")
    else:
        print("Not jolly")
