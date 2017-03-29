import sys

if __name__=="__main__":
    while True:
        test = sys.stdin.readline().strip()
        if test == "0 0 0 0 0":
            break
        #This array represents all cards that are free and taken
        cards = [True] * 53
        hand = [int(card) for card in test.split()]
        #mark cards in hand as taken inside cards array
        for card in hand:
            cards[card] = False
        her = hand[0:3]
        his = hand[3:]
        her.sort()
        his.sort()

        '''
        We have 3 cases in which no matter what prince do he can win:
        1)  He has 2 cards that are higher then every card that princes has
            In this case, he will win no matter what, so we should give him the
            lowest card possible. We can use cards array to search for that kind
            of card, because we can see which cards are taken and which are not
        2)  He has only one card which is greater than every card that princess has
            In this case in order for him to win, he needs on more card that is greater
            then every card that princess has. So we need to give him this card.
            The only problem to this is if there is no such card. But we will use cards
            list again to see if it is possible to find needed card.
        3)  He has 2 cards, that are greter than 2 cards that princess has
            (lets call this 2 cards "lower princess cards").
            This also means that princess has the greater card in the hand.
            In order for prince to win, we need to find one more card that is greater
            than "lower princess cards".

        Note:
            Some solution may overlap, which means, that in one hand, we can have
            all 3 cases. In situations like that, we should take the case which
            gives min card as a solution.
        '''
        card = []
        #case 1
        if his[0] > her[2]:
            for i in range(1, len(cards)):
                if cards[i]:
                    card.append(i)
                    break
        #case 2
        if his[1] > her[2]:
            for i in range(her[2] + 1, len(cards)):
                if cards[i]:
                    card.append(i)
                    break
        #case 3
        if his[0] > her[1]:
            for i in range(her[1] + 1, len(cards)):
                if cards[i]:
                    card.append(i)
                    break
        solution = -1
        if card:
            solution = min(card)
        print(solution)
