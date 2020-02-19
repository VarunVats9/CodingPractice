import math
import sys
    
def main():
    n, k = map(int, input().split())
    a = list(map(int, input().split()))
    dp = [0] * n

    for i in range(1, n):
        dp[i] = sys.maxsize
        stop = max(-1, i-(k+1))
        for j in range(i-1, stop, -1):
            if j >= 0:
                dp[i] = min(dp[j] + math.fabs(a[i] - a[j]), dp[i])

    print(int(dp[n-1]))

main()