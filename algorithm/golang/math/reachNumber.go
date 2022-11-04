package math

// 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
//
//你可以做一些数量的移动 numMoves :
//
//每次你可以选择向左或向右移动。
//第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
//给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
//
//
//
//示例 1:
//
//输入: target = 2
//输出: 3
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 -1 。
//第三次移动，从 -1 到 2 。
//示例 2:
//
//输入: target = 3
//输出: 2
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 3 。
//
//
//提示:
//
//-109 <= target <= 109
//target != 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/reach-a-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// oom
func reachNumber(target int) int {
	queue := []int{0}
	depth := 0
	visit := make(map[int]bool)
	visit[0] = true
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			p := queue[i]
			if p+depth+1 == target || p-depth-1 == target {
				return depth + 1
			}
			queue = append(queue, p+depth+1, p-depth-1)
			/*if !visit[p+depth+1] {
				queue = append(queue, p+depth+1)
			}
			if !visit[p-depth-1] {
				queue = append(queue, p-depth-1)
			}*/
		}
		queue = queue[size:]
		depth++
	}
	return -1
}

// 代码虽然很短，但其实真的不容易想到
func reachNumber2(target int) int {
	if target < 0 {
		target = -target
	}
	s := 0
	i := 1
	for s < target || (s-target)%2 != 0 {
		s += i
		i++
	}
	return i - 1
}
