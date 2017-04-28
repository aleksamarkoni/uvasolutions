import sys

cards_to_ints = {
  'A' : 1, '2' : 2, '3' : 3, '4' : 4, '5' : 5, '6' : 6, '7' : 7, '8' : 8,
  '9' : 9, 'T' : 10, 'J' : 11, 'Q' : 12, 'K' : 13,
  }

while True:
    line = sys.stdin.readline().strip()
    if line == "#":
        break
    deck = []
    deck += line.split()
    for i in range(3):
        deck += sys.stdin.readline().strip().split()
    piles = [[] for i in range(13)]
    i = 0
    for card in deck[::-1]:
        piles[i].append(card)
        i = (i + 1) % 13
    i = 12
    total = 0
    last_card = None
    while len(piles[i]) != 0:
        last_card = piles[i].pop()
        i = cards_to_ints[last_card[0]] - 1
        total += 1
    print("{:02},{}".format(total, last_card))
        
        
