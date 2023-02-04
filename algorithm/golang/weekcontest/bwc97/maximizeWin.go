package bwc97

// 在 X轴 上有一些奖品。给你一个整数数组 prizePositions ，它按照 非递减 顺序排列，其中 prizePositions[i] 是第 i 件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数 k 。
//
//你可以选择两个端点为整数的线段。每个线段的长度都必须是 k 。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。
//
//比方说 k = 2 ，你可以选择线段 [1, 3] 和 [2, 4] ，你可以获得满足 1 <= prizePositions[i] <= 3 或者 2 <= prizePositions[i] <= 4 的所有奖品 i 。
//请你返回在选择两个最优线段的前提下，可以获得的 最多 奖品数目。
//
//
//
//示例 1：
//
//输入：prizePositions = [1,1,2,2,3,3,5], k = 2
//输出：7
//解释：这个例子中，你可以选择线段 [1, 3] 和 [3, 5] ，获得 7 个奖品。
//示例 2：
//
//输入：prizePositions = [1,2,3,4], k = 0
//输出：2
//解释：这个例子中，一个选择是选择线段 [3, 3] 和 [4, 4] ，获得 2 个奖品。
//
//
//提示：
//
//1 <= prizePositions.length <= 105
//1 <= prizePositions[i] <= 109
//0 <= k <= 109
//prizePositions 有序非递减。

func maximizeWin(prizePositions []int, k int) int {
	n := len(prizePositions)
	dp0 := make([]int, n)
	dp0[0] = 1
	// 找到最小的下标，prizePositions[i]>=pos
	search := func(pos int) int {
		if prizePositions[n-1] < pos {
			return -1
		}
		l := 0
		r := n - 1
		for l < r {
			mid := l + (r-l)/2
			if prizePositions[mid] >= pos {
				r = mid
			} else {
				l = mid + 1
			}
		}
		return l
	}
	for i := 1; i < n; i++ {
		dp0[i] = dp0[i-1]
		start := search(prizePositions[i] - k)
		if i-start+1 > dp0[i] {
			dp0[i] = i - start + 1
		}
	}
	dp1 := make([]int, n)
	dp1[0] = 1
	for i := 1; i < n; i++ {
		dp1[i] = dp1[i-1]
		start := search(prizePositions[i] - k)
		sub := i - start + 1
		if start > 0 {
			sub += dp0[start-1]
		}
		if sub > dp1[i] {
			dp1[i] = sub
		}
	}
	return dp1[n-1]
}
