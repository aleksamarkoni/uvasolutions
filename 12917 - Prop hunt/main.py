import sys


while True:
	line = sys.stdin.readline().strip()

	if line == '':
		break

	p, h, o = [int(x) for x in line.split()]
	if h > (o - p):
		print('Hunters win!')
	else:
		print('Props win!')
