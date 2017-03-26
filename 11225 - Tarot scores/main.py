import sys

if __name__=="__main__":
    num_of_hands = int(sys.stdin.readline().strip())
    for hand in range(num_of_hands):
        cards = int(sys.stdin.readline().strip())
        num_of_oudlers = 0
        num_of_points = 0
        for c in range(cards):
            card = sys.stdin.readline().strip()
            if card == "fool" or card == "one of trumps" or card == "twenty-one of trumps":
                num_of_oudlers = num_of_oudlers + 1
                num_of_points = num_of_points + 4.5
            else:
                card_info = card.split()
                rank = card_info[0]
                if rank == "king":
                    num_of_points = num_of_points + 4.5
                elif rank == "queen":
                    num_of_points = num_of_points + 3.5
                elif rank == "knight":
                    num_of_points = num_of_points + 2.5
                elif rank == "jack":
                    num_of_points = num_of_points + 1.5
                else:
                    num_of_points = num_of_points + 0.5
        points_based_on_num_of_oudlers = [56, 51, 41, 36]
        points_needed_to_win = points_based_on_num_of_oudlers[num_of_oudlers]
        print("Hand #{}".format(hand+1))
        if num_of_points >= points_needed_to_win:
            print("Game won by {:.0f} point(s).".format(num_of_points - points_needed_to_win))
        else:
            print("Game lost by {:.0f} point(s).".format(points_needed_to_win - num_of_points))
        if hand != (num_of_hands-1):
            print()
