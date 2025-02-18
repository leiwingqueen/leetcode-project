package RangeFreqQuery

// 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
//
//子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
//
//请你实现 RangeFreqQuery 类：
//
//RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
//int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
//一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
//
//
//
//示例 1：
//
//输入：
//["RangeFreqQuery", "query", "query"]
//[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
//输出：
//[null, 1, 2]
//
//解释：
//RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
//rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
//rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
//
//
//提示：
//
//1 <= arr.length <= 105
//1 <= arr[i], value <= 104
//0 <= left <= right < arr.length
//调用 query 不超过 105 次。

// 内存溢出
type RangeFreqQuery struct {
	mp     map[int]int
	prefix [][]int
}

func Constructor(arr []int) RangeFreqQuery {
	mp := make(map[int]int)
	for _, v := range arr {
		mp[v]++
	}
	var meta []int
	for num := range mp {
		meta = append(meta, num)
	}
	mp2 := make(map[int]int)
	for i, num := range meta {
		mp2[num] = i
	}
	prefix := make([][]int, len(meta))
	for i := 0; i < len(meta); i++ {
		prefix[i] = make([]int, len(arr)+1)
	}
	for i := 0; i < len(arr); i++ {
		for j := 0; j < len(meta); j++ {
			prefix[j][i+1] = prefix[j][i]
		}
		prefix[mp2[arr[i]]][i+1]++
	}
	return RangeFreqQuery{
		mp:     mp2,
		prefix: prefix,
	}
}

func (this *RangeFreqQuery) Query(left int, right int, value int) int {
	idx, ok := this.mp[value]
	if !ok {
		return 0
	}
	return this.prefix[idx][right+1] - this.prefix[idx][left]
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,value);
 */
