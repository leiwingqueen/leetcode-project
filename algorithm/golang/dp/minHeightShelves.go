package dp

import "math"

//给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。你也会得到一个整数
//shelfWidth 。
//
// 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
//
// 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
//
// 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
//
//
// 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
//
//
// 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
//
// 以这种方式布置书架，返回书架整体可能的最小高度。
//
//
//
// 示例 1：
//
//
//
//
//输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
//输出：6
//解释：
//3 层书架的高度和为 1 + 3 + 2 = 6 。
//第 2 本书不必放在第一层书架上。
//
//
// 示例 2:
//
//
//输入: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
//输出: 4
//
//
//
//
// 提示：
//
//
// 1 <= books.length <= 1000
// 1 <= thicknessi <= shelfWidth <= 1000
// 1 <= heighti <= 1000
//
//
// Related Topics 数组 动态规划 👍 215 👎 0

func minHeightShelves(books [][]int, shelfWidth int) int {
	n := len(books)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		dp[i] = math.MaxInt32
		// [j,i-1]单独作为一层
		mx := 0
		width := 0
		for j := i - 1; j >= 0; j-- {
			width += books[j][0]
			if width > shelfWidth {
				break
			}
			if books[j][1] > mx {
				mx = books[j][1]
			}
			if dp[j]+mx < dp[i] {
				dp[i] = dp[j] + mx
			}
		}
	}
	return dp[n]
}
