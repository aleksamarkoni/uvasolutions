import sys

while True:
    line = sys.stdin.readline().strip()
    a, b = line.split()
    if a is '0' and b is '0':
        break
    #append zeros in front of the shorter number, so we get the strings with the same length
    if (len(a) < len(b)):
        a = '0' * (len(b) - len(a)) + a
    else:
        b = '0' * (len(a) - len(b)) + b
    numOfCarry = 0
    carry = 0
    for i in range(len(a) - 1, -1, -1):
        if int(a[i]) + int(b[i]) + carry >= 10:
            numOfCarry = numOfCarry + 1
            carry = 1
        else:
            carry = 0
    if (numOfCarry == 0):
        print('No carry operation.')
    elif (numOfCarry == 1):
        print('1 carry operation.')
    else:
        print('{} carry operations.'.format(numOfCarry))
