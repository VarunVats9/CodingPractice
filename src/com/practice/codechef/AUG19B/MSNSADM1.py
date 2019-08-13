T = int(raw_input())
for t in xrange(T):
    maximum = 0
    N = int(raw_input())
    G = map(int, raw_input().split())
    F = map(int, raw_input().split())
    for i in range(len(G)):
        maximum = max(maximum, 20 * G[i] - 10 * F[i])
    print(maximum if maximum > 0 else 0)
