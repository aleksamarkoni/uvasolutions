import random
import sys
from functools import cmp_to_key


class Card(object):
    """Represents a standard playing card.

    Attributes:
      suit: integer 0-3
      rank: integer 1-13
    """

    suit_names = ["C", "D", "S", "H"]
    rank_names = [None, "2", "3", "4", "5", "6", "7",
                  "8", "9", "T", "J", "Q", "K", "A"]

    face_cards = ['J', 'Q', 'K', 'A']

    def __init__(self, suit='C', rank='2'):
        if suit not in Card.suit_names:
            raise ValueError("Wrong suit")
        if rank not in Card.rank_names:
            raise ValueError("Wrong rank")
        self.suit = suit
        self.rank = rank

    def is_face_card(self):
        return self.rank in Card.face_cards

    def num_of_cards_to_cover(self):
        if not self.is_face_card():
            return 0
        else:
            return Card.face_cards.index(self.rank)

    def __str__(self):
        """Returns a human-readable string representation."""
        return '%s%s' % (self.suit, self.rank)

    def cmp(self, other):
        """Compares this card to other, first by suit, then rank.

        Returns a positive number if this > other; negative if other > this;
        and 0 if they are equivalent.
        """
        t1 = Card.suit_names.index(self.suit), Card.rank_names.index(self.rank)
        t2 = Card.suit_names.index(other.suit), Card.rank_names.index(other.rank)
        if t1[0] < t2[0]:
            return -1
        elif t1[0] > t2[0]:
            return 1
        else:
            if t1[1] < t2[1]:
                return -1
            elif t1[1] > t2[1]:
                return 1
            else:
                return 0


class Deck(object):
    """Represents a deck of cards.

    Attributes:
      cards: list of Card objects.
    """

    def __init__(self):
        self.cards = []
        for suit in range(4):
            for rank in range(1, 14):
                card = Card(Card.suit_names[suit], Card.rank_names[rank])
                self.cards.append(card)

    def __str__(self):
        res = []
        for card in self.cards:
            res.append(str(card))
        return ' '.join(res)

    def add_card(self, card):
        """Adds a card to the deck."""
        self.cards.append(card)

    def add_cards_to_begining(self, deck):
        #print ("deck + player")
        #print(deck)
        #print(self)
        p = deck.cards[::-1]
        for card in self.cards:
           p.append(card)
        #p1 = []
        #for card in p:
        #    p1.append(str(card))
        #print(p1)
        self.cards = list(p)
        #print(deck)
        #print(self)

    def remove_card(self, card):
        """Removes a card from the deck."""
        self.cards.remove(card)

    def remove_all_cards(self):
        self.cards[:] = []

    def pop_card(self, i=-1):
        """Removes and returns a card from the deck.

        i: index of the card to pop; by default, pops the last card.
        """
        return self.cards.pop(i)

    def can_play(self):
        return self.cards

    def shuffle(self):
        """Shuffles the cards in this deck."""
        random.shuffle(self.cards)

    def sort(self):
        """Sorts the cards in ascending order."""
        self.cards.sort(key=cmp_to_key(Card.cmp))

    def move_cards(self, hand, num):
        """Moves the given number of cards from the deck into the Hand.

        hand: destination Hand object
        num: integer number of cards to move
        """
        for i in range(num):
            hand.add_card(self.pop_card())


class Hand(Deck):
    """Represents a hand of playing cards."""

    def __init__(self, label=''):
        self.cards = []
        self.label = label

while True:
    line = sys.stdin.readline().strip()
    if line == "#":
        break
    hand = Hand()
    dealer = line[0]

    for j in range(2):
        line = sys.stdin.readline().strip()
        for i in range(26):
            hand.add_card(Card(line[2 * i], line[2 * i + 1]))

    player_names = ['S', 'W', 'N', 'E']
    players = []
    for p in player_names:
        players.append(Hand(p))

    index = player_names.index(dealer)

    for card in hand.cards:
        index = (index + 1) % 4
        players[index].add_card(card)

    for p in players:
        p.sort()

    for player in players:
        print(player.label + ":", player)
