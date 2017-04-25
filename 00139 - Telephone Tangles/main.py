import sys

table = {}

while True:
    line = sys.stdin.readline().strip()
    if line == '000000':
        break
    first_space = line.index(' ')
    code = line[:first_space]
    dolar_sign = line.index('$')
    locality_name = line[first_space:dolar_sign].strip()
    price = float(line[dolar_sign+1:])/100
    table[code] = (locality_name, price)

while True:
    line = sys.stdin.readline().strip()
    if line == '#':
        break
    code, duration = line.split()
    found = None
    for k in table:
        if code.startswith(k):
            len_code = len(code) - len(k)
            if code[0] == '0' and code[1] == '0' and len_code >= 4 and len_code <= 10:
                found = k
                break
            if code[0] == '0' and code[1] != '0' and len_code >= 4 and len_code <= 7:
                found = k
                break
    if code[0] != '0':
        print("{:16}{:25}{:>10}{:>5}{:>6.2f}{:>7.2f}".format(code, "Local", code, duration, 0.00, 0.00))
    elif found:
        print("{:16}{:25}{:>10}{:>5}{:>6.2f}{:>7.2f}".format(code, table[k][0], code[len(k):], duration, table[k][1], (int(duration) * table[k][1])))
    else:
        print("{:16}{:25}{:>10}{:>5}{:>13.2f}".format(code, "Unknown", '', duration, -1.00))
