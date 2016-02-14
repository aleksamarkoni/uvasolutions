import sys

song = ["Happy", "birthday", "to", "you",
"Happy", "birthday", "to", "you",
"Happy", "birthday", "to", "Rujia",
"Happy", "birthday", "to", "you"]

line = sys.stdin.readline()
n = int(line.strip())
nameList = []
j = 0
for i in range(n):
    name = sys.stdin.readline().strip()
    nameList.append(name)
    print(name + ": " + song[j])
    j = (j + 1) % 16
t = 0
for k in range(j, 16):
    print(nameList[t] + ": " + song[k])
    t = (t + 1) % n
