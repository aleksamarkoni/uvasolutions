import sys

month = [0, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5]
days = ["Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"]

tc = int(sys.stdin.readline().strip())

for i in range(tc):
    line = sys.stdin.readline().strip()
    m, d = [int(x) for x in line.split()]
    d = (d + month[m]) % 7
    print(days[d])
