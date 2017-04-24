import sys

#I am getting runtime error with this, but its working properly on all udebug data

line = sys.stdin.readline().strip()
tc = int(line)
i = 0
while True:
	if i == tc:
		break
	line = sys.stdin.readline().strip()
	if line == '':
		continue
	i += 1
	data = line.split()
	a = int(data[0])
	b = int(data[2]) if len(data) > 2 else 0
	print("Case {}: {:g}".format(i, (a * 0.5 + b * 0.05)))
