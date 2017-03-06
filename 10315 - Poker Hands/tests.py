import unittest
from main import Hand

class TestHand(unittest.TestCase):

    def test_isStraightFlash(self):
        hand = Hand("2S 3S 4S 5S 6S")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], 6)

        hand = Hand("2S 3S 4S 5S 7S")
        result = hand.is_straight_flash()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isFourOfAKind(self):
        hand = Hand("AS AD AH AC KS")
        result = hand.is_four_of_a_kind()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], 14)

        hand = Hand("TS AD AH AC KS")
        result = hand.is_four_of_a_kind()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_isFullHouse(self):
        hand = Hand("AS AD AH TC TS")
        result = hand.is_full_house()
        self.assertEqual(result[0], True)
        self.assertEqual(result[1], 14)

        hand = Hand("TS AD AH TC KS")
        result = hand.is_full_house()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

    def test_flush(self):
        hand = Hand("AS TS 9S 2S 5S")
        result = hand.is_flush()
        print(result)
        self.assertEqual(result[0], True)
        self.assertListEqual(result[1], [2, 5, 9, 10, 14])

        hand = Hand("TS AD AH TC KS")
        result = hand.is_flush()
        self.assertEqual(result[0], False)
        self.assertEqual(result[1], None)

if __name__=='__main__':
    unittest.main()
