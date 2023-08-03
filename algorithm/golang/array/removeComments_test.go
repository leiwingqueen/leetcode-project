package array

import (
	"fmt"
	"strings"
	"testing"
)

func Test(t *testing.T) {
	s := "// test"
	b := strings.HasPrefix(s, "//")
	fmt.Println(b)
}
