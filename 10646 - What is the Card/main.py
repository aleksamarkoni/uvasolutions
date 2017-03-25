import sys

class Card(object):
    """Represents a standard playing card.
    Attributes:
      suit: integer 0-3
      rank: integer 1-13
    """

    suit_names = ["C", "D", "H", "S"]
    rank_names = [None, None, "2", "3", "4", "5", "6", "7",
              "8", "9", "T", "J", "Q", "K", "A"]

    def __init__(self, rank="2", suit="C", ):
        self.rank = self.rank_names.index(rank)
        self.suit = self.suit_names.index(suit)

    def __str__(self):
        """Returns a human-readable string representation."""
        return '%s%s' % (self.rank_names[self.rank], self.suit_names[self.suit])

    def __lt__(self, other):
        return self.rank < other.rank

    def __eq__(self, other):
        return self.rank == other.rank

    def card_value(self):
        if self.rank >= 2 and self.rank <= 9:
            return self.rank
        else:
            return 10

class Deck(object):
    """Represents a deck of cards.

    Attributes:
      cards: list of Card objects.
    """

    def __init__(self):
        self.cards = []
        for suit in range(4):
            for rank in range(2, 15):
                card = Card(suit, rank)
                self.cards.append(card)

    def __str__(self):
        res = []
        for card in self.cards:
            res.append(str(card))
        return ' '.join(res)

    def add_card(self, card):
        """Adds a card to the deck."""
        self.cards.append(card)

    def add_deck(self, deck):
        self.cards = self.cards + deck.cards

    def remove_card(self, card):
        """Removes a card from the deck."""
        self.cards.remove(card)

    def pop_card(self, i=-1):
        """Removes and returns a card from the deck.

        i: index of the card to pop; by default, pops the last card.
        """
        return self.cards.pop(i)

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

    def split_deck(self, hand, num):
        """ splits the deck on two parts
        self will contain cards from up to and including num
        example, lets say that you have 10 cards in a deck and do the
        split_deck(hand, 4) the result will be
        that the deck will have 4 cards and hand will have 6
        and hand will have cards from num to end of the dec"""
        for i in range(num, len(self.cards)):
            hand.add_card(self.cards[i])
        del self.cards[num:]

class Hand(Deck):
    """Represents a hand of playing cards."""

    def __init__(self, cards, label=''):
        self.cards = []
        for c in cards.split():
            self.add_card(Card(c[0], c[1]))
        self.label = label


def what_is_the_card(hand):
    top_pile = Hand("")
    hand.split_deck(top_pile, len(hand.cards) - 25)
    y = 0
    for i in range(3):
        x = hand.pop_card().card_value()
        y = y + x
        for j in range(10 - x):
            hand.pop_card()
    hand.add_deck(top_pile)
    return hand.cards[y-1]

if __name__=="__main__":
    line = sys.stdin.readline().strip()
    test_cases = int(line)
    for test_case in range(0, test_cases):
        line = sys.stdin.readline().strip()
        hand = Hand(line[:155])
        print("Case {0}: {1}".format(test_case+1, what_is_the_card(hand)))
