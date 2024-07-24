package wc407

// 给你一个 二进制字符串 s。
//
// 你可以对这个字符串执行 任意次 下述操作：
//
// 选择字符串中的任一下标 i（ i + 1 < s.length ），该下标满足 s[i] == '1' 且 s[i + 1] == '0'。
// 将字符 s[i] 向 右移 直到它到达字符串的末端或另一个 '1'。例如，对于 s = "010010"，如果我们选择 i = 1，结果字符串将会是 s = "000110"。
// 返回你能执行的 最大 操作次数。
//
// 示例 1：
//
// 输入： s = "1001101"
//
// 输出： 4
//
// 解释：
//
// 可以执行以下操作：
//
// 选择下标 i = 0。结果字符串为 s = "0011101"。
// 选择下标 i = 4。结果字符串为 s = "0011011"。
// 选择下标 i = 3。结果字符串为 s = "0010111"。
// 选择下标 i = 2。结果字符串为 s = "0001111"。
// 示例 2：
//
// 输入： s = "00111"
//
// 输出： 0
//
// 提示：
//
// 1 <= s.length <= 105
// s[i] 为 '0' 或 '1'。

func maxOperations(s string) int {
	n := len(s)
	p1, p2 := n-1, n-1
	res := 0
	for p1 >= 0 {
		if s[p1] == '1' {
			res += p2 - p1
			p2--
		}
		p1--
	}
	return res
}

// 超时
func maxOperations2(s string) int {
	n := len(s)
	arr := []byte(s)
	move := func() bool {
		p := 0
		for p < n-1 && (arr[p] == '0' || arr[p+1] == '1') {
			p++
		}
		if p == n-1 {
			return false
		}
		for p < n-1 && arr[p+1] == '0' {
			arr[p], arr[p+1] = arr[p+1], arr[p]
			p++
		}
		return true
	}
	cnt := 0
	for move() {
		cnt++
	}
	return cnt
}

func maxOperations3(s string) int {
	n := len(s)
	var st []int
	for i, ch := range s {
		if ch == '1' {
			st = append(st, i)
		}
	}
	move := func() bool {
		for i := 0; i < len(st); i++ {
			if st[i] < n-1 && (i == len(st)-1 || st[i] > st[i+1]+1) {
				// 最后一个数字或者中间有0
				last := n - 1
				if len(st) > 1 {
					last = st[i+1] - 1
				}
				st[i] = last
				return true
			}
		}
		return false
	}
	cnt := 0
	for move() {
		cnt++
	}
	return cnt
}
