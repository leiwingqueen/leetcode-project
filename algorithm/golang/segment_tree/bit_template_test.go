package segment_tree

import (
	"fmt"
	"testing"
)

func Test_t1(t *testing.T) {
	template := constructBitTemplate(5)
	for i := 0; i < 5; i++ {
		template.update(i, 1)
	}
	for i := 0; i < 5; i++ {
		r := template.queryRange(i, i)
		fmt.Printf("[%d,%d]:%d\n", i, i, r)
	}
	for i := 0; i < 5; i++ {
		r := template.queryRange(0, i)
		fmt.Printf("[0,%d]:%d\n", i, r)
	}
}
