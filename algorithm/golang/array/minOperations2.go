package array

// 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
//
//在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
//
//返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
//
//每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
//
//
//
//示例 1：
//
//输入：boxes = "110"
//输出：[1,1,3]
//解释：每个盒子对应的最小操作数如下：
//1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
//2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
//3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
//示例 2：
//
//输入：boxes = "001011"
//输出：[11,8,5,4,3,4]
//
//
//提示：
//
//n == boxes.length
//1 <= n <= 2000
//boxes[i] 为 '0' 或 '1'
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 暴力解法，毫无美感
func minOperations2(boxes string) []int {
	var abs = func(num int) int {
		if num > 0 {
			return num
		} else {
			return -num
		}
	}
	n := len(boxes)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		cnt := 0
		for j := 0; j < n; j++ {
			if boxes[j] != '0' {
				cnt += abs(i - j)
			}
		}
		res[i] = cnt
	}
	return res
}

// 优化解法
func minOperations3(boxes string) []int {
	n := len(boxes)
	pre1 := make([]int, n+1)
	pre2 := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre1[i+1] = pre1[i]
		pre2[i+1] = pre2[i]
		if boxes[i] != '0' {
			pre1[i+1]++
			pre2[i+1] += i
		}
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		res[i] = pre1[i]*i - pre2[i] + (pre2[n] - pre2[i]) - (pre1[n]-pre1[i])*i
	}
	return res
}
