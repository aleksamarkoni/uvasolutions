import sys

tc = int(sys.stdin.readline().strip())

#this can be done a lot simpler then this, just buy substracting from 12 and 60
#but I was in a hurry
for i in range(tc):
    line = sys.stdin.readline().strip()
    hh, mm = [int(x) for x in line.split(':')]
    minutes = (hh * 60 + mm) % 720
    diff = 12 * 60 - minutes
    newhh = diff // 60
    newhh = 12 if newhh == 0 else newhh
    newmm = diff % 60
    print("{:02d}:{:02d}".format(newhh, newmm))
