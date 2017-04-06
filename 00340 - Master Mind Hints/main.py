import sys
t = 0
while True:
    line = sys.stdin.readline().strip()
    length = int(line)
    if length == 0:
        break
    t += 1
    print("Game {}:".format(t))
    originalCode = sys.stdin.readline().strip().split()
    while True:
        guess = sys.stdin.readline().strip().split()
        if (guess[0] == '0'):
            break
        code = originalCode[:]
        cp = 0
        wp = 0

        for i in range(len(code)):
            if code[i] == guess[i]:
                cp += 1
                code[i] = '0'
                guess[i] = '0'

        for i in range(len(code)):
            if code[i] != '0':
                for j in range(len(guess)):
                    if code[i] == guess[j]:
                        wp += 1
                        code[i] = '0'
                        guess[j] = '0'
                        break
        print("    ({},{})".format(cp, wp))
