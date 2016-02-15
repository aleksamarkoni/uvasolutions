import sys

line = sys.stdin.readline()
t = int(line.strip())
for test in range(t):
    line = sys.stdin.readline().strip()
    totalCalls = int(line)
    line = sys.stdin.readline().strip()
    calls = line.split()
    def callPrice(duration, perSec, cents):
        price = duration // perSec * cents + cents
        #if duration % perSec > 0:
        #    price = price + cents
        return price
    totalMilo = 0
    totalJuice = 0
    for call in calls:
        totalMilo = totalMilo + callPrice(int(call), 30, 10)
        totalJuice = totalJuice + callPrice(int(call), 60, 15)
    company = ""
    if totalMilo == totalJuice:
        company = "Mile Juice " + str(totalMilo)
    elif totalMilo < totalJuice:
        company = "Mile " + str(totalMilo)
    else:
        company = "Juice " + str(totalJuice)
    print("Case {}: {}".format(test + 1, company))
