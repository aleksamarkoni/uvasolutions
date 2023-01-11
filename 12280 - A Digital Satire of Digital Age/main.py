import enum
import sys
from string import Template

class Weight(enum.Enum):
    LEFT = 1
    EQ = 2
    RIGHT = 3

weight_list = {
    'A': 2250, 'B': 2250, 'C': 2500, 'D': 2250, 'E': 2500, 'F': 2500, 'G': 2750, 'H': 2250, 'I': 2500,
    'J': 2500, 'K': 2750, 'L': 2500, 'M': 2750, 'N': 2750, 'O': 3000, 'P': 2250, 'Q': 2500, 'R': 2500,
    'S': 2750, 'T': 2500, 'U': 2750, 'V': 2750, 'W': 3000, 'X': 2500, 'Y': 2750, 'Z': 2750
}

def load_input():
    m = []
    for i in range(7):
        line = sys.stdin.readline()
        m.append(line)
    sys.stdin.readline() #skip the _________________ line

    if m[4][1] == '_':
        left = "".join([ch for ch in m[3][1:7] if ch.isalpha()])
        right = "".join([ch for ch in m[5][11:18] if ch.isalpha()])
        w = Weight.RIGHT
        return left, right, w

    if m[5][1] == '_':
        left = "".join([ch for ch in m[4][1:7] if ch.isalpha()])
        right = "".join([ch for ch in m[4][11:18] if ch.isalpha()])
        w = Weight.EQ
        return left, right, w

    left = "".join([ch for ch in m[5][1:7] if ch.isalpha()])
    right = "".join([ch for ch in m[3][11:18] if ch.isalpha()])
    w = Weight.LEFT
    return left, right, w


def calculate_real_weight(left, right):
    lw = sum([weight_list[ch] for ch in left])
    rw = sum([weight_list[ch] for ch in right])
    aw = Weight.EQ
    if lw > rw:
        aw = Weight.LEFT
    elif lw < rw:
        aw = Weight.RIGHT
    return aw

left_template = Template(
"""........||.../\...
........||../..\..
.../\...||./....\.
../..\..||/$right\\
./....\.||\______/
/$left\||........
\______/||........""")

right_template = Template(
""".../\...||........
../..\..||........
./....\.||.../\...
/$left\||../..\..
\______/||./....\.
........||/$right\\
........||\______/""")

eq_template = Template(
"""........||........
.../\...||.../\...
../..\..||../..\..
./....\.||./....\.
/$left\||/$right\\
\______/||\______/
........||........""")

def print_actual_figure(left, right, rw):
    left = left + '.' * (6 - len(left))
    right = right + '.' * (6 - len(right))
    if rw == Weight.LEFT:
        print(left_template.substitute(left=left, right=right))
    elif rw == Weight.RIGHT:
        print(right_template.substitute(left=left, right=right))
    else:
        print(eq_template.substitute(left=left, right=right))


line = sys.stdin.readline()
n = int(line.strip())
for i in range(n):
    left, right, w = load_input()
    rw = calculate_real_weight(left, right)
    print(f'Case {i+1}:')
    if w == rw:
        print('The figure is correct.')
    else:
        print_actual_figure(left, right, rw)
