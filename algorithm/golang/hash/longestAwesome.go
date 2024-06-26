package hash

// 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
//
//「超赞子字符串」需满足满足下述两个条件：
//
//该字符串是 s 的一个非空子字符串
//进行任意次数的字符交换后，该字符串可以变成一个回文字符串
//
//
//示例 1：
//
//输入：s = "3242415"
//输出：5
//解释："24241" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "24142"
//示例 2：
//
//输入：s = "12345678"
//输出：1
//示例 3：
//
//输入：s = "213123"
//输出：6
//解释："213123" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "231132"
//示例 4：
//
//输入：s = "00"
//输出：2
//
//
//提示：
//
//1 <= s.length <= 10^5
//s 仅由数字组成

// 二分是不成立的
func longestAwesome(s string) int {
	n := len(s)
	check2 := func(odd, k int) bool {
		if k%2 == 0 {
			// 必须数字都是偶数
			return odd == 0
		} else {
			return odd == 1
		}
	}
	check := func(k int) bool {
		odd := 0
		mp := make([]int, 10)
		l, r := 0, 0
		for r < n {
			if r-l < k {
				if mp[s[r]-'0']%2 == 0 {
					// 偶数变奇数
					odd++
				} else if mp[s[r]-'0'] > 0 {
					// 奇数变偶数
					odd--
				}
				mp[s[r]-'0']++
				r++
			} else {
				// 到达窗口大小
				if check2(odd, k) {
					return true
				}
				if mp[s[l]-'0']%2 == 1 {
					odd--
				} else if mp[s[l]-'0'] > 0 {
					// 偶数变奇数
					odd++
				}
				mp[s[l]-'0']--
				l++
			}
		}
		return check2(odd, k)
	}
	// 二分查找
	l, r := 1, n
	for l < r {
		mid := l + (r-l+1)/2
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}

// 不出意外的超时了
func longestAwesome2(s string) int {
	n := len(s)
	prefix := make([]int, n+1)
	for i := 0; i < n; i++ {
		b := 1 << (s[i] - '0')
		prefix[i+1] = prefix[i] ^ b
	}
	check := func(k int) bool {
		for i := 0; i <= n-k; i++ {
			// [i,i+k)
			p := prefix[i+k] ^ prefix[i]
			cnt := 0
			for j := 0; j < 10; j++ {
				if p&(1<<j) != 0 {
					cnt++
				}
			}
			if cnt <= 1 {
				return true
			}
		}
		return false
	}
	for i := n; i >= 1; i-- {
		if check(i) {
			return i
		}
	}
	return -1
}

// 在上面基础上增加哈希表来统计
func longestAwesome3(s string) int {
	n := len(s)
	res := 0
	xor := 0
	mp := make(map[int]int)
	mp[0] = -1
	for i := 0; i < n; i++ {
		xor ^= 1 << (s[i] - '0')
		if p, ok := mp[xor]; ok {
			res = max(i-p, res)
		}
		for j := 0; j < 10; j++ {
			if p, ok := mp[xor^(1<<j)]; ok {
				res = max(i-p, res)
			}
		}
		if _, ok := mp[xor]; !ok {
			mp[xor] = i
		}
	}
	return res
}
