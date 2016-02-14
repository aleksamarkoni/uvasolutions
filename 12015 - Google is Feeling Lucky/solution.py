import sys

line = sys.stdin.readline()
tc = int(line.strip())
for i in range(tc):
    rez = []
    maxRelevance = 0
    for j in range(10):
        line = sys.stdin.readline()
        data = line.split()
        relevance = int(data[1])
        if (relevance > maxRelevance):
            rez.clear()
            maxRelevance = relevance
        elif (relevance < maxRelevance):
            continue
        rez.append(data[0])
    print("Case #{}:".format(i+1))
    for url in rez:
        print(url)

