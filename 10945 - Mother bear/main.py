import sys

while True:
    line = sys.stdin.readline().strip()
    if line == 'DONE':
        break
    sentence = [c.lower() for c in line if c.isalpha()]
    #this will reverse sting and check if palindrom
    if sentence == sentence[::-1]:
        print("You won't be eaten!")
    else:
        print("Uh oh..")
