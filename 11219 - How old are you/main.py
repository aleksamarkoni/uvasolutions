import sys

#really simple problem, we just need to take care about the dates in the years 
#that are just a year appart
line = sys.stdin.readline().strip()
TC = int(line)
for T in range(TC):
    print("Case #{}: ".format(T + 1), end = '')
    sys.stdin.readline()
    line = sys.stdin.readline().strip()
    cd, cm, cy = [int(x) for x in line.split('/')]
    line = sys.stdin.readline().strip()
    bd, bm, by = [int(x) for x in line.split('/')]
    age = cy - by
    if not (cm > bm or (cm == bm and cd >= bd)):
        age -= 1
    #print(age)
    if age < 0:
        print("Invalid birth date")
    elif age > 130:
        print("Check birth date")
    else:
        print(age)
    
