package dp

//给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
//
//我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
//
//返回数组能分成的最多块数量。
//
//
//
//示例 1:
//
//输入: arr = [4,3,2,1,0]
//输出: 1
//解释:
//将数组分成2块或者更多块，都无法得到所需的结果。
//例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
//示例 2:
//
//输入: arr = [1,0,2,3,4]
//输出: 4
//解释:
//我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
//然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
//
//
//提示:
//
//n == arr.length
//1 <= n <= 10
//0 <= arr[i] < n
//arr 中每个元素都 不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/max-chunks-to-make-sorted
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//dp解法
//f(n)表示前n个数的最大分块
//f(n)=f(k)+1,我们假设k是从后往前扫描的第一个满足[k,n)的范围在[k,n)的块
func maxChunksToSorted(arr []int) int {
	n := len(arr)
	dp := make([]int, n+1)
	dp[0] = 0
	for i := 1; i <= n; i++ {
		max := -1
		min := -1
		dp[i] = -1
		for j := i; j > 0; j-- {
			if max < 0 || arr[j-1] > max {
				max = arr[j-1]
			}
			if min < 0 || arr[j-1] < min {
				min = arr[j-1]
			}
			// [j-1,i)下标的范围刚好是[j-1,i)
			if max == i-1 && min == j-1 && dp[j-1] >= 0 {
				dp[i] = dp[j-1] + 1
				break
			}
		}
	}
	return dp[n]
}

func maxChunksToSorted2(arr []int) int {
	res := 0
	mx := -1
	for i, v := range arr {
		if mx < 0 || v > mx {
			mx = v
		}
		if mx == i {
			res++
		}
	}
	return res
}
