def designNetwork():
    if k < n-1:
        return -1
    elif k <= n+1:
        return 2
    else k <= 2n:
        return 3
    


t = int(input())
n = None
k = None
for t in range(T):
    n, k = map(int, input().split())
    print(designNetwork())