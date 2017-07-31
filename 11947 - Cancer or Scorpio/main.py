import sys
from datetime import date
from datetime import timedelta

limits = [0, 21, 20, 21, 21, 22, 22, 23, 22, 24, 24, 23, 23];

signs = ["", "capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", 
    "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn"]

line = sys.stdin.readline().strip()
TC = int(line)
for T in range(TC):
    print("{} ".format(T + 1), end = '')
    line = sys.stdin.readline().strip()
    cm, cd, cy = line[0:2], line[2:4], line[4:8]
    m, d, y = int(cm), int(cd), int(cy)
    startDate = date(y, m, d)
    endDate = startDate + timedelta(days=280)
    em, ed = endDate.month, endDate.day
    #%B will print full month name
    sign = signs[em] if ed < limits[em] else signs[em+1]
    print(endDate.strftime("%m/%d/%Y"), sign)
