package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

var scanner = bufio.NewScanner(os.Stdin)
var sumValue = 0

// maxValue is the maximum int value.
const maxValue = 2e9

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
		ints[i] = make([]int, 2)
	}
	for i := 0; i < n; i++ {
		ints[i][0], ints[i][1] = nextInt(), nextInt()
		sumValue = sumValue + ints[i][1]
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
	if a > b {
		return b
	}
	return a
}

func main() {

	scanner.Split(bufio.ScanWords)
	n, w := nextInt(), nextInt()
	a := nextInts(n)

	// dp[i] : the minimum total weight for the exact value i
	dp := make([]int, sumValue+1)
	for i := range dp {
		dp[i] = maxValue
	}
	dp[0] = 0

	for i := 1; i <= n; i++ {
		weight := a[i-1][0]
		value := a[i-1][1]
		for j := sumValue; j >= value; j-- {
			dontChoose := dp[j]
			choose := weight + dp[j-value]
			dp[j] = min(dontChoose, choose)
		}
	}

	answer := 0
	for i := 1; i <= sumValue; i++ {
		if dp[i] <= w {
			answer = max(answer, i)
		}
	}
	fmt.Println(answer)
}
