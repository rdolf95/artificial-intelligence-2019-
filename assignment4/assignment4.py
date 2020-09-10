from z3 import *
import time

# Number of Queens
print("N: ")
N = int(input())

start = time.time()
# Variables
X = [Int("x_%s" % (row)) for row in range(N)]

# Constraints
# For each row, there should be one key.

domain = [And(X[row] <= N, X[row] >=1) for row in range(N)]


rowConst = [(X[i] != X[j]) for i in range(N) for j in range(i+1, N)]

digConst = [And((X[j] - X[i]) != (j - i), (X[j] - X[i]) != (i - j)) 
            for i in range(N) for j in range(i+1,N)]

n_queens_c = domain + rowConst + digConst

s = Solver()
s.add(n_queens_c)

if s.check() == sat:
    m = s.model()
    result = []
    for i in range(N):
        result.append(m.evaluate(X[i]))
    print(result)
else:
    print("No answer")

print("elapsed time: ", time.time() - start, " sec")


