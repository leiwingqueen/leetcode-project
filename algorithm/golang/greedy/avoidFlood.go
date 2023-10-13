package greedy

// 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨前是空的，那么它就会装满水。如果第 n 个湖泊下雨前是 满的 ，这个湖泊会发生 洪水 。你的目标是避免任意一个湖泊发生洪水。
//
//给你一个整数数组 rains ，其中：
//
//rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
//rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
//请返回一个数组 ans ，满足：
//
//ans.length == rains.length
//如果 rains[i] > 0 ，那么ans[i] == -1 。
//如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
//如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
//
//请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
//
//
//
//示例 1：
//
//输入：rains = [1,2,3,4]
//输出：[-1,-1,-1,-1]
//解释：第一天后，装满水的湖泊包括 [1]
//第二天后，装满水的湖泊包括 [1,2]
//第三天后，装满水的湖泊包括 [1,2,3]
//第四天后，装满水的湖泊包括 [1,2,3,4]
//没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
//示例 2：
//
//输入：rains = [1,2,0,0,2,1]
//输出：[-1,-1,2,1,-1,-1]
//解释：第一天后，装满水的湖泊包括 [1]
//第二天后，装满水的湖泊包括 [1,2]
//第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
//第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
//第五天后，装满水的湖泊包括 [2]。
//第六天后，装满水的湖泊包括 [1,2]。
//可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
//示例 3：
//
//输入：rains = [1,2,0,1,2]
//输出：[]
//解释：第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。
//但第三天后，湖泊 1 和 2 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。
//
//
//提示：
//
//1 <= rains.length <= 105
//0 <= rains[i] <= 109

func avoidFlood(rains []int) []int {
	n := len(rains)
	mp := make(map[int]int)
	res := make([]int, n)
	idx := -1
	for i, r := range rains {
		if r > 0 {
			res[i] = -1
			mp[r]++
			if mp[r] > 1 {
				// 选择前面的空闲的一天放水
				if idx < 0 {
					return []int{}
				} else {
					mp[r]--
					res[idx] = r
					idx++
					for idx < i && rains[idx] > 0 {
						idx++
					}
					if idx == i {
						idx = -1
					}
				}
			}
		} else {
			if idx < 0 {
				idx = i
			}
		}
	}
	// 补0操作
	if idx >= 0 {
		for ; idx < n; idx++ {
			if res[idx] == 0 {
				res[idx] = 1
			}
		}
	}
	return res
}

// 简单思路，勉强通过
func avoidFlood2(rains []int) []int {
	n := len(rains)
	res := make([]int, n)
	prePos := make(map[int]int)
	for i, r := range rains {
		if r > 0 {
			res[i] = -1
			if pre, ok := prePos[r]; ok {
				j := pre + 1
				// 这里用treemap去维护会更好
				for ; j < i; j++ {
					if res[j] == 0 {
						res[j] = r
						delete(prePos, r)
						break
					}
				}
				if j == i {
					// 找不到对应位置
					return []int{}
				}
			}
			prePos[r] = i
		}
	}
	for i := 0; i < n; i++ {
		if res[i] == 0 {
			res[i] = 1
		}
	}
	return res
}
