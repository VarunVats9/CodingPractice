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
		ints[i] = make([]int, 2)
	}
	for i := 0; i < n; i++ {
		ints[i][0], ints[i][1] = nextInt(), nextInt()
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
	n, w := nextInt(), nextInt()
	a := nextInts(n)

	// dp[i] : The maximum total value for the exact weight i
	dp := make([]int, w+1)

	for i := 1; i <= n; i++ {
		weight := a[i-1][0]
		value := a[i-1][1]
		for j := w; j >= weight; j-- {
			dontChoose := dp[j]
			choose := value + dp[j-weight]
			dp[j] = max(dontChoose, choose)
		}
	}

	fmt.Println(dp[w])
}
