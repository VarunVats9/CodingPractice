def xorContest(seq):
    count = 0
    X[1] = seq[1]
    for i in range(2, len(seq)):
        X[i] = X[i-1] ^ seq[i]
    for i in range(len(seq)-1):
        dict = {}
        for j in range(i+1):
            if i == j:
                R[j] = seq[i]
            else: 
                R[j] = R[j] ^ seq[i]
            if R[j] in dict:
                dict[R[j]] = dict[R[j]] + 1
            else:
                dict[R[j]] = 1
        for k in range(i+1, len(seq)):
            if X[k] in dict:
                count = count + dict[X[k]]
            X[k] = X[k] ^ seq[i+1]
    return count

X = [None] * 100000
R = [None] * 100000
T = int(raw_input())
for t in range(T):
    N = int(raw_input())
    A = map(int, raw_input().split())
    print(xorContest(A))
