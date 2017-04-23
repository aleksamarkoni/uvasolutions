import sys

#improvment to use binary search on presorted major scales
#Since we are using python, not the fastest solution, but I think
#its the most readable one.

scale = ['C/B#', 'C#/Db', 'D', 'D#/Eb', 'E/Fb', 'F/E#', 'F#/Gb', 'G', 'G#/Ab', 'A', 'A#/Bb', 'B/Cb']
major_scale = {
    'C' : ['C', 'D', 'E', 'F', 'G', 'A', 'B', ],
    'Db' : ['Db', 'Eb', 'F', 'Gb', 'Ab', 'Bb', 'C', ],
    'D' : ['D', 'E', 'F#', 'G', 'A', 'B', 'C#', ],
    'Eb' : ['Eb', 'F', 'G', 'Ab', 'Bb', 'C', 'D', ],
    'E' : ['E', 'F#', 'G#', 'A', 'B', 'C#', 'D#', ],
    'F' : ['F', 'G', 'A', 'Bb', 'C', 'D', 'E', ],
    'Gb' : ['Gb', 'Ab', 'Bb', 'Cb', 'Db', 'Eb', 'F', ],
    'G' : ['G', 'A', 'B', 'C', 'D', 'E', 'F#', ],
    'Ab' : ['Ab', 'Bb', 'C', 'Db', 'Eb', 'F', 'G', ],
    'A' : ['A', 'B', 'C#', 'D', 'E', 'F#', 'G#', ],
    'Bb' : ['Bb', 'C', 'D', 'Eb', 'F', 'G', 'A', ],
    'B' : ['B', 'C#', 'D#', 'E', 'F#', 'G#', 'A#', ],
}

intervals = ["", "SECOND", "THIRD", "FOURTH", "FIFTH", "SIXTH", "SEVENTH", "OCTAVE"]
#generate major_scale:
#order = [0, 2, 4, 5, 7, 9, 11, 12]
#for position, tone in enumerate(scale):
#    print("'" + tone + "' : [", end='')
#    for item in order:
#        print("'" + scale[(position + item) % len(scale)] + "', ", end = '')
#    print("]")
# and then manually edit

while True:
    line = sys.stdin.readline().strip()
    if not line:
        break
    scale = major_scale[line]
    print("Key of " + line)
    line = sys.stdin.readline().strip()
    for query in line.split(";"):
        tone, direction, interval = query.split()
        if tone not in scale:
            print(tone + ": invalid note for this key")
            continue
        d = 1 if direction == "UP" else -1;
        i = intervals.index(interval)
        res = scale[(scale.index(tone) + d * i) % len(scale)]
        print(tone + ": " + direction + " " + interval + " > " + res)
    print()
