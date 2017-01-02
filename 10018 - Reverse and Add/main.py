import sys

def isPalindrom(n):
    '''returns if the given string representation of the number n is palindrom'''
    for i in range(len(n) // 2):
        if n[i] != n[len(n)-1-i]:
            return False
    return True

line = sys.stdin.readline().strip()
t = int(line)
for testCase in range(t):
    line = sys.stdin.readline().strip()
    a = line
    n = 0
    while not isPalindrom(a):
        a = str(int(a)+int(a[::-1]))
        n = n + 1
    print("{} {}".format(n, a))
