package dp

/**
给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

 

示例 1:

输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
示例 2:

输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。
示例 3:

输入: amount = 10, coins = [10]
输出: 1
 

注意:

你可以假设：

0 <= amount (总金额) <= 5000
1 <= coin (硬币面额) <= 5000
硬币种类不超过 500 种
结果符合 32 位符号整数


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-change-2
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func change(amount int, coins []int) int {
	if amount <= 0 {
		return 1
	}
	dp := make([][]int, amount+1)
	for i := 0; i <= amount; i++ {
		dp[i] = make([]int, len(coins))
	}
	for i := 1; i <= amount; i++ {
		for j := 0; j < len(coins); j++ {
			if i-coins[j] == 0 {
				dp[i][j] = 1
			} else if i-coins[j] > 0 {
				for k := 0; k <= j; k++ {
					dp[i][j] += dp[i-coins[j]][k]
				}
			}
		}
	}
	sum := 0
	for i := 0; i < len(coins); i++ {
		sum += dp[amount][i]
	}
	return sum
}

/**
优化2

f(k,n)=f(k,n-coins[k-1])+f(k-1,n)
f(k,n)表示前k个硬币构成金额为n的组合数
*/
func change2(amount int, coins []int) int {
	len := len(coins)
	dp := make([][]int, len+1)
	for i := 0; i <= len; i++ {
		dp[i] = make([]int, amount+1)
	}
	//初始化
	for i := 1; i <= len; i++ {
		dp[i][0] = 1
	}
	for i := 1; i <= len; i++ {
		for j := 1; j <= amount; j++ {
			dp[i][j] += dp[i-1][j]
			if j-coins[i-1] >= 0 {
				dp[i][j] += dp[i][j-coins[i-1]]
			}
		}
	}
	return dp[len][amount]
}

/**
空间优化

挺巧妙的，由于
f(k,n)=f(k,n-coins[k-1])+f(k-1,n)

f(k,n)和f(k-1,n)属于同一列的数据，我们可以少存f(k-1,n)，直接在原来的元素上面累加即可
*/
func change3(amount int, coins []int) int {
	len := len(coins)
	dp := make([]int, amount+1)
	//初始化
	dp[0] = 1
	for i := 0; i < len; i++ {
		for j := 1; j <= amount; j++ {
			if j-coins[i] >= 0 {
				dp[j] += dp[j-coins[i]]
			}
		}
	}
	return dp[amount]
}
