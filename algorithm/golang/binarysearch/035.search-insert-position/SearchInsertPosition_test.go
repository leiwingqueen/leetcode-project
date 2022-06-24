package _35_search_insert_position

import (
	"fmt"
	"testing"
)

func TestSearchInsert(t *testing.T) {
	tests := [][]int{
		{1, 3, 5, 6},
		{1, 3, 5, 6},
	}
	target := []int{3, 7}
	result := []int{1, 4}
	for i := 0; i < len(tests); i++ {
		insert := searchInsert(tests[i], target[i])
		fmt.Printf("result:%v", insert)
		if result[i] != insert {
			t.Errorf("expect:%d,actual:%v",result[i],insert)
		}
	}
}
