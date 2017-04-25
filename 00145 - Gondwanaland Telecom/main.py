import sys

#Idea is to use 2 days interval instead of one.
#If the end time is less then start time just add 24 * 60, so end time is again in front of start time
#Thats better then using 1000 if else checks
#Divide the two days interval to zones and see if zone intersects with the call

charging_step_table = {
    'A' : [0.02, 0.10, 0.06, 0.02, 0.10, 0.06, 0.02],
    'B' : [0.05, 0.25, 0.15, 0.05, 0.25, 0.15, 0.05],
    'C' : [0.13, 0.53, 0.33, 0.13, 0.53, 0.33, 0.13],
    'D' : [0.17, 0.87, 0.47, 0.17, 0.87, 0.47, 0.17],
    'E' : [0.30, 1.44, 0.80, 0.30, 1.44, 0.80, 0.30],
}

intervals = [0, 8*60, 18*60, 22*60, 32*60, 42*60, 46*60, 48*60]
zone = [3, 1, 2, 3, 1, 2, 3]

while True:
    line = sys.stdin.readline().strip()
    if line == '#':
        break
    d = line.split()
    charge_step, number, hs, ms, he, me = d[0], d[1], d[2], d[3], d[4], d[5]
    start_time = int(hs) * 60 + int(ms)
    end_time = int(he) * 60 + int(me)
    if end_time <= start_time:
        end_time += 24 * 60
    charge_amounts = charging_step_table[charge_step]
    charge = 0
    zones = [0] * 3
    for i in range(1, len(intervals)):
        i_min, i_max = [max(intervals[i-1], start_time), min(intervals[i], end_time)]
        if i_max > i_min: # there was intersection
            intersection = i_max - i_min
            charge += intersection * charge_amounts[i-1]
            zones[zone[i-1] - 1] += intersection
    print("{:>10s}{:>6d}{:>6d}{:>6d}{:>3s}{:>8.2f}".format(number, zones[0], zones[1], zones[2], charge_step, charge))
