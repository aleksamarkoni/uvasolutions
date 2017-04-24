import sys

while True:
    line = sys.stdin.readline().strip()
    pages = int(line)
    if pages == 0:
        break
    print("Printing order for {} pages:".format(pages))
    num = int((pages + 3) / 4)
    for i in range(num):
        front_first = (num * 4) - (i * 2)
        front_first = "Blank" if front_first > pages else front_first
        front_sec = (i+1) * 2 - 1
        front_sec = "Blank" if front_sec > pages else front_sec
        back_first = (i + 1) * 2
        back_first = "Blank" if back_first > pages else back_first
        back_sec = (num * 4) - (i * 2) - 1
        back_sec = "Blank" if back_sec > pages else back_sec
        if front_first != "Blank" or front_sec != "Blank":
            print("Sheet {}, front: {}, {}".format((i+1), front_first, front_sec))
        if back_first != "Blank" or back_sec != "Blank":
            print("Sheet {}, back : {}, {}".format((i+1), back_first, back_sec))
