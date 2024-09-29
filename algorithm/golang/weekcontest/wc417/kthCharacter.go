package wc417

import (
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

// 居然过了
func kthCharacter2(k int64, operations []int) byte {
	// 计算需要操作多少次
	n := int(math.Log2(float64(k))) + 1
	// 第i次迭代序号为idx的字符
	var dfs func(i int, idx int64) byte
	dfs = func(i int, idx int64) byte {
		// fmt.Printf("i:%d,idx:%d\n", i, idx)
		if i == 0 {
			return 'a'
		}
		// 判断数字是来源于前一半还是后一半
		mid := int64(1 << (i - 1))
		if idx >= mid {
			// 后一半
			ch := dfs(i-1, idx-mid)
			op := operations[i-1]
			if op == 1 {
				ch++
				if ch > 'z' {
					ch = 'a'
				}
			}
			return ch
		} else {
			// 前一半
			return dfs(i-1, idx)
		}
	}
	return dfs(n, k-1)
}
