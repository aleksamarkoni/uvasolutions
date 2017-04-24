import sys

line = sys.stdin.readline().strip()
tc = int(line)
for i in range(tc):
    line = sys.stdin.readline().strip()
    temp_c, f_add = line.split()
    #convert c->f, and add f
    temp_f = 9.0/5.0 * float(temp_c) + 32 + float(f_add)
    #return back to c
    res = (temp_f - 32) * 5.0/9.0
    print("Case {}: {:.2f}".format((i+1), res))
