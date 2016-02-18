import sys

while True:
    line = sys.stdin.readline().strip()
    length = int(line)
    if length == 0:
        break
    line = sys.stdin.readline().strip()
    lastR = -1
    lastD = -1
    minDistance = 2000001
    for i in range(length):
        place = line[i]
        if place == 'D':
            if lastR != -1:
                distance = i - lastR
                if minDistance > distance:
                    minDistance = distance
            lastD = i
        if place == 'R':
            if lastD != -1:
                distance = i - lastD
                if minDistance > distance:
                    minDistance = distance
            lastR = i
        if place == 'Z':
            minDistance = 0
            break
    print(minDistance)
            
             
