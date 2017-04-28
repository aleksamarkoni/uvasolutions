import sys

def canFit(size, paragrapsh, x, y):
    th = 0
    for p in paragrapsh:
        tw = 0
        total_lines = 1
        i = 0
        while i < len(p):
            w = p[i]
            #print("tws:", tw)
            #print(w*size, x)
            if (w * size) > x: #single word can't fit, break
                return False
            if tw + w * size <= x:
                tw += w * size
                tw += size # add spaces after the word
                i += 1
            else:
                # the word can't fit in this line, we need to do a wrap
                total_lines += 1
                tw = 0
            #print("twe", tw)
        #print("lines", total_lines)
        if total_lines * size > y: # paragraph can't fit return false
            return False
        if th + total_lines * size > y: #total height of all paragrapsh cant fit
            return False
        th += total_lines * size
    return True # all paragrapsh can fit return true

line = sys.stdin.readline().strip()
tc = int(line)
for t in range(tc):
    parnum = int(sys.stdin.readline().strip())
    paragrapsh = []
    for p in range(parnum):
        para = [len(w) for w in sys.stdin.readline().strip().split()]
        #space after last word should not be used
        paragrapsh.append(para)
    #print(paragrapsh)
    x, y = [int(size) for size in sys.stdin.readline().strip().split()]
    right = 28
    left = 8
    found = None
    while left <= right:
        mid = (right + left) // 2
        #print(left, mid, right, canFit(mid, paragrapsh, x, y))
        if canFit(mid, paragrapsh, x, y):
            left = mid + 1
            found = mid
        else:
            right = mid - 1
    if found:
        print(found)
    else:
        print("No solution")
