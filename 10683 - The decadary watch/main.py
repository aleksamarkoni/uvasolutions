import sys

while True:
    line = sys.stdin.readline().strip()
    if not line:
        break
    hh, mm, ss, cc = [int(line[i:i+2]) for i in range(0, len(line), 2)]
    time = hh * 60 * 60 * 100 + mm * 60 * 100 + ss * 100 + cc
    convert = (10 * 100 * 100 * time) // (24 * 60 * 60) # we could cut some zeros, but who cares
    print("{:07}".format(convert))
