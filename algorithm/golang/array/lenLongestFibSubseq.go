package array

//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
//
//n >= 3
//对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
//给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
//
//（回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
//
//
//
//示例 1：
//
//输入: arr = [1,2,3,4,5,6,7,8]
//输出: 5
//解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
//示例 2：
//
//输入: arr = [1,3,7,11,12,14,18]
//输出: 3
//解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
//
//
//提示：
//
//3 <= arr.length <= 1000
//1 <= arr[i] < arr[i + 1] <= 10^9
//
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//确定前面两个数字就能确定一个斐波那契数量，所以我们可以穷举所有情况，
//对于每一个数列的校验，为了加速查找，可以用一个map保存起来
func lenLongestFibSubseq(arr []int) int {
	mp := make(map[int]bool)
	for _, v := range arr {
		mp[v] = true
	}
	mx := 0
	for i := 0; i < len(arr)-2; i++ {
		for j := i + 1; j < len(arr)-1; j++ {
			//从i,j下标开始的数列
			check := func() int {
				res := 2
				f0 := arr[i]
				f1 := arr[j]
				for mp[f0+f1] {
					res++
					tmp := f0
					f0 = f1
					f1 = tmp + f1
				}
				return res
			}
			r := check()
			if r > mx && r >= 3 {
				mx = r
			}
		}
	}
	return mx
}

//dp解法
func lenLongestFibSubseq2(arr []int) int {
	//保存对应下标
	indexMap := make(map[int]int)
	for i, v := range arr {
		indexMap[v] = i
	}
	dp := make([][]int, len(arr)-1)
	for i := 0; i < len(arr)-1; i++ {
		dp[i] = make([]int, len(arr))
	}
	for i := 0; i < len(arr); i++ {
		dp[0][i] = 2
	}
	//i<j<n
	mx := 2
	for i := 1; i < len(arr)-1; i++ {
		for j := i + 1; j < len(arr); j++ {
			dp[i][j] = 2
			//计算k对应的下标
			sub := arr[j] - arr[i]
			v, exist := indexMap[sub]
			if exist && v < i {
				dp[i][j] = dp[v][i] + 1
			}
			if dp[i][j] > mx {
				mx = dp[i][j]
			}
		}
	}
	if mx > 2 {
		return mx
	} else {
		return 0
	}
}
