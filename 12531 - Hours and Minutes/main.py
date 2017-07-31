import sys

# #function to precalculate all angles
# def computeAllAngles():
#     res = {}
#     for i in range(180):
#         res[i] = 'N'
#     for i in range(720):
#         # this will calculate angle directly sine we need to multiply with 6
#         h = (i // 12) * 6
#         m = (i % 60) * 6
#         angle = abs(h - m)
#         angle = angle if angle < 180 else 360 - angle
#         res[angle] = 'Y'
#     return res
# print(computeAllAngles())

#there is a nice patter here and it's that we don't have any angle that is not
#devisible by 6, which is logical since angles for hour and min hand are angles
#that are devisible by 6, so we could do something like this
# #include <stdio.h>
# int main()
# {
#     int n;
#     while(scanf("%d",&n)==1){
#         ( n % 6 == 0) ? puts("Y") : puts("N");
#     }
#     return 0;
# }
angles = {
    0: 'Y', 1: 'N', 2: 'N', 3: 'N', 4: 'N', 5: 'N', 
    6: 'Y', 7: 'N', 8: 'N', 9: 'N', 10: 'N', 11: 'N', 
    12: 'Y', 13: 'N', 14: 'N', 15: 'N', 16: 'N', 17: 'N', 
    18: 'Y', 19: 'N', 20: 'N', 21: 'N', 22: 'N', 23: 'N', 
    24: 'Y', 25: 'N', 26: 'N', 27: 'N', 28: 'N', 29: 'N', 
    30: 'Y', 31: 'N', 32: 'N', 33: 'N', 34: 'N', 35: 'N', 
    36: 'Y', 37: 'N', 38: 'N', 39: 'N', 40: 'N', 41: 'N', 
    42: 'Y', 43: 'N', 44: 'N', 45: 'N', 46: 'N', 47: 'N', 
    48: 'Y', 49: 'N', 50: 'N', 51: 'N', 52: 'N', 53: 'N', 
    54: 'Y', 55: 'N', 56: 'N', 57: 'N', 58: 'N', 59: 'N', 
    60: 'Y', 61: 'N', 62: 'N', 63: 'N', 64: 'N', 65: 'N', 
    66: 'Y', 67: 'N', 68: 'N', 69: 'N', 70: 'N', 71: 'N', 
    72: 'Y', 73: 'N', 74: 'N', 75: 'N', 76: 'N', 77: 'N', 
    78: 'Y', 79: 'N', 80: 'N', 81: 'N', 82: 'N', 83: 'N', 
    84: 'Y', 85: 'N', 86: 'N', 87: 'N', 88: 'N', 89: 'N', 
    90: 'Y', 91: 'N', 92: 'N', 93: 'N', 94: 'N', 95: 'N', 
    96: 'Y', 97: 'N', 98: 'N', 99: 'N', 100: 'N', 101: 'N', 
    102: 'Y', 103: 'N', 104: 'N', 105: 'N', 106: 'N', 107: 'N', 
    108: 'Y', 109: 'N', 110: 'N', 111: 'N', 112: 'N', 113: 'N', 
    114: 'Y', 115: 'N', 116: 'N', 117: 'N', 118: 'N', 119: 'N', 
    120: 'Y', 121: 'N', 122: 'N', 123: 'N', 124: 'N', 125: 'N', 
    126: 'Y', 127: 'N', 128: 'N', 129: 'N', 130: 'N', 131: 'N', 
    132: 'Y', 133: 'N', 134: 'N', 135: 'N', 136: 'N', 137: 'N', 
    138: 'Y', 139: 'N', 140: 'N', 141: 'N', 142: 'N', 143: 'N', 
    144: 'Y', 145: 'N', 146: 'N', 147: 'N', 148: 'N', 149: 'N', 
    150: 'Y', 151: 'N', 152: 'N', 153: 'N', 154: 'N', 155: 'N', 
    156: 'Y', 157: 'N', 158: 'N', 159: 'N', 160: 'N', 161: 'N', 
    162: 'Y', 163: 'N', 164: 'N', 165: 'N', 166: 'N', 167: 'N', 
    168: 'Y', 169: 'N', 170: 'N', 171: 'N', 172: 'N', 173: 'N', 
    174: 'Y', 175: 'N', 176: 'N', 177: 'N', 178: 'N', 179: 'N', 
    180: 'Y'}

while True:
    line = sys.stdin.readline().strip()
    if line == '':
        break
    angle = int(line)
    print(angles[angle])
