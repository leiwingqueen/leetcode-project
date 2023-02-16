package wc332

import (
	"math"
	"sort"
)

// 你有两个果篮，每个果篮中有 n 个水果。给你两个下标从 0 开始的整数数组 basket1 和 basket2 ，用以表示两个果篮中每个水果的成本。
//
//你希望两个果篮相等。为此，可以根据需要多次执行下述操作：
//
//选中两个下标 i 和 j ，并交换 basket1 中的第 i 个水果和 basket2 中的第 j 个水果。
//交换的成本是 min(basket1i,basket2j) 。
//根据果篮中水果的成本进行排序，如果排序后结果完全相同，则认为两个果篮相等。
//
//返回使两个果篮相等的最小交换成本，如果无法使两个果篮相等，则返回 -1 。
//
//
//
//示例 1：
//
//输入：basket1 = [4,2,2,2], basket2 = [1,4,1,2]
//输出：1
//解释：交换 basket1 中下标为 1 的水果和 basket2 中下标为 0 的水果，交换的成本为 1 。此时，basket1 = [4,1,2,2] 且 basket2 = [2,4,1,2] 。重排两个数组，发现二者相等。
//示例 2：
//
//输入：basket1 = [2,3,4,1], basket2 = [3,2,5,1]
//输出：-1
//解释：可以证明无法使两个果篮相等。
//
//
//提示：
//
//basket1.length == bakste2.length
//1 <= basket1.length <= 105
//1 <= basket1i,basket2i <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/rearranging-fruits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 贪心
func minCost(basket1 []int, basket2 []int) int64 {
	min := func(a int, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	mpTotal := make(map[int]int)
	mp1 := make(map[int]int)

	for _, num := range basket1 {
		mp1[num]++
		mpTotal[num]++
	}
	for _, num := range basket2 {
		mpTotal[num]++
	}
	for _, v := range mpTotal {
		if v%2 != 0 {
			return -1
		}
	}
	in := make([][]int, 0)
	out := make([][]int, 0)
	for k, v := range mpTotal {
		if mp1[k] == v/2 {
			continue
		}
		if mp1[k] > v/2 {
			out = append(out, []int{k, mp1[k] - v/2})
		} else {
			in = append(in, []int{k, v/2 - mp1[k]})
		}
	}
	sort.Slice(in, func(i, j int) bool {
		return in[i][0] < in[j][0]
	})
	sort.Slice(out, func(i, j int) bool {
		return out[i][0] < out[j][0]
	})
	var res int64
	l1 := 0
	r1 := len(in) - 1
	l2 := 0
	r2 := len(out) - 1
	for l1 <= r1 {
		if in[l1][0] <= out[l2][0] {
			sub := min(in[l1][1], out[r2][1])
			in[l1][1] -= sub
			out[r2][1] -= sub
			res += int64(sub) * int64(in[l1][0])
			if in[l1][1] == 0 {
				l1++
			}
			if out[r2][1] == 0 {
				r2--
			}
		} else {
			sub := min(out[l2][1], in[r1][1])
			out[l2][1] -= sub
			in[r1][1] -= sub
			res += int64(sub) * int64(out[l2][0])
			if out[l2][1] == 0 {
				l2++
			}
			if in[r1][1] == 0 {
				r1--
			}
		}
	}
	return res
}

func minCost2(basket1 []int, basket2 []int) int64 {
	min := func(a int, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	mpTotal := make(map[int]int)
	mp1 := make(map[int]int)
	minNum := math.MaxInt32
	for _, num := range basket1 {
		mp1[num]++
		mpTotal[num]++
		minNum = min(minNum, num)
	}
	for _, num := range basket2 {
		mpTotal[num]++
		minNum = min(minNum, num)
	}
	for _, v := range mpTotal {
		if v%2 != 0 {
			return -1
		}
	}
	in := make([]int, 0)
	out := make([]int, 0)
	for k, v := range mpTotal {
		if mp1[k] == v/2 {
			continue
		}
		if mp1[k] > v/2 {
			for i := 0; i < mp1[k]-v/2; i++ {
				out = append(out, k)
			}
		} else {
			for i := 0; i < v/2-mp1[k]; i++ {
				in = append(in, k)
			}
		}
	}
	sort.Ints(in)
	sort.Ints(out)
	var res int64
	for len(in) > 0 {
		m1 := min(in[0], out[0])
		m2 := 2 * minNum
		if m2 < m1 {
			res += int64(m2)
			in = in[:len(in)-1]
			out = out[:len(out)-1]
		} else {
			res += int64(m1)
			if in[0] < out[0] {
				in = in[1:]
				out = out[:len(out)-1]
			} else {
				out = out[1:]
				in = in[:len(in)-1]
			}
		}
	}
	return res
}
