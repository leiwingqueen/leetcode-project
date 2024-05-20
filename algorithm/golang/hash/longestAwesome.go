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

func longestAwesome(s string) int {
	n := len(s)
	check2 := func(odd, k int) bool {
		if k%2 == 0 {
			// 必须数字都是偶数
			for i := 0; i < 10; i++ {
				if odd > 0 {
					return false
				}
			}
			return true
		} else {
			cnt := 0
			for i := 0; i < 10; i++ {
				if odd > 0 {
					if cnt > 0 {
						return false
					}
					cnt++
				}
			}
			return true
		}
	}
	check := func(k int) bool {
		odd := 0
		mp := make([]int, 10)
		l, r := 0, 0
		for r < n {
			if r-l < k {
				mp[s[r]-'0']++
				if mp[s[r]-'0']%2 == 1 {
					odd++
				} else if mp[s[r]-'0'] > 1 {
					// 奇数变偶数
					odd--
				}
				r++
			} else {
				// 到达窗口大小
				if check2(odd, k) {
					return true
				}
				mp[s[l]-'0']--
				if mp[s[l]-'0']%2 == 1 {
					odd--
				} else if mp[s[l]-'0'] > 1 {
					// 偶数变奇数
					odd++
				}
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
