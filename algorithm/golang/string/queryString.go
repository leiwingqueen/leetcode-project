package string

import (
	"strconv"
	"strings"
)

// 暴力解法
func queryString(s string, n int) bool {
	for i := 1; i <= n; i++ {
		f := strconv.FormatUint(uint64(i), 2)
		if !strings.Contains(s, f) {
			return false
		}
	}
	return true
}
