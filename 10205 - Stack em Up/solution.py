import collections

import sys

Card = collections.namedtuple('Card', ['rank', 'suit'])

class Deck:
    ranks = [str(n) for n in range(2, 11)] + "Jack Queen King Ace".split(' ')
    suits = 'Clubs Diamonds Hearts Spades'.split()

    def __init__(self):
        self._cards = [Card(rank, suit) for suit in self.suits
                       for rank in self.ranks]

    def __len__(self):
        return len(self._cards)

    def __getitem__(self, position):
        return self._cards[position]

    def __setitem__(self, position, value):
        self._cards[position] = value

    def __delitem__(self, position):
        del self._cards[position]

    def insert(self, position, value):
        self._cards.insert(position, value)

    def __str__(self):
        str = ""
        for card in self._cards:
            str += card.rank + " of " + card.suit + "\n"
        return str

numOfTestCases = int(input())
input()
for i in range(numOfTestCases):
    numOfDecks = int(input())
    data = []
    while len(data) < 52 * numOfDecks:
        data += [int(n) for n in input().split(' ')]
    listOfDecks = []
    for j in range(numOfDecks):
        listOfDecks.append(data[j * 52:(j+1)*52])

    deck = Deck()
    while True:
        line = sys.stdin.readline().strip()
        if line == '' or not line:
            break
        tmpDeck = Deck()
        for idx, s in enumerate(listOfDecks[int(line) - 1]):
            tmpDeck[idx] = deck[int(s)-1]
        deck = tmpDeck
    if i == numOfTestCases - 1:
        print(deck, end="")
    else:
        print(deck)
