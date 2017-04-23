import sys

accounts = {}

while True:
    line = sys.stdin.readline().strip()
    if line[:3] == '000':
        break
    accounts[line[:3]] = line[3:]

def processTransaction(transaction):
    totals = []
    acc = []
    for record in transaction:
        an = record[3:6]
        amount = float(record[6:]) / 100
        i = acc.index(an) if an in acc else None
        if i:
            totals[i] += amount
        else:
            totals.append(amount)
            acc.append(an)
    total = sum(totals)
    if total != 0:
        print("*** Transaction {:3s} is out of balance ***".format(transaction[0][:3]))
        for an, amount in zip(acc, totals):
            print("{:3s} {:30s} {:10.2f}".format(an, accounts[an], amount))
        print("{:3s} {:30s} {:10.2f}".format("999", "Out of Balance", -total))
        print()


transaction = []
while True:
    line = sys.stdin.readline().strip()
    if (line[:3]) == '000':
        break
    if len(transaction) == 0 or line[:3] == transaction[-1][:3]:
        transaction.append(line)
    else:
        processTransaction(transaction)
        transaction = []
        transaction.append(line)
processTransaction(transaction)
