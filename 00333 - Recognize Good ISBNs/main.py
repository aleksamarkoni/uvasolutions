import sys

while True:
    line = sys.stdin.readline()
    if not line:
        break
    line = line.strip()
    valid = True
    digits = []
    num_x = 0
    for c in line:
        if c.isdigit():
            digits.append(c)
            continue
        if c == 'X':
            num_x += 1
            digits.append('10')
            continue
        if c not in [' ', '\t', '\r', '\n', '-']:
            valid = False
            break
    if not valid or num_x > 1 or len(digits) != 10 or (num_x == 1 and digits[9] != '10'):
        print("{} is incorrect.".format(line))
    else:
        ps = 0
        s = 0
        for i in range(10):
            ps += int(digits[i])
            s += ps
        if s % 11 == 0:
            print("{} is correct.".format(line))
        else:
            print("{} is incorrect.".format(line))
