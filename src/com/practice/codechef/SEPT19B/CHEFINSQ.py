def comb(n, r):
    minim = n-r if n-r < r else r
    ans = 1
    for x in range(1, minim+1):
        ans = (ans * ((n-minim) + x)) / x
    return int(ans)

def minimumSumSeq(A):
    if N == K:
        return 1
    repeatNum = {}
    for _, x in enumerate(A):
        if x in repeatNum:
            repeatNum[x] = repeatNum[x] + 1
        else:
            repeatNum[x] = 1
    A.sort()
    val = A[K-1]
    r = 0
    # Call function to calculate nCr
    for idx in range(K):
        if A[idx] == val:
            r = r + 1
    return comb(repeatNum[val], r)

T = int(input())
N = None
K = None
for t in range(T):
    N, K = map(int, input().split())
    A = list(map(int, input().split()))
    print(minimumSumSeq(A))