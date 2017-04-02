import sys

'''
This code is correct, but please don't look at it :)
It will be efficent then to do loop untill you don't find time in the future
which is palindrom, going one minute by one minute in the future, but
so much corner cases and so much if else. It can be optimizes :)
'''

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
        if mm < 32:
            return hh, 32
        else:
            return 0, 0
    elif hh < 10:
        mmhigh = int(time[1][0])
        mmlow = int(time[1][1])
        if mmlow < hh:
            return hh, mmhigh * 10 + hh
        else:
            if mmhigh + 1 < 6:
                return hh, (mmhigh + 1) * 10 + hh
            else:
                if hh + 1 != 10:
                    return hh + 1, hh + 1
                else:
                    return 10, 1
    else:
        if mm < int(time[0][::-1]):
            if hh < 16 or hh > 19:
                return hh, int(time[0][::-1])
            else:
                return 20, 2
        else:
            if hh + 1 < 16 or hh + 1 > 19:
                return hh + 1, int(str(hh + 1)[::-1])
            else:
                return 20, 2
line = sys.stdin.readline().strip()
tc = int(line)
for test in range(tc):
    line = sys.stdin.readline().strip()
    rhh, rmm = calculate(line)
    print("{:02d}:{:02d}".format(rhh, rmm))
