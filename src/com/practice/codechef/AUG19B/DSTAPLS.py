T = int(raw_input())
for t in xrange(T):
    N, K = map(int, raw_input().split())
    print("NO" if (N/K) % K == 0 else "YES")
