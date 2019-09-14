def goodSequence(A):
    count = 1
    num = N
    den = 1
    ans = 1
    for _ in range(1, K+1):
        ans = (ans * num) / den
        ans = ans % MOD
        count = count + ans
        den = den + 1
        num = num - 1
        count = count % MOD

    return int(count)

MOD = 1000000007

N, K = map(int, input().split())
A = list(map(int, input().split()))
print(goodSequence(A))