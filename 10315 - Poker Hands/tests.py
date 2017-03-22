import unittest
from main import Hand
from main import calculate_winner

class TestHand(unittest.TestCase):

    def test_isStraightFlash(self):
        hand = Hand("2S 3S 4S 5S 6S")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [6])

        hand = Hand("2S 3S 4S 5S 7S")
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

        hand = Hand("TS AD AH TC KS")
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

    def test_isStraight(self):
        hand = Hand("AS TD JC QH KS")
        result = hand.is_straight()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("AS 9D JC QH KS")
        result = hand.is_straight()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isThreeOfAKind(self):
        hand = Hand("AS AD AC QH KS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [14])

        hand = Hand("AS AD JC QH KS")
        result = hand.is_three_of_a_kind()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isTwoPairs(self):
        hand = Hand("AS AD QC QH KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [14, 12, 13])

        hand = Hand("AS AD JC QH KS")
        result = hand.is_two_diff_pairs()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isPair(self):
        hand = Hand("QS AD 9C QH KS")
        result = hand.is_pair()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], [12, 14, 13, 9])

        hand = Hand("AS 9D 3C QH KS")
        result = hand.is_pair()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_simpleTest(self):
        black = Hand("2H 3D 5S 9C KD")
        white = Hand("2C 3H 4S 8C KH")
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Black wins.")

    def test_simpleTest2(self):
        black = Hand("2C 2D 5H 5S 5C")
        white = Hand("3C 3D 4H 4S 4C")
        #print(black.max_hand_value())
        #print(white.max_hand_value())
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "Black wins.")


    def test_simpleTest3(self):
        black = Hand("4C 4C 8C 7C 5C")
        white = Hand("3D 3D 8D 7D 6D")
        #print(black.max_hand_value())
        #print(white.max_hand_value())
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

    def test_simpleTest4(self):
        black = Hand("3H 4H 5H 6H 7H")
        white = Hand("4D 5D 6D 7D 8D")
        print(black.max_hand_value())
        print(white.max_hand_value())
        winner = calculate_winner(black, white)
        self.assertEqual(winner, "White wins.")

if __name__=='__main__':
    unittest.main()
