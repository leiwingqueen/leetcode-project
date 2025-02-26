package dfs

import (
	"fmt"
	"testing"
)

func Test1(t *testing.T) {
	res := findSubsequences2([]int{4, 6, 7, 7})
	for _, item := range res {
		fmt.Printf("%v\n", item)
	}
}
