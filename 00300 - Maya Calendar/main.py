import sys

haab_months = [ "pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin", "mol", 
    "chen", "yax", "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", 
    "cumhu", "uayet", ]

tzolkin_months = [ "imix", "ik", "akbal", "kan", "chicchan", "cimi", "manik", 
    "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", 
    "eznab", "canac", "ahau"]
    
tc = int(sys.stdin.readline().strip())
print(tc)
for t in range(tc):
    d, m, y = sys.stdin.readline().strip().split()
    mi = haab_months.index(m)
    hdays = int(y) * 365 + (mi) * 20 + int(d[:-1])
    ty = hdays // 260
    left_days = hdays % 260
    tm = left_days % 20
    td = left_days % 13 + 1
    print(td, tzolkin_months[tm], ty)
