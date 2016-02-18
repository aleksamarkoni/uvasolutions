import sys

#algorith O(n), only count colums when the previous colum is higher that the current
#and substract the width of the previous colum to get the total cuts needed

while True:
    line = sys.stdin.readline().strip()
    data = line.split()
    height = int(data[0])
    width = int(data[1])
    if height == 0 and width == 0:
        break
    line = sys.stdin.readline().strip()
    data = line.split()
    firstColHeight = int(data[0])
    cutsNeeded = height - firstColHeight
    previousColHeight = firstColHeight
    for i in range(1, width):
        colHeight = int(data[i])
        if colHeight < previousColHeight:
            cutsNeeded = cutsNeeded + previousColHeight - colHeight
        previousColHeight = colHeight
    print(cutsNeeded)
        
