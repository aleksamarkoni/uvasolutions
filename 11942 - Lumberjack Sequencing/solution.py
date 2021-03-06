import sys

line = sys.stdin.readline()
n = int(line.strip())
print("Lumberjacks:")
for i in range(n):
    line = sys.stdin.readline()
    data = line.split()
    #check to see if they are sorted asc
    asc = lambda x, y : x >= y
    desc = lambda x, y : x <= y
    def check(data, f):
        sorted = True
        for j in range(1, len(data)):
            if f(int(data[j]),int(data[j-1])):
                sorted = False
                break
        return sorted    
    if check(data, asc) or check(data, desc):
        print("Ordered")
    else:
        print("Unordered")
