package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

var scanner = bufio.NewScanner(os.Stdin)

func next() string {
	scanner.Scan()
	return scanner.Text()
}

func nextInt() int {
	num, _ := strconv.Atoi(next())
	return num
}

func nextInts(n int) [][]int {
	ints := make([][]int, n)
	for i := range ints {
		ints[i] = make([]int, 3)
	}
	for i := 0; i < n; i++ {
		ints[i][0], ints[i][1], ints[i][2] = nextInt(), nextInt(), nextInt()
	}
	return ints
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func main() {

	scanner.Split(bufio.ScanWords)
	n := nextInt()
	a := nextInts(n)

	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, 3)
	}

	for i := 0; i < n; i++ {
		if i == 0 {
			dp[i][0] = a[i][0]
			dp[i][1] = a[i][1]
			dp[i][2] = a[i][2]
		} else {
			dp[i][0] = a[i][0] + max(dp[i-1][1], dp[i-1][2])
			dp[i][1] = a[i][1] + max(dp[i-1][0], dp[i-1][2])
			dp[i][2] = a[i][2] + max(dp[i-1][0], dp[i-1][1])
		}
	}

	fmt.Println(max(dp[n-1][2], max(dp[n-1][0], dp[n-1][1])))
}
