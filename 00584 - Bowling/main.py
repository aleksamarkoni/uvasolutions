import sys

while True:
    line = sys.stdin.readline().strip()
    if line == "Game Over":
        break
    s = 0
    rools = line.split() + ['0', '0'] #to prevent index out of bounds
    i = 0
    frame = 0
    while i < len(rools) - 2 and frame < 10:
        if rools[i] == 'X':
            s += 10 #plus next two rools
            if rools[i+2] == '/':
                s += 10
            else:
                s += 10 if rools[i+1] == 'X' else int(rools[i+1])
                s += 10 if rools[i+2] == 'X' else int(rools[i+2])
            i += 1
        else:
            if rools[i+1] == '/':
                s += 10
                s += 10 if rools[i+2] == 'X' else int(rools[i+2])
            else:
                s += int(rools[i]) + int(rools[i+1])
            i += 2
        frame += 1
    print(s)
