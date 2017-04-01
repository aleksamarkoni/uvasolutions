#Trivial solution if you are using the Set or someking of list to store uniqu palindromes
#in the worst case that will require around 80k or memory to store all unigue palindrom substrings
#here is the solution for that
'''
import sys

while True:
    line = sys.stdin.readline()
    if line == '':
        break
    uniquePalindroms = []
    line = line.rstrip()
    n = len(line)
    for i in range(n):
        substring = ""
        for j in range(i, n):
            substring = substring + line[j]
            #this will check if the string is palindrom
            if substring == substring[::-1]:
                if substring not in uniquePalindroms:
                    uniquePalindroms.append(substring)
    print("The string '{}' contains {} palindromes.".format(line, len(uniquePalindroms)))
'''
#If we want to save as much memory as possible, and still do good with caluclating
#time we should travers substrings based on their lenght. So first we traverse
#substring of length 1, then ones with length 2 etc.
#For all the substring of the given lenghts, we store the boolean array which is the
#same lenght as the given string, and then mark with false, all the substring there are repeating
#For example in string 'mamamam' we have 3 substrings 'mam' but we should only store one.
#When we traverse all the substrings of length 3, start positions of those strings, will be
#[0, 1, 2, 3, 4] notice that we don't traverse last two positions [5,6] because
#they cant form substring of length 3.
#So we make a boolean array of lenght 5 with all true values, and then while we
#traverse all the substrings of lenght 3, we will mark with false all the substring
#that are all ready repeting. As a result we will get something like this.
# first substring of lenght 3 is 'mam'. Because its palindrom [T, T, T, T, T]
# 'ama' [T, T, T, T, T]
# 'mam' [T, T, F, T, T] we all ready have mam on first position
# 'ama' [T, T, F, F, T] all ready having ama on second positions
# 'mam' [T, T, F, F, F] all ready having mam
# as you can see we only have 2 unique palindrom substring of lenght 3, which we can add
#to the overall number of all unique palindrom substrings
import sys

while True:
    line = sys.stdin.readline()
    if line == '':
        break
    line = line.strip()
    n = len(line)
    totalUniqueSubstrings = 0
    for i in range(1, n + 1):
        unique = [True] * (n-i+1)
        for j in range(0, n-i+1):
            substring = line[j:j+i]
            #this will check if the string is palindrom
            if substring == substring[::-1]:
                for k in range(0, j):
                    if line[k:k+i] == substring:
                        unique[j] = False
                        break
            else:
                unique[j] = False
        totalUniqueSubstrings = totalUniqueSubstrings + unique.count(True)
    print("The string '{}' contains {} palindromes.".format(line, totalUniqueSubstrings))
