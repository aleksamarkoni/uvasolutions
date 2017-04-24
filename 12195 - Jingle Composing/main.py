import sys

notes = {
    'W' : 64,
    'H' : 32,
    'Q' : 16,
    'E' : 8,
    'S' : 4,
    'T' : 2,
    'X' : 1,
}

while True:
    line = sys.stdin.readline().strip()
    if line == '*':
        break
    composition = line[1:-1].split('/')
    total = 0
    for measure in composition:
        if sum([notes[tone] for tone in measure]) == 64:
            total += 1
    print(total)
