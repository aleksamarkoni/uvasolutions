import sys
import math

#We have fore cases to cover, but notice that if you check if something is
#palindrom then case 1 and 3 are the same and also case 3 and 4 are the same
line = sys.stdin.readline().strip()
TC = int(line)
for T in range(TC):
    print("Case #{}:".format(T + 1))
    line = sys.stdin.readline().strip()
    #filter out everything except letters
    sentence = [c.lower() for c in line if c.isalpha()]
    #check if the len is complete squeare number
    lenght = len(sentence)
    n = int(math.sqrt(lenght))
    if (n * n) != lenght:
        print("No magic :(")
        continue

    #this will cover cases 1 and 3
    if sentence != sentence[::-1]:
        print("No magic :(")
        continue

    sentenceByColums = []
    for j in range(n):
        for i in range(n):
            sentenceByColums.append(sentence[i * n + j])

    #case 2
    if sentence != sentenceByColums:
        print("No magic :(")
        continue

    #case 4
    if sentence != sentenceByColums[::-1]:
        print("No magic :(")
        continue
    print(n)
