import sys

#Python has built in BigInteger

first_line = True
while True:
    #read line
    line = sys.stdin.readline()
    if not line:
        break
    #if not eof, check if the line is empty
    line = line.strip()
    if not line:
        continue
    if not first_line:
        print()
    first_line = False
    year = int(line)
    ordinary = True
    leap = False
    if year % 4 == 0 and year % 100 != 0 or year % 400 == 0:
        print("This is leap year.")
        ordinary = False
        leap = True
    if year % 15 == 0:
        print("This is huluculu festival year.")
        ordinary = False
    if leap and year % 55 == 0:
        print("This is bulukulu festival year.")
        ordinary = False
    if ordinary:
        print("This is an ordinary year.")
