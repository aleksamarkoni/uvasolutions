import sys

gmin = 0
gmax = 10
dishonest = False

while True:
    line = sys.stdin.readline().strip()
    guess = int(line)
    if guess == 0:
        break
    line = sys.stdin.readline().strip()

    if line == "too high":
        if guess <= gmin:
            dishonest = True
            #skip until right on
        elif guess < gmax:
            gmax = guess
    elif line == "too low":
        if guess >= gmax:
            dishonest = True
            #skip until right on
        elif guess > gmin:
            gmin = guess
    else:
        #print(gmin, gmax)
        if guess > gmin and guess < gmax and not dishonest:
            print("Stan may be honest")
        else:
            print("Stan is dishonest")
        gmin = 0
        gmax = 11
        dishonest = False
