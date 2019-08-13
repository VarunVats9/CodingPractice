T = int(raw_input())
for t in range(T):
    s = raw_input()
    zeros = 1 if s[0] == '0' else 0
    prev = s[0]
    for i in range(1, len(s)):
        if s[i] == '0':
            if prev == '0':
                zeros = zeros + 1
                prev = '0'
            else:
                prev = '1'
        else:
            if prev == '1':
                prev = '0'
                zeros = zeros + 1
            else:
                zeros = 0
                prev = '1'    
    if zeros > 0:
        print("LOSE")
    else:
        print("WIN")
