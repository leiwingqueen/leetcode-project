package string

import "fmt"

// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
//
//例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
//给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
//
//
//
//示例 1：
//
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
//示例 2：
//
//输入：s = "0000"
//输出：["0.0.0.0"]
//示例 3：
//
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
//
//
//提示：
//
//1 <= s.length <= 20
//s 仅由数字组成

func restoreIpAddresses(s string) []string {
	n := len(s)
	var res []string
	var dfs func(idx1, idx2 int, path []byte)
	dfs = func(idx1, idx2 int, path []byte) {
		if idx1 == n {
			res = append(res, string(path[:idx2-1]))
			return
		}
		if s[idx1] == '0' {
			path[idx2] = '0'
			dfs(idx1+1, idx2+1, path)
		} else {
			num := 0
			for i := 0; i < 3; i++ {
				if idx1+i >= n || num > 255 {
					break
				}
				path[idx2+i] = s[idx1+i]
				path[idx2+i+1] = '.'
				dfs(idx1+i+1, idx2+i+2, path)
				num = num*10 + int(s[idx1+i]-'0')
			}
		}
	}
	dfs(0, 0, make([]byte, n+4))
	return res
}

// 总算通过
func restoreIpAddresses2(s string) []string {
	n := len(s)
	var res []string
	var dfs func(i, j int, num int, nums []int)
	dfs = func(i, j int, num int, nums []int) {
		if i == n {
			if j == 4 {
				res = append(res, fmt.Sprintf("%d.%d.%d.%d", nums[0], nums[1], nums[2], nums[3]))
			} else if j == 3 {
				if num >= 0 && num <= 255 {
					nums[j] = num
					dfs(i, j+1, -1, nums)
				}
			}
			return
		}
		if j >= 4 || num > 255 {
			return
		}
		k := int(s[i] - '0')
		if num < 0 {
			dfs(i+1, j, k, nums)
		} else if num > 0 {
			// 加一个点
			nums[j] = num
			dfs(i, j+1, -1, nums)
			// 不加点
			dfs(i+1, j, num*10+k, nums)
		} else {
			// 前缀0
			nums[j] = 0
			dfs(i, j+1, -1, nums)
		}
	}
	dfs(0, 0, -1, make([]int, 4))
	return res
}
