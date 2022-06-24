package _64_min_path

import (
	"fmt"
	"testing"
)

func Test(t *testing.T) {
	grid:=[][]int{{1,3,1},{1,5,1},{4,2,1}}
	sum := minPathSum(grid)
	fmt.Printf("result %d",sum)
}

