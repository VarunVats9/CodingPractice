def xorContest(seq):
    ans = 0
    count = {}
    bad_count = {}
    pre_xor = [None] * (len(seq) + 1)

    pre_xor[0] = 0
    for i in range(1, len(seq)+1):
        pre_xor[i] = pre_xor[i-1] ^ seq[i-1]

    for i in range(len(pre_xor)):
        xor = pre_xor[i]
        ans = ans + i * (count[xor] if xor in count else 0) \
            - (bad_count[xor] if xor in bad_count else 0)
        if xor in count:
            count[xor] = count[xor] + 1
            bad_count[xor] = bad_count[xor] + (i+1)
        else:
            count[xor] = 1
            bad_count[xor] = i+1

    return ans



X = [None] * 100000
R = [None] * 100000
T = int(input())
for t in range(T):
    N = int(input())
    A = [int(x) for x in input().split()]
    print(xorContest(A))
