package wc417

import (
	"fmt"
	"math"
)

func kthCharacter(k int) byte {
	word := make([]byte, 0, k)
	word = append(word, 'a')
	for len(word) < k {
		size := len(word)
		for i := 0; i < size; i++ {
			next := word[i] + 1
			if next > 'z' {
				next = 'a'
			}
			word = append(word, next)
		}
	}
	return word[k-1]
}

func kthCharacter2(k int64, operations []int) byte {
	// 计算需要操作多少次
	n := int(math.Log2(float64(k)))
	n++
	// 第i次迭代序号为idx的字符
	var dfs func(i int, idx int64) byte
	dfs = func(i int, idx int64) byte {
		fmt.Printf("i:%d,idx:%d\n", i, idx)
		if i == 0 {
			return 'a'
		}
		op := operations[i-1]
		idx -= 1 << (i - 1)
		ch := dfs(i-1, idx)
		if op == 1 {
			ch++
			if ch > 'z' {
				ch = 'a'
			}
		}
		return ch
	}
	return dfs(n, k)
}
