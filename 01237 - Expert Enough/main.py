import sys

tc = int(sys.stdin.readline().strip())
for test in range(tc):
    if (test > 0):
        print()
    d = int(sys.stdin.readline().strip())
    database = []
    for i in range(d):
        line = sys.stdin.readline().strip().split()
        manufacturer = (line[0], int(line[1]), int(line[2]))
        database.append(manufacturer)
    q = int(sys.stdin.readline().strip())
    for i in range(q):
        p = int(sys.stdin.readline().strip())
        count = 0
        name = ""
        for j in range(d):
            manufacturer = database[j]
            if (p >= manufacturer[1] and p <= manufacturer[2]):
                count = count + 1
                name = manufacturer[0]
                if (count > 1):
                    print("UNDETERMINED")
                    break;
        if (count == 1):
            print(name)
        elif count == 0:
            print("UNDETERMINED")
