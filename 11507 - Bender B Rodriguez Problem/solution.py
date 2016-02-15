import sys

bendMap = {
  "+y" : {"+x" : "+y", "-x" : "-y", "+y" : "-x", "-y" : "+x", "+z" : "+z", "-z" : "-z"},
  "-y" : {"+x" : "-y", "-x" : "+y", "+y" : "+x", "-y" : "-x", "+z" : "+z", "-z" : "-z"},
  "+z" : {"+x" : "+z", "-x" : "-z", "+y" : "+y", "-y" : "-y", "+z" : "-x", "-z" : "+x"},
  "-z" : {"+x" : "-z", "-x" : "+z", "+y" : "+y", "-y" : "-y", "+z" : "+x", "-z" : "-x"}  
}

while True:
    line = sys.stdin.readline().strip()
    l = int(line)
    if l == 0:
        break
    line = sys.stdin.readline().strip()
    data = line.split()
    direction = "+x"
    for bend in data:
        if bend != "No":
            direction = bendMap[bend][direction] 
    print(direction)

