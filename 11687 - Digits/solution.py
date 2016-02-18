import sys

while True:
    line = sys.stdin.readline().strip()
    if line == "END":
        break
    counter = 1
    x0 = line
    while True:
        x1 = len(x0)
        if x0 == str(x1):
            break
        x0 = str(x1)
        counter = counter + 1
    print(counter)
