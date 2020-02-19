package main

import (
	"bufio"
	"fmt"
	"math"
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

func nextInts(n int) []int {
	ints := make([]int, n)
	for i := 0; i < n; i++ {
		ints[i] = nextInt()
	}
	return ints
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}

func main() {

	scanner.Split(bufio.ScanWords)
	n, k := nextInt(), nextInt()
	a := nextInts(n)

	dp := make([]int, n)
	for i := 1; i < n; i++ {
		dp[i] = math.MaxInt32
		stop := max(-1, i-(k+1))
		for j := i - 1; j > stop; j-- {
			if j >= 0 {
				dp[i] = min(dp[j]+abs(a[i]-a[j]), dp[i])
			}
		}
	}

	fmt.Println(dp[n-1])
}
