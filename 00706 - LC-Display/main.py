import sys

# --    top row - all dots are on the middle if the number has this row
#|  |   top half row this row can have left and right edge
#|  |   top half row -||-
# --    mid row is similar as top row
#|  |   down half row similar as top half
#|  |   down half row 
# --    down row - similar as mid and top row
up   = ['-', ' ', '-', '-', ' ', '-', '-', '-', '-', '-'] #for the top row
thl  = ['|', ' ', ' ', ' ', '|', '|', '|', ' ', '|', '|']
thr  = ['|', '|', '|', '|', '|', ' ', ' ', '|', '|', '|']
mid  = [' ', ' ', '-', '-', '-', '-', '-', ' ', '-', '-']
dhl  = ['|', ' ', '|', ' ', ' ', ' ', '|', ' ', '|', ' ']
dhr  = ['|', '|', ' ', '|', '|', '|', '|', '|', '|', '|']
down = ['-', ' ', '-', '-', ' ', '-', '-', ' ', '-', '-']
    
while True:
    s, n = sys.stdin.readline().strip().split()
    if s == '0' and n == '0':
        break
    s = int(s)
    n = [int(d) for d in n] # convert from string to list of digits
    #top row
    print(' '.join([" {} ".format(up[d] * s) for d in n]))
    #top half
    for i in range(s):
        print(' '.join(["{}{}{}".format(thl[d], (' ' * (s)), thr[d]) for d in n]))
    #mid row
    print(' '.join([" {} ".format(mid[d] * s) for d in n]))
    #down half
    for i in range(s):
        print(' '.join(["{}{}{}".format(dhl[d], (' ' * (s)), dhr[d]) for d in n]))
    #down row
    print(' '.join([" {} ".format(down[d] * s) for d in n]))
    print()
    
