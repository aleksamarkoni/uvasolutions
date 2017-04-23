import sys
import math

ds = 0
while True:
    line = sys.stdin.readline().strip()
    if line == '0':
        break
    ds += 1
    t = 0
    last_five_sec = 0
    file_size = int(line)
    print("Output for data set {}, {} bytes:".format(ds, file_size))
    while file_size > 0:
        line = sys.stdin.readline().strip()
        sent =  int(line)
        last_five_sec += sent
        file_size -= sent
        t += 1
        if t % 5 == 0:
            if last_five_sec == 0:
                print("   Time remaining: stalled")
            else:
                res = int(math.ceil(file_size * 5.0 / last_five_sec))
                print("   Time remaining: {} seconds".format(res))
            last_five_sec = 0
    print("Total time: {} seconds".format(t))
    print()
