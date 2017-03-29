import unittest
from main import Hand
from main import calculate_winner

class TestHand(unittest.TestCase):

    def test_isStraightFlash(self):
        hand = Hand("2S 3S 4S 5S 6S")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [6])

        hand = Hand("TS JS QS KS AS")
        result = hand.is_straight_flash();
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        # hand is not sorted
        hand = Hand("JS AS QS TS KS")
        result = hand.is_straight_flash();
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("TS JS QH KH AH")
        result = hand.is_straight_flash();
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("2S 3S 4S 5S 7S")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("2S TD JD QS KS")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isFourOfAKind(self):
        hand = Hand("AS AD AH AC KS")
        result = hand.is_four_of_a_kind()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("TS AD AH AC KS")
        result = hand.is_four_of_a_kind()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isFullHouse(self):
        hand = Hand("AS AD AH TC TS")
        result = hand.is_full_house()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("2S 4D 4H 2C 4S")
        result = hand.is_full_house()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [4])

        hand = Hand("TS AD AH TC KS")
        result = hand.is_full_house()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("TS TD AH TC KS")
        result = hand.is_full_house()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("TS TD AH KC KS")
        result = hand.is_full_house()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)


    def test_flush(self):
        hand = Hand("AS TS 9S 2S 5S")
        result = hand.is_flush()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14, 10, 9, 5, 2])

        hand = Hand("TS AD AH TC KS")
        result = hand.is_flush()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("TS AS AS TS KC")
        result = hand.is_flush()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isStraight(self):
        hand = Hand("AS TD JC QH KS")
        result = hand.is_straight()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("9S TD JC QH 8S")
        result = hand.is_straight()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [12])

        hand = Hand("AS 9D JC QH KS")
        result = hand.is_straight()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isThreeOfAKind(self):
        hand = Hand("AS AD AC QH KS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("TS AD TC TH KS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [10])

        hand = Hand("AS AD JC QH KS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        hand = Hand("2S 2D JC QH QS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isTwoPairs(self):
        hand = Hand("AS AD QC QH KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [14, 12, 13])

        hand = Hand("3S 3D 2C 2H KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [3, 2, 13])

        hand = Hand("AS AD JC QH KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

        #this is ok since this will be full house, which is greater then two pairs
        hand = Hand("AS AD AC KH KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14, 13, 14])

        hand = Hand("TD TC 2D 2H AC")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [10, 2, 14])

        hand = Hand("TS TH 3S 3D 2S")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [10, 3, 2])

    def test_isPair(self):
        hand = Hand("QS AD 9C QH KS")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [12, 14, 13, 9])

        hand = Hand("QS AD QC QH 9S")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [12, 14, 12, 9])

        hand = Hand("3S 3D 3C 3H 9S")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [3, 9, 3, 3])

        hand = Hand("2S 2D 3C 3H 9S")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [3, 9, 2, 2])

        hand = Hand("3S 3D 2C 2H 9S")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [3, 9, 2, 2])

        hand = Hand("AS 9D 3C QH KS")
        result = hand.is_pair()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_simpleTest(self):
        black = Hand("2H 3D 5S 9C KD")
        white = Hand("2C 3H 4S 8C KH")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Black wins.")

    def test_straightFlashVsOthers(self):
        white = Hand("7C 3C 4C 5C 6C")
        black = Hand("2H 4H 5H 3H 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        black = Hand("7C 3C 4C 5C 6C")
        white = Hand("2H 4H 5H 3H 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Black wins.")

        white = Hand("7C 3C 4C 5C 6C")
        black = Hand("7H 4H 5H 3H 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Tie.")

        black = Hand("7C 3C 4C 5C 6C")
        white = Hand("7H 4H 5H 3H 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Tie.")

        white = Hand("7C 8C 4C 5C 6C")
        black = Hand("4H 4D 4S 4C 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("7C 8C 9C 5C 6C")
        black = Hand("4H 4D 4S 6C 6H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("7C 8C 9C TC JC")
        black = Hand("2D 3H 4S 6S 5H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("QC 8C 9C TC JC")
        black = Hand("3D 3H 3S 6S 5H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("QC KC 9C TC JC")
        black = Hand("3D 3H 6S 6S 5H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("QC KC AC TC JC")
        black = Hand("3D 3H 2S 6S 5H")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")


    def test_FourOfAKindVSOthers(self):
        black = Hand("4C 4C 8C 7C 5C")
        white = Hand("3D 3D 8D 7D 6D")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

    def test_simpleTest4(self):
        black = Hand("3H 4H 5H 6H 7H")
        white = Hand("4D 5D 6D 7D 8D")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

        white = Hand("TD TC 2D 2H AC")
        black = Hand("TS TH 3S 3D 2S")
        winner = calculate_winner(white, black)
        self.assertEqual(winner, "Black wins.")

if __name__=='__main__':
    unittest.main()
