import sys

pres_per_note = {
    'c' : [2, 3, 4, 7, 8, 9, 10, ],
    'd' : [2, 3, 4, 7, 8, 9, ],
    'e' : [2, 3, 4, 7, 8, ],
    'f' : [2, 3, 4, 7, ],
    'g' : [2, 3, 4, ],
    'a' : [2, 3, ],
    'b' : [2, ],
    'C' : [3, ],
    'D' : [1, 2, 3, 4, 7, 8, 9, ],
    'E' : [1, 2, 3, 4, 7, 8, ],
    'F' : [1, 2, 3, 4, 7, ],
    'G' : [1, 2, 3, 4, ],
    'A' : [1, 2, 3, ],
    'B' : [1, 2, ],    
}

tc = int(sys.stdin.readline().strip())
for t in range(tc):
    notes = sys.stdin.readline().strip()
    pressed = [False] * 10
    totals = [0] * 10
    for note in notes:
        new_pressed_state = [False] * 10
        for i in pres_per_note[note]:
            totals[i-1] += 0 if pressed[i-1] else 1
            new_pressed_state[i-1] = True
        pressed = new_pressed_state
    print(' '.join([str(x) for x in totals]))
