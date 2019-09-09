def nearestPowerOf2(num):
    power = 1
    ans = 1
    while (power <= num):
        ans = power
        power = 2 * power
    return ans


def fibonaciCycle():
    F[0] = 0
    F[1] = 1
    for i in range(2, 60):
        F[i] = (F[i-1] + F[i-2]) % 10


F = [None] * 60
T = int(input())
fibonaciCycle()

for t in range(T):
    N = int(input())
    P = nearestPowerOf2(N)
    #print(F)
    print(F[(P -1) % 60])
