package tree

import (
	"fmt"
	"testing"
)

func Test_allPossibleFBT(t *testing.T) {
	fbt := allPossibleFBT(2)
	for _, root := range fbt {
		fmt.Printf("%v\n", root)
	}
}
