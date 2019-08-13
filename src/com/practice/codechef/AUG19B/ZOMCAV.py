def rangeUpdate(radiation):
    R = [0] * len(radiation)
    #print(R)
    for i in range(1, len(radiation)+1):
        high = min(len(radiation), i+radiation[i-1])
        low = max(1, i-radiation[i-1])
        R[low-1] = R[low-1] + 1
        #print(low, high)
        if high < len(radiation):
            R[high] = R[high] - 1
    #print(R)
    for i in range(len(R)):
        if i == 0:
            continue
        R[i] = R[i-1] + R[i]
    R.sort()     
    return R

T = int(raw_input())
for t in range(T):
    N = int(raw_input())
    C = map(int, raw_input().split())
    H = map(int, raw_input().split())
    R = rangeUpdate(C)
    H.sort()
    #print(R)
    notPossible = False
    for i in range(len(H)):
        if H[i] != R[i]:
            notPossible = True
            break
    print("YES" if notPossible == False else "NO")
