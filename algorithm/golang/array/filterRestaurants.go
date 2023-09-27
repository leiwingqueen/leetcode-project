package array

import "sort"

//给你一个餐馆信息数组 restaurants，其中 restaurants[i] = [idi, ratingi, veganFriendlyi,
//pricei, distancei]。你必须使用以下三个过滤器来过滤这些餐馆信息。
//
// 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括
//veganFriendlyi 为 true 的餐馆，为 false 则意味着可以包括任何餐馆。此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，
//它们分别考虑餐厅的价格因素和距离因素的最大值。
//
// 过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序。简单起见， veganFriendlyi
//和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
//
//
//
// 示例 1：
//
// 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,
//1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
//输出：[3,1,5]
//解释：
//这些餐馆为：
//餐馆 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
//餐馆 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
//餐馆 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
//餐馆 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
//餐馆 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
//在按照 veganFriendly = 1, maxPrice = 50 和 maxDistance = 10 进行过滤后，我们得到了餐馆 3, 餐馆 1
//和 餐馆 5（按评分从高到低排序）。
//
//
// 示例 2：
//
// 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,
//1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
//输出：[4,3,2,1,5]
//解释：餐馆与示例 1 相同，但在 veganFriendly = 0 的过滤条件下，应该考虑所有餐馆。
//
//
// 示例 3：
//
// 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,
//1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
//输出：[4,5]
//
//
//
//
// 提示：
//
//
// 1 <= restaurants.length <= 10^4
// restaurants[i].length == 5
// 1 <= idi, ratingi, pricei, distancei <= 10^5
// 1 <= maxPrice, maxDistance <= 10^5
// veganFriendlyi 和 veganFriendly 的值为 0 或 1 。
// 所有 idi 各不相同。
//
//
// Related Topics 数组 排序 👍 43 👎 0

func filterRestaurants(restaurants [][]int, veganFriendly int, maxPrice int, maxDistance int) []int {
	sort.Slice(restaurants, func(i, j int) bool {
		if restaurants[i][1] != restaurants[j][1] {
			return restaurants[i][1] > restaurants[j][1]
		} else {
			return restaurants[i][0] > restaurants[j][0]
		}
	})
	var res []int
	for _, re := range restaurants {
		id := re[0]
		vegan := re[2]
		price := re[3]
		distance := re[4]
		if (veganFriendly == 0 || vegan == 1) && maxPrice >= price && maxDistance >= distance {
			res = append(res, id)
		}
	}
	return res
}
