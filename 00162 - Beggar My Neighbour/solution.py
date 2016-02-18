import random
import sys


class Card(object):
    """Represents a standard playing card.
    
    Attributes:
      suit: integer 0-3
      rank: integer 1-13
    """

    suit_names = ["C", "D", "H", "S"]
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

    def num_of_cards_to_cover(self):
        if not self.is_face_card():
            return 0
        else:
            return Card.face_cards.index(self.rank)

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
    
    def __init__(self, label=''):
        self.cards = []
        self.label = label

while True:
    line = sys.stdin.readline().strip()
    if line == "#":
        break
    nonDealer = Hand()
    dealer = Hand()
    data = line.split()
    for i in range(3):
        data.extend(sys.stdin.readline().strip().split())
    for i in range(0, len(data), 2):
        nonDealer.add_card(Card(data[i][0], data[i][1]))
        dealer.add_card(Card(data[i+1][0], data[i+1][1]))
    
    stack = Hand()
    faceStage = False
    numCover = 0
    
    def changePlayer(curPlayer):
       if curPlayer == nonDealer:
           return dealer
       else:
           return nonDealer    

    def playTurn(player):
        global numCover
        global faceStage
        global stack
        if player.can_play():
           topCard = player.pop_card()
           stack.add_card(topCard)
           if topCard.is_face_card():
               faceStage = True
               numCover = topCard.num_of_cards_to_cover()
               return 1
           elif numCover == 0:
               if faceStage:
                  otherPlayer = changePlayer(player)
                  #print(otherPlayer)
                  otherPlayer.add_cards_to_begining(stack)
                  stack.remove_all_cards()
                  #print(stack.cards)
                  #print(otherPlayer.cards)
                  #print(otherPlayer.cards)
                  faceStage = False
               return 1
           else:
               numCover = numCover - 1
               return 0                    
        else:
            return -1

    player = nonDealer
    while True:
        res = playTurn(player)
        if res == 1:
            player = changePlayer(player)
        elif res == -1:
           break
    player = changePlayer(player)
    if player == dealer:
        print("1{:-3}".format(len(player.cards)))
    else:
        print("2{:-3}".format(len(player.cards)))
   
           
        
            
