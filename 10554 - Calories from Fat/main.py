import sys

'''
Simple simulation problem, use calories as a standpoint,
calculate toatal_fat usage per row, and total_calories per row.
And then sum all the fat_per_row and all the calories_per row and see the percent
'''

c_per_g = [9, 4, 4, 4, 7]

while True:
    line = sys.stdin.readline().strip()
    if line == '-':
        break
    sum_fat = 0
    sum_total = 0
    while True:
        data = line.split()
        sum_procent = 0
        sum_calories = 0
        for i, d in enumerate(data):
            if d[-1] == '%':
                sum_procent += int(d[:-1])
            elif d[-1] == 'g':
                sum_calories += int(d[:-1]) * c_per_g[i]
            elif d[-1] == 'C':
                sum_calories += int(d[:-1])
        #print(sum_calories, sum_procent)
        total_for_this_raw = (100.0 * sum_calories) / (100.0 - sum_procent)
        total_fat_for_this_raw = 0
        fat_data = data[0]
        if fat_data[-1] == '%':
            total_fat_for_this_raw =  int(fat_data[:-1]) * (total_for_this_raw / 100.0)
        elif fat_data[-1] == 'g':
            total_fat_for_this_raw = int(fat_data[:-1]) * c_per_g[0]
        elif fat_data[-1] == 'C':
            total_fat_for_this_raw = int(fat_data[:-1])
        #print("per_row_total ", total_fat_for_this_raw, total_for_this_raw)
        sum_total += total_for_this_raw
        sum_fat += total_fat_for_this_raw
        line = sys.stdin.readline().strip()
        if line == '-':
            break
    #print("totals ", sum_fat, sum_total)
    res = int(round(( sum_fat * 100.0) / sum_total))
    print("{}%".format(res))
