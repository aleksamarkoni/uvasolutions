import random
import sys


class Card(object):
    """Represents a standard playing card.

    Attributes:
      suit: integer 0-3
      rank: integer 1-13
    """

    suit_names = ["S", "H", "D", "C"]
    rank_names = [None, "A", "2", "3", "4", "5", "6", "7",
                  "8", "9", "T", "J", "Q", "K"]

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

    def num_of_points_based_on_face(self):
        if not self.is_face_card():
            return 0
        else:
            return Card.face_cards.index(self.rank) + 1

    def __str__(self):
        """Returns a human-readable string representation."""
        return '%s%s' % (self.suit, self.rank)

    def __cmp__(self, other):
        """Compares this card to other, first by suit, then rank.

        Returns a positive number if this > other; negative if other > this;
        and 0 if they are equivalent.
        """
        t1 = Card.suit_names.index(self.suit), Card.rank_names.index(self.rank)
        t2 = Card.suti_names.index(other.suit), Card.rank_names.index(other.rank)
        return cmp(t1, t2)


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
        p = deck.cards[::-1]
        for card in self.cards:
           p.append(card)
        self.cards = list(p)

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
        self.cards.sort()

    def move_cards(self, hand, num):
        """Moves the given number of cards from the deck into the Hand.

        hand: destination Hand object
        num: integer number of cards to move
        """
        for i in range(num):
            hand.add_card(self.pop_card())

    def numberOfCardsInTheSuit(self, suit):
        num = 0
        for card in self.cards:
            if card.suit is suit:
                num += 1
        return num

    def suitWithMostCards(self):
        maxNum = -1
        maxsuit = ''
        for suit in Card.suit_names:
            num = self.numberOfCardsInTheSuit(suit)
            if num > maxNum:
                maxNum = num
                maxsuit = suit
        return maxsuit

class Hand(Deck):
    """Represents a hand of playing cards."""

    def __init__(self, label=''):
        self.cards = []
        self.label = label

    def calculatePoints(self):
        """
        calculate points based on the rules given in the problem description
        """
        points = 0
        points += self.addPointsBasedOnCardFace()
        points -= self.substractPointsForKings()
        points -= self.substractPointsForQuins()
        points -= self.substractPointsForJacks()
        points += self.addPointsForTheSuits()
        return points

    def addPointsBasedOnCardFace(self):
        points = 0
        for card in self.cards:
            points = points + card.num_of_points_based_on_face()
        return points

    def substractPointsForKings(self):
        num = 0
        for card in self.cards:
            if card.rank is 'K' and self.numberOfCardsInTheSuit(card.suit) is 1:
                num += 1
        return num

    def substractPointsForQuins(self):
        num = 0
        for card in self.cards:
            if card.rank is 'Q' and self.numberOfCardsInTheSuit(card.suit) in [1, 2]:
                num += 1
        return num

    def substractPointsForJacks(self):
        num = 0
        for card in self.cards:
            if card.rank is 'J' and self.numberOfCardsInTheSuit(card.suit) in [1, 2, 3]:
                num += 1
        return num

    def addPointsForTheSuits(self):
        points = 0
        for s in Card.suit_names:
            num = self.numberOfCardsInTheSuit(s)
            if num is 2:
                points += 1
            elif num in [0, 1]:
                points += 2
        return points

    def isSuitStoped(self, suit):
        for card in self.cards:
            if card.suit is not suit:
                continue
            if card.rank is 'A':
                return True
            if card.rank is 'K' and self.numberOfCardsInTheSuit(suit) >= 2:
                return True
            if card.rank is 'Q' and self.numberOfCardsInTheSuit(suit) >= 3:
                return True
        return False

    def isNoTrump(self):
        points = self.calculatePoints()
        #remove the points for rules 5, 6, 7
        points -= self.addPointsForTheSuits()
        if points < 16:
            return False
        for suit in Card.suit_names:
            if not self.isSuitStoped(suit):
                return False
        return True

    def isSuitBid(self):
        points = self.calculatePoints()
        if points < 14:
            return False
        return True

    def checkWhatToPlay(self):
        if self.isNoTrump():
            return "BID NO-TRUMP"
        if self.isSuitBid():
            return "BID " + self.suitWithMostCards()
        return "PASS"

while True:
    line = sys.stdin.readline().strip()
    if not line:
        break
    hand = Hand('Hand')
    data = line.split()
    for card in data:
        hand.add_card(Card(card[1], card[0]))
    print(hand.checkWhatToPlay())



