import sys

def calculate(line):
    time = line.split(":")
    hh = int(time[0])
    mm = int(time[1])
    if hh == 0:
        if mm + 1 < 10:
            return hh, mm + 1
        elif mm + 1 > 55:
            return hh + 1, 1
        elif mm == 9:
            return hh, 11
        else:
            smm = str(mm)
            #print(smm)
            mm1 = int(smm[0])
            mm2 = int(smm[1])
            if mm2 < mm1:
                return hh, mm1*10 + mm1
            else:
                return hh, (mm1+1) * 10 + (mm1 + 1)
    elif hh == 23:
        if mm < 23:
            return hh, 23
        else:
            return 0, 1
    else:
        if mm < int(time[0][::-1]):
            return hh, int(time[0][::-1])
        else:
            return hh + 1, int(str(hh + 1)[::-1])

line = sys.stdin.readline().strip()
tc = int(line)
for test in range(tc):
    line = sys.stdin.readline().strip()
    rhh, rmm = calculate(line)
    print("{:02d}:{:02d}".format(rhh, rmm))
