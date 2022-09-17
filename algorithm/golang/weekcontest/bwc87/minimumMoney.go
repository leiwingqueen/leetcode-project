package bwc87

import "sort"

// 给你一个下标从 0 开始的二维整数数组 transactions，其中transactions[i] = [costi, cashbacki] 。
//
//数组描述了若干笔交易。其中每笔交易必须以 某种顺序 恰好完成一次。在任意一个时刻，你有一定数目的钱 money ，为了完成交易 i ，money >= costi 这个条件必须为真。执行交易后，你的钱数 money 变成 money - costi + cashbacki 。
//
//请你返回 任意一种 交易顺序下，你都能完成所有交易的最少钱数 money 是多少。
//
//
//
//示例 1：
//
//输入：transactions = [[2,1],[5,0],[4,2]]
//输出：10
//解释：
//刚开始 money = 10 ，交易可以以任意顺序进行。
//可以证明如果 money < 10 ，那么某些交易无法进行。
//示例 2：
//
//输入：transactions = [[3,0],[0,3]]
//输出：3
//解释：
//- 如果交易执行的顺序是 [[3,0],[0,3]] ，完成所有交易需要的最少钱数是 3 。
//- 如果交易执行的顺序是 [[0,3],[3,0]] ，完成所有交易需要的最少钱数是 1 。
//所以，刚开始钱数为 3 ，任意顺序下交易都可以全部完成。
//
//
//提示：
//
//1 <= transactions.length <= 105
//transactions[i].length == 2
//0 <= costi, cashbacki <= 109

func minimumMoney(transactions [][]int) int64 {
	sort.Slice(transactions, func(i, j int) bool {
		if transactions[i][0] <= transactions[i][1] && transactions[j][0] <= transactions[j][1] {
			return transactions[i][0] >= transactions[j][0]
		}
		if transactions[i][0] <= transactions[i][1] {
			return false
		}
		if transactions[j][0] <= transactions[j][1] {
			return true
		}
		if transactions[i][0] == transactions[j][0] {
			return transactions[i][1] <= transactions[j][1]
		}
		return transactions[i][0] <= transactions[j][0]
	})
	var res int64
	var cur int64
	for _, t := range transactions {
		if cur < int64(t[0]) {
			diff := int64(t[0]) - cur
			cur += diff
			res += diff
		}
		cur += int64(-t[0]) + int64(t[1])
	}
	return res
}
