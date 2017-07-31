import sys

while True:
    line = sys.stdin.readline().strip()
    if line == '':
        break
    k, m = [int(x) for x in line.split()]
    #check out the forum
    seconds = (((86400 - k) * 43200 ) // abs(k - m)) % 43200 + 30 # +30 will round it up :)
    hour = (seconds // 3600) % 12
    hour = 12 if hour == 0 else hour
    minutes = (seconds // 60) % 60
    print("{} {} {:02d}:{:02d}".format(k, m, hour, minutes))
