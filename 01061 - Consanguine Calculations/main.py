import sys

#code is not so clean, but solution is there :) 

parent_parent = {
    "A" : { "A" : [ "A", "O", ],
    	    "B" : [ "A", "B", "AB", "O", ],
            "AB" : [ "A", "B", "AB", ],
            "O" : [ "A", "O", ], },
    "B" : { "A" : ["A", "B", "AB", "O"],
            "B" : ["B", "O", ],
            "AB" : [ "A", "B", "AB", ],
            "O" : ["B", "O", ], },
    "AB" : { "A" : [ "A", "B", "AB", ],
            "B" : [ "A", "B", "AB", ],
            "AB" : [ "A", "B", "AB", ],
            "O" : [ "A", "B", ], },
    "O" : { "A" : ["A", "O", ],
            "B" : ["B", "O", ],
            "AB" : ["A", "B", ],
            "O" : ["O", ], },
    }

parent_child = {
    "A" : { "A" : [ "A", "B", "AB", "O", ],
    	    "B" : [ "B", "AB", ],
            "AB" : [ "B", "AB", ],
            "O" : [ "A", "B", "O", ], },
    "B" : { "A" : ["A", "AB", ],
            "B" : [ "A", "B", "AB", "O", ],
            "AB" : [ "A", "AB", ],
            "O" : [ "A", "B", "O", ], },
    "AB" : { "A" : [ "A", "B", "AB", "O", ],
            "B" : [ "A", "B", "AB", "O", ],
            "AB" : [ "A", "B", "AB", ],
            "O" : None },
    "O" : { "A" : ["A", "AB", ],
            "B" : ["B", "AB", ],
            "AB" : None,
            "O" : ["A", "B", "O", ], },
}

def calculate(a, b, data, rh):
    abt, arh = a[0:-1], a[-1:]
    bbt, brh = b[0:-1], b[-1:]
    d = data[abt][bbt]
    if d:
        if rh:
            rhdata = ['-'] if (arh == '-' and brh == '-') else ['+', '-']
        else:
            rhdata = ['+'] if (arh == '-' and brh == '+') else ['+', '-']
        return [bt + rh for bt in d for rh in rhdata]

tc = 0;
while True:
    line = sys.stdin.readline().strip()
    if line == "":
        continue
    if line == "E N D":
        break
    tc += 1
    print("Case {}: ".format(tc), end='')
    p1, p2, c = line.split();
    if (p1 == "?"):
        res = calculate(p2, c, parent_child, False)
        if not res:
            print("IMPOSSIBLE", p2, c)
        elif len(res) == 1:
            print(res[0], p2, c)
        else:
            print("{" + ', '.join(res[::-1]) + "}", p2, c)
    if (p2 == "?"):
        res = calculate(p1, c, parent_child, False)
        if not res:
            print(p1, "IMPOSSIBLE", c)
        elif len(res) == 1:
            print(p1, res[0], c)
        else:
            print(p1, "{" + ', '.join(res[::-1]) + "}", c)
    if (c == "?"):
        res = calculate(p1, p2, parent_parent, True)
        if not res:
            print(p1 , p2, "IMPOSSIBLE")
        elif len(res) == 1:
            print(p1, p2, res[0])
        else:
            print(p1, p2, "{" + ', '.join(res[::-1]) + "}")
