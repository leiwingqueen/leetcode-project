package dfs

import (
	"fmt"
	"testing"
)

func TestStockSpanner_Next(t *testing.T) {
	this := Constructor()
	arr := []int{100, 80, 60, 70, 60, 75, 85}
	for _, num := range arr {
		r := this.Next(num)
		fmt.Printf("%d\n", r)
	}
}
