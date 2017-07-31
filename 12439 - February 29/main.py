import sys

months = ['', 'January', 'February', 'March', 'April', 'May', 'June',
'July', 'August', 'September', 'October', 'November', 'December']

tc = int(sys.stdin.readline().strip())

for i in range(tc):
    print("Case {}: ".format(i+1), end='')
    line = sys.stdin.readline().strip()
    sm, sd, sy = line.replace(',', ' ').split()
    sm, sd, sy = months.index(sm), int(sd), int(sy)
    line = sys.stdin.readline().strip()
    em, ed, ey = line.replace(',', ' ').split()
    em, ed, ey = months.index(em), int(ed), int(ey)
    if sm > 2:
        sy = sy + 1
    if em < 2 or (em == 2 and ed < 29):
        ey = ey - 1
    ans = ey // 4 - (sy - 1) // 4
    ans = ans - ey // 100 + (sy - 1) // 100
    ans = ans + ey // 400 - (sy - 1) // 400
    print(ans)
