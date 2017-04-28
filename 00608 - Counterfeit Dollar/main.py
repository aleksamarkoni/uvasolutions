import sys

def processWeighs(res):
    left, right, weight = sys.stdin.readline().strip().split()
    if weight == "even":
        for c in left:
            res[ord(c) - ord('A')] = 0
        for c in right:
            res[ord(c) - ord('A')] = 0
    elif weight == "up":
        for c in left:
            res[ord(c) - ord('A')] += -1
        for c in right:
            res[ord(c) - ord('A')] += 1
    elif weight == "down":
        for c in left:
            res[ord(c) - ord('A')] += 1
        for c in right:
            res[ord(c) - ord('A')] += -1
    
n = int(sys.stdin.readline().strip())
for i in range(n):
    res = [0] * 12
    processWeighs(res)
    processWeighs(res)
    processWeighs(res)
    print(res)
    for c in res:
        if c >= 2:
            print(('A' + c) + " is the counterfeit coin and it is light");
            break
        if c <= -2:
            print(('A' + c) + " is the counterfeit coin and it is heavy");
            break
