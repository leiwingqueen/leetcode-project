package dp

import "fmt"

// 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
//
//每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
//
//找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
//
//
//
//示例 1：
//
//输入：stones = [3,2,4,1], K = 2
//输出：20
//解释：
//从 [3, 2, 4, 1] 开始。
//合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
//合并 [4, 1]，成本为 5，剩下 [5, 5]。
//合并 [5, 5]，成本为 10，剩下 [10]。
//总成本 20，这是可能的最小值。
//示例 2：
//
//输入：stones = [3,2,4,1], K = 3
//输出：-1
//解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
//示例 3：
//
//输入：stones = [3,5,1,2,6], K = 3
//输出：25
//解释：
//从 [3, 5, 1, 2, 6] 开始。
//合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
//合并 [3, 8, 6]，成本为 17，剩下 [17]。
//总成本 25，这是可能的最小值。
//
//
//提示：
//
//1 <= stones.length <= 30
//2 <= K <= 30
//1 <= stones[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-cost-to-merge-stones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 回溯，必然超时
func mergeStones(stones []int, k int) int {
	n := len(stones)
	var dfs func(idx int, cnt int, sum int) int
	dfs = func(idx int, cnt int, sum int) int {
		if idx == n {
			if cnt == 1 {
				return 0
			} else {
				return -1
			}
		}
		res := -1
		for i := idx; i < n; i++ {
			fmt.Println("选择:", stones[i])
			stones[idx], stones[i] = stones[i], stones[idx]
			sub := -1
			if cnt == k-1 {
				stones[idx] += sum
				sub = dfs(idx, 0, 0) + stones[idx]
				stones[idx] -= sum
			} else {
				sub = dfs(idx+1, cnt+1, sum+stones[idx])
			}
			stones[idx], stones[i] = stones[i], stones[idx]
			if sub >= 0 && (res < 0 || sub < res) {
				res = sub
			}
		}
		fmt.Printf("idx:%d,cnt:%d,res:%d\n", idx, cnt, res)
		return res
	}
	return dfs(0, 0, 0)
}
