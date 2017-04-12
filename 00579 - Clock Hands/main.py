import sys
while True:
    line = sys.stdin.readline().strip()
    data = line.split(':')
    hour = float(data[0])
    minute = float(data[1])
    if hour == 0 and minute == 0:
        break
    #the hour hand will move a bit over the exact hour, based on number of minutes
    hour += minute / 60.0
    hAngle = hour * 30.0;
    mAngle = minute * 6.0;
    diff = abs(hAngle - mAngle)
    if diff > 180:
        diff = 360 - diff;
    print("{:.3f}".format(diff))
