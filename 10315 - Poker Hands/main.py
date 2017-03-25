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


class Hand(Deck):
    """Represents a hand of playing cards."""

    def __init__(self, cards, label=''):
        self.cards = []
        for c in cards.split():
            self.add_card(Card(c[0], c[1]))
        self.label = label

    def number_of_suits(self):
        """ Returns the number of diffrent suits inside hand"""
        suits = []
        for i in self.cards:
            if i.suit not in suits:
                suits.append(i.suit)
        return len(suits)

    def diffrent_ranks(self):
        ranks = {}
        for card in self.cards:
            ranks[card.rank] = ranks.get(card.rank, 0) + 1
        return ranks

    def is_straight_flash(self):
        straight = self.is_straight()
        flash = self.is_flush()
        if flash[0] and straight[0]:
            return (True, straight[1])
        else:
            return (False, None)

    def is_four_of_a_kind(self):
        diff_ranks = self.diffrent_ranks()
        max_rank = max(diff_ranks.values())
        #this is really strange, but who knows what kind of input they have there
        if max_rank >= 4:
            value = list(diff_ranks.keys())[list(diff_ranks.values()).index(max_rank)]
            return (True, [value])
        else:
            return (False, None)

    def is_full_house(self):
        diff_ranks = self.diffrent_ranks()
        dict_of_values = diff_ranks.values()
        max_rank = max(dict_of_values)
        if len(dict_of_values) == 2 and max_rank == 3:
            value = list(diff_ranks.keys())[list(diff_ranks.values()).index(max_rank)]
            return (True, [value])
        else:
            return (False, None)

    def is_flush(self):
        if self.number_of_suits() == 1:
            l = sorted([card.rank for card in self.cards], reverse=True)
            return (True, l)
        else:
            return (False, None)

    def is_straight(self):
        self.sort()
        ranks = self.diffrent_ranks()
        diff = self.cards[-1].rank - self.cards[0].rank
        if len(ranks) is 5 and diff is 4:
            return (True, [max(self.cards).rank])
        else:
            return (False, None)

    def is_three_of_a_kind(self):
        diff_ranks = self.diffrent_ranks()
        max_rank = max(diff_ranks.values())
        if max_rank == 3:
            value = list(diff_ranks.keys())[list(diff_ranks.values()).index(max_rank)]
            return (True, [value])
        else:
            return (False, None)

    def is_two_diff_pairs(self):
        diff_ranks = self.diffrent_ranks()
        num = 0
        for rank_value in diff_ranks.values():
            if rank_value >= 2:
                num = num + 1
        if num == 2:
            pairs = []
            single_value = -1
            for r, c in diff_ranks.items():
                if c == 4:
                    pairs.append(r)
                    pairs.append(r)
                elif c == 3:
                    pairs.append(r)
                    single_value = r
                elif c == 2:
                    pairs.append(r)
                else:
                    single_value = r
            pairs.sort(reverse=True)
            pairs.append(single_value)
            return (True, pairs)
        else:
            return (False, None)

    def is_pair(self):
        diff_ranks = self.diffrent_ranks()
        max_rank = max(diff_ranks.values())
        if max_rank >= 2:
            pairs = []
            par_value = -1
            par_value_1 = -1
            for r, c in diff_ranks.items():
                if c == 4:
                    pairs.append(r)
                    pairs.append(r)
                    par_value = r
                elif c == 3:
                    pairs.append(r)
                    par_value = r
                elif c == 2:
                    if max_rank == 3:
                        pairs.append(r)
                    else:
                        if r > par_value:
                            par_value_1 = par_value
                            par_value = r
                        elif r > par_value_1:
                            par_value_1 = r
                else:
                    pairs.append(r)
            if par_value_1 != -1:
                pairs.append(par_value_1)
                pairs.append(par_value_1)
            pairs.sort(reverse=True)
            pairs.insert(0, par_value)
            return (True, pairs)
        else:
            return (False, None)

    def max_hand_value(self):
        return [self.is_straight_flash(), \
        self.is_four_of_a_kind(), \
        self.is_full_house(), \
        self.is_flush(), \
        self.is_three_of_a_kind(), \
        self.is_two_diff_pairs(), \
        self.is_pair()]

def calculate_winner(black_player_hand, white_player_hand):
    black_best_hand = black_player_hand.max_hand_value()
    white_best_hand = white_player_hand.max_hand_value()
    winner = None
    for i in range(7):
        (b_won, b_card) = black_best_hand[i]
        (w_won, w_card) = white_best_hand[i]
        if b_won and not w_won:
            winner = "Black wins."
            break
        elif w_won and not b_won:
            winner = "White wins."
            break
        elif b_won and w_won:
            for i in range(len(b_card)):
                b_best = b_card[i]
                w_best = w_card[i]
                if b_best > w_best:
                    winner = "Black wins."
                    break
                elif b_best < w_best:
                    winner = "White wins."
                    break
            if winner == None:
                winner = "Tie."
            break
    if winner == None:
        black = sorted([card.rank for card in black_player_hand.cards], reverse=True)
        white = sorted([card.rank for card in white_player_hand.cards], reverse=True)
        for i in range(len(black)):
            b_best = black[i]
            w_best = white[i]
            if b_best > w_best:
                winner = "Black wins."
                break
            elif b_best < w_best:
                winner = "White wins."
                break
        if winner == None:
            winner = "Tie."
    return winner

if __name__=="__main__":
    while True:
        line = sys.stdin.readline().strip()
        if line is "":
            break
        black_player = Hand(line[0:14], "Black")
        white_player = Hand(line[15:], "White")
        print(calculate_winner(black_player, white_player))
