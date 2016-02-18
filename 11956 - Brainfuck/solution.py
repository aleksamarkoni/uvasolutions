import sys

line = sys.stdin.readline().strip()
T = int(line)
for test in range(T):
    line = sys.stdin.readline().strip()
    display = [0] * 100
    pointer = 0
    for command in line:
        if command == '>':
            pointer = (pointer + 1) % 100
        elif command == '<':
            pointer = (pointer -1) % 100
        elif command == '+':
            display[pointer] = (display[pointer] + 1) % 256
        elif command == '-':
            display[pointer] = (display[pointer] - 1) % 256
    print("Case {}: ".format(test + 1) + " ".join("{:02X}".format(k) for k in display))
