import math

def minCost():
    dp[0] = 0
    for i in range(1, n):
        if i == 1:
            dp[i] = math.fabs(a[i] - a[i-1])
        else:
            dp[i] = min(dp[i-1] + math.fabs(a[i] - a[i-1]), dp[i-2] + math.fabs(a[i] - a[i-2]))
    return int(dp[n-1])

dp = [0] * 100000
n = int(input())
a = list(map(int, input().split()))
print(minCost())