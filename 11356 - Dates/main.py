import sys
from datetime import date
from datetime import timedelta

months = ['', 'January', 'February', 'March', 'April', 'May', 'June',
'July', 'August', 'September', 'October', 'November', 'December']

#really simple problem, we just need to take care about the dates in the years 
#that are just a year appart
line = sys.stdin.readline().strip()
TC = int(line)
for T in range(TC):
    print("Case {}: ".format(T + 1), end = '')
    line = sys.stdin.readline().strip()
    cy, cm, cd = line.split('-')
    y, m, d = int(cy), months.index(cm), int(cd)
    daysToAdd = int(sys.stdin.readline().strip())
    startDate = date(y, m, d)
    endDate = startDate + timedelta(days=daysToAdd)
    #%B will print full month name
    print(endDate.strftime("%Y-%B-%d"))
    
