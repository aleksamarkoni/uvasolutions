import sys


while True:
	line = sys.stdin.readline().strip()

	if line == '':
		break

	tea_type = line
	line = sys.stdin.readline().strip()
	correct_guess = line.split().count(tea_type)
	print(correct_guess)
