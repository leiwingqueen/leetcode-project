package wc298

//给你两个整数 num 和 k ，考虑具有以下属性的正整数多重集：
//
//每个整数个位数字都是 k 。
//所有整数之和是 num 。
//返回该多重集的最小大小，如果不存在这样的多重集，返回 -1 。
//
//注意：
//
//多重集与集合类似，但多重集可以包含多个同一整数，空多重集的和为 0 。
//个位数字 是数字最右边的数位。
//
//
//示例 1：
//
//输入：num = 58, k = 9
//输出：2
//解释：
//多重集 [9,49] 满足题目条件，和为 58 且每个整数的个位数字是 9 。
//另一个满足条件的多重集是 [19,39] 。
//可以证明 2 是满足题目条件的多重集的最小长度。
//示例 2：
//
//输入：num = 37, k = 2
//输出：-1
//解释：个位数字为 2 的整数无法相加得到 37 。
//示例 3：
//
//输入：num = 0, k = 7
//输出：0
//解释：空多重集的和为 0 。
//
//
//提示：
//
//0 <= num <= 3000
//0 <= k <= 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func minimumNumbers(num int, k int) int {
	if num == 0 {
		return 0
	}
	n := 1
	for n <= 3000 {
		sub := num - n*k
		if sub < 0 {
			return -1
		}
		if sub%10 == 0 {
			return n
		}
		n++
	}
	return -1
}

//上面的写法有点取巧
func minimumNumber2(num int, k int) int {
	if num == 0 {
		return 0
	}
	n := 1
	for n <= num {
		sub := num - n*k
		if sub < 0 {
			return -1
		}
		if sub%10 == 0 {
			return n
		}
		n++
	}
	return -1
}
