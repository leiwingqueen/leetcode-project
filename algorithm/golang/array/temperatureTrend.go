package array

// 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
//
//若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
//若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
//若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
//已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
//
//即最大的 n，使得第 i~i+n 天之间，两地气温变化趋势相同
//
//示例 1：
//
//输入： temperatureA = [21,18,18,18,31] temperatureB = [34,32,16,16,17]
//
//输出：2
//
//解释：如下表所示， 第 2～4 天两地气温变化趋势相同，且持续时间最长，因此返回 4-2=2image.png
//
//示例 2：
//
//输入： temperatureA = [5,10,16,-6,15,11,3] temperatureB = [16,22,23,23,25,3,-16]
//
//输出：3
//
//提示：
//
//2 <= temperatureA.length == temperatureB.length <= 1000
//-20 <= temperatureA[i], temperatureB[i] <= 40

func temperatureTrend(temperatureA []int, temperatureB []int) int {
	n := len(temperatureA)
	arr1, arr2 := make([]int, n-1), make([]int, n-1)
	convert := func(a, b int) int {
		if a == b {
			return 0
		} else if a < b {
			return -1
		} else {
			return 1
		}
	}
	for i := 0; i < n-1; i++ {
		arr1[i] = convert(temperatureA[i+1], temperatureA[i])
		arr2[i] = convert(temperatureB[i+1], temperatureB[i])
	}
	res := 0
	l, r := 0, 0
	for r < n-1 {
		for l < n-1 && arr1[l] != arr2[l] {
			l++
		}
		if l == n-1 {
			return res
		}
		r = l
		for r < n-1 && arr1[r] == arr2[r] {
			r++
		}
		res = max(res, r-l)
		l = r
	}
	return res
}
