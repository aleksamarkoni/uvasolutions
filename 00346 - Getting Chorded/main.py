
import sys

notes = ['a', 'a#', 'b', 'c', 'c#', 'd', 'd#', 'e', 'f', 'f#', 'g', 'g#',
         'a', 'a#', 'b', 'c', 'c#', 'd', 'd#', 'e', 'f', 'f#', 'g', 'g#']
#              Bb              Db         Eb              Gb         Ab
#major                    c                     e               g
#minor                    c               d#                    g

convert = {
    'bb' : 'a#',
    'db' : 'c#',
    'eb' : 'd#',
    'gb' : 'f#',
    'ab' : 'g#',
}

#This will generate all major chords
#print("major = {")
#for i in range(12):
#    print("'" + notes[i] + "'" + " : ['" + notes[i+4] + "','" + notes[i+7] + "'],")
#print("}")
major = {
    'a' : ['c#','e'],
    'a#' : ['d','f'],
    'b' : ['d#','f#'],
    'c' : ['e','g'],
    'c#' : ['f','g#'],
    'd' : ['f#','a'],
    'd#' : ['g','a#'],
    'e' : ['g#','b'],
    'f' : ['a','c'],
    'f#' : ['a#','c#'],
    'g' : ['b','d'],
    'g#' : ['c','d#'],
}

#This will generate all minor chords
#print("minor = {")
#for i in range(12):
#    print("'" + notes[i] + "'" + " : ['" + notes[i+3] + "','" + notes[i+7] + "'],")
#print("}")
minor = {
    'a' : ['c','e'],
    'a#' : ['c#','f'],
    'b' : ['d','f#'],
    'c' : ['d#','g'],
    'c#' : ['e','g#'],
    'd' : ['f','a'],
    'd#' : ['f#','a#'],
    'e' : ['g','b'],
    'f' : ['g#','c'],
    'f#' : ['a','c#'],
    'g' : ['a#','d'],
    'g#' : ['b','d#'],
}

def check(n1, n2, n3, line):
    maj = major[n1]
    if n2 in maj and n3 in maj:
        print(line, "is a {} Major chord.".format(n1.upper()))
        return True
    if n2 in minor[n1] and n3 in minor[n1]:
        print(line, "is a {} Minor chord.".format(n1.upper()))
        return True
    return False

while True:
    line = sys.stdin.readline()
    if not line:
        break
    line = line.strip()
    #if the line is empty
    if not line:
        continue
    lower = [n.lower() for n in line.split()]
    n1, n2, n3 = [note if note not in convert else convert[note] for note in lower]
    if n1 in notes and n2 in notes and n3 in notes:
        if n1 != n2 and n1 != n3 and n2 != n3:
            if check(n1, n2, n3, line):
                continue
            if check(n2, n1, n3, line):
                continue
            if check(n3, n1, n2, line):
                continue
    print(line + " is unrecognized.")
