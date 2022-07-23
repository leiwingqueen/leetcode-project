package topological

//给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。还提供了一个 2D 整数数组 sequences ，其中 sequences[i] 是 nums 的子序列。
//检查 nums 是否是唯一的最短 超序列 。最短 超序列 是 长度最短 的序列，并且所有序列 sequences[i] 都是它的子序列。对于给定的数组 sequences ，可能存在多个有效的 超序列 。
//
//例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
//而对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
//如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
//子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3], sequences = [[1,2],[1,3]]
//输出：false
//解释：有两种可能的超序列：[1,2,3]和[1,3,2]。
//序列 [1,2] 是[1,2,3]和[1,3,2]的子序列。
//序列 [1,3] 是[1,2,3]和[1,3,2]的子序列。
//因为 nums 不是唯一最短的超序列，所以返回false。
//示例 2：
//
//输入：nums = [1,2,3], sequences = [[1,2]]
//输出：false
//解释：最短可能的超序列为 [1,2]。
//序列 [1,2] 是它的子序列：[1,2]。
//因为 nums 不是最短的超序列，所以返回false。
//示例 3：
//
//输入：nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
//输出：true
//解释：最短可能的超序列为[1,2,3]。
//序列 [1,2] 是它的一个子序列：[1,2,3]。
//序列 [1,3] 是它的一个子序列：[1,2,3]。
//序列 [2,3] 是它的一个子序列：[1,2,3]。
//因为 nums 是唯一最短的超序列，所以返回true。
//
//
//提示：
//
//n == nums.length
//1 <= n <= 104
//nums 是 [1, n] 范围内所有整数的排列
//1 <= sequences.length <= 104
//1 <= sequences[i].length <= 104
//1 <= sum(sequences[i].length) <= 105
//1 <= sequences[i][j] <= n
//sequences 的所有数组都是 唯一 的
//sequences[i] 是 nums 的一个子序列
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sequence-reconstruction
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//有点难，拓补排序对我而言理解还是不够深刻
func sequenceReconstruction(nums []int, sequences [][]int) bool {
	//入度
	inDegree := make([]int, len(nums)+1)
	//构造图，这里为了去重用map来表示
	graph := make([]map[int]bool, len(nums)+1)
	for i := 0; i <= len(nums); i++ {
		graph[i] = make(map[int]bool)
	}
	//出点的点的并集
	points := make(map[int]bool, 0)
	//构造图
	for _, seq := range sequences {
		points[seq[0]] = true
		for i := 1; i < len(seq); i++ {
			from := seq[i-1]
			to := seq[i]
			if !(graph[from][to]) {
				graph[from][to] = true
				inDegree[to]++
			}
			points[to] = true
		}
	}
	//尝试构造唯一的超序集
	res := make([]int, 0)
	queue := make([]int, 0)
	for p := range points {
		if inDegree[p] == 0 {
			queue = append(queue, p)
		}
	}
	for len(queue) > 0 {
		if len(queue) > 1 {
			return false
		}
		pop := queue[0]
		queue = queue[1:]
		res = append(res, pop)
		for to := range graph[pop] {
			inDegree[to]--
			if inDegree[to] == 0 {
				queue = append(queue, to)
			}
		}
	}
	//比较res和nums
	if len(res) != len(nums) {
		return false
	}
	for i := 0; i < len(res); i++ {
		if res[i] != nums[i] {
			return false
		}
	}
	return true
}
