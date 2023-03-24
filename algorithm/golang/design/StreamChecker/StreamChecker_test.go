package StreamChecker

import (
	"fmt"
	"testing"
)

// ["StreamChecker","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query","query"]
//[[["abaa","abaab","aabbb","bab","ab"]],["a"],["a"],["b"],["b"],["b"],["a"],["a"],["b"],["b"],["a"],["a"],["a"],["a"],["b"],["a"],["b"],["b"],["b"],["a"],["b"],["b"],["b"],["a"],["a"],["a"],["a"],["a"],["b"],["a"],["b"],["b"],["b"],["a"],["a"],["b"],["b"],["b"],["a"],["b"],["a"]]
func Test_query(t *testing.T) {
	check := Constructor([]string{"aabbb"})
	check.Query('a')
	check.Query('a')
	check.Query('b')
	check.Query('b')
	r := check.Query('b')
	fmt.Println("{}", r)
}
