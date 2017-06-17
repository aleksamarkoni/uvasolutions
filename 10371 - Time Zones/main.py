import sys

zones = { "UTC" : 0, "GMT" : 0, "BST" : 1, "IST" : 1, "WET" : 0, "WEST" : 1, 
    "CET" : 1, "CEST" : 2, "EET" : 2, "EEST" : 3, "MSK" : 3, "MSD" : 4, 
    "AST" : -4, "ADT" : -3, "NST" : -3.5, "NDT" : -2.5, "EST" : -5, "EDT" : -4,
    "CST" : -6, "CDT" : -5, "MST" : -7, "MDT" : -6, "PST" : -8, "PDT" : -7,
    "HST" : -10, "AKST" : -9, "AKDT" : -8, "AEST" : 10, "AEDT" : 11, 
    "ACST" : 9.5, "ACDT" : 10.5, "AWST" : 8, }
    
n = int(sys.stdin.readline().strip())
for i in range(n):
    line = sys.stdin.readline().strip()
    data = line.split()
    tz = None
    time = None
    if data[0] == 'noon':
        time = 12 * 60 - zones[data[1]] * 60
        tz = data[2]
    elif data[0] == 'midnight':
        time = 24 * 60 - zones[data[1]] * 60
        tz = data[2]
    else:
        hh, mm = [int(x) for x in data[0].split(':')]
        hh = 0 if hh == 12 else hh # change that first hour, look at the problem input for a.m, p.m explanation
        adding = 0 if data[1] == "a.m." else 12 * 60
        time = hh * 60 + mm + adding - zones[data[2]] * 60
        tz = data[3]
    time = (time + 24 * 60) % (24 * 60) # we add 24 * 60 to time to avoid negative times
    time = (time + zones[tz] * 60 + 24 * 60) % (24 * 60)
    hh = int(time // 60)
    mm = int(time % 60)
    if hh == 0 and mm == 0:
        print("midnight")
    elif hh == 12 and mm == 0:
        print("noon")
    elif hh < 12:
        hh = 12 if hh == 0 else hh # for the first hour, look at previous comment
        print("{:d}:{:02d} a.m.".format(hh, mm))
    else:
        hh = 12 if hh - 12 == 0 else hh - 12
        print("{:d}:{:02d} p.m.".format(hh, mm))    
