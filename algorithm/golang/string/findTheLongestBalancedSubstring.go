package string

// You are given a binary string s consisting only of zeroes and ones.
//
//A substring of s is considered balanced if all zeroes are before ones and the number of zeroes is equal to the number of ones inside the substring. Notice that the empty substring is considered a balanced substring.
//
//Return the length of the longest balanced substring of s.
//
//A substring is a contiguous sequence of characters within a string.
//
//
//
//Example 1:
//
//Input: s = "01000111"
//Output: 6
//Explanation: The longest balanced substring is "000111", which has length 6.
//Example 2:
//
//Input: s = "00111"
//Output: 4
//Explanation: The longest balanced substring is "0011", which has length 4.
//Example 3:
//
//Input: s = "111"
//Output: 0
//Explanation: There is no balanced substring except the empty substring, so the answer is 0.
//
//
//Constraints:
//
//1 <= s.length <= 50
//'0' <= s[i] <= '1'

func findTheLongestBalancedSubstring(s string) int {
	n := len(s)
	check := func(l, r int) bool {
		p := r
		cnt := 0
		for p >= l && s[p] == '1' {
			cnt++
			p--
		}
		for p >= l && s[p] == '0' {
			p--
		}
		return p < l && cnt*2 == (r-l+1)
	}
	for i := n; i >= 1; i-- {
		for j := 0; j <= n-i; j++ {
			if check(j, j+i-1) {
				return i
			}
		}
	}
	return 0
}

func findTheLongestBalancedSubstring2(s string) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(s)
	cnt0, cnt1 := 0, 0
	res := 0
	for i := 0; i < n; i++ {
		if s[i] == '1' {
			cnt1++
			res = max(res, 2*min(cnt0, cnt1))
		} else if i > 0 && s[i-1] == '1' {
			cnt1 = 0
			cnt0 = 1
		} else {
			cnt0++
		}
	}
	return res
}
