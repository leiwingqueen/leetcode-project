package bwc139

// 给你一个整数数组 nums 和一个 正 整数 k 。
//
// 定义长度为 2 * x 的序列 seq 的 值 为：
//
// (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
// 请你求出 nums 中所有长度为 2 * k 的 子序列 的 最大值 。
//
// 示例 1：
//
// 输入：nums = [2,6,7], k = 1
//
// 输出：5
//
// 解释：
//
// 子序列 [2, 7] 的值最大，为 2 XOR 7 = 5 。
//
// 示例 2：
//
// 输入：nums = [4,2,5,6,7], k = 2
//
// 输出：2
//
// 解释：
//
// 子序列 [4, 5, 6, 7] 的值最大，为 (4 OR 5) XOR (6 OR 7) = 2 。
//
// 提示：
//
// 2 <= nums.length <= 400
// 1 <= nums[i] < 27
// 1 <= k <= nums.length / 2

// f(i,j,x)为[0,i)选择j个数字值为x是否可能
// 超时，差点能过
func maxValue(nums []int, k int) int {
	n := len(nums)
	mx := (1 << 7) - 1
	pre := make([][][]bool, n+1)
	for i := 0; i <= n; i++ {
		pre[i] = make([][]bool, k+1)
		for j := 0; j <= k; j++ {
			pre[i][j] = make([]bool, mx+1)
		}
	}
	pre[0][0][0] = true
	for i := 1; i <= n; i++ {
		pre[i][0][0] = true
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			for l := 1; l <= mx; l++ {
				if pre[i-1][j][l] {
					pre[i][j][l] = true
				} else {
					for t := 0; t <= mx; t++ {
						if t|nums[i-1] == l && pre[i-1][j-1][t] {
							pre[i][j][l] = true
							break
						}
					}
				}
			}
		}
	}
	suf := make([][][]bool, n+1)
	for i := 0; i <= n; i++ {
		suf[i] = make([][]bool, k+1)
		for j := 0; j <= k; j++ {
			suf[i][j] = make([]bool, mx+1)
		}
	}
	suf[0][0][0] = true
	for i := 1; i <= n; i++ {
		suf[i][0][0] = true
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			for l := 1; l <= mx; l++ {
				if suf[i-1][j][l] {
					suf[i][j][l] = true
				} else {
					for t := 0; t <= mx; t++ {
						if t|nums[n-i] == l && suf[i-1][j-1][t] {
							suf[i][j][l] = true
							break
						}
					}
				}
			}
		}
	}
	// 尝试以i点为分割[0,i),[i,n)
	res := 0
	for i := k; i <= n-k; i++ {
		for x := 1; x <= mx; x++ {
			if !pre[i][k][x] {
				continue
			}
			for y := 1; y <= mx; y++ {
				if !suf[n-i][k][y] {
					continue
				}
				res = max(x^y, res)
			}
		}
	}
	return res
}
