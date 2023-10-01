import sys


while True:
	line = sys.stdin.readline().strip()

	if line == '':
		break

	a, b, c = [int(x) for x in line.split()]
	if a != b and a != c:
		print('A')
		continue
	if b != a and b != c:
		print('B')
		continue
	if c != a and c != b:
		print('C')
		continue
	print('*')
