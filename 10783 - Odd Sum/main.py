import sys

line = sys.stdin.readline().strip()
t = int(line)
for testCase in range(t):
    line = sys.stdin.readline().strip()
    a = int(line)
    line = sys.stdin.readline().strip()
    b = int(line)
    if a % 2 == 0:
        a = a + 1
    sum = 0
    for i in range(a, b + 1, 2):
        sum = sum + i
    print('Case {}: {}'.format(testCase + 1, sum))
