package array

// 给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。
//
//正整数的 各位数字之和 是其所有位上的对应数字相加的结果。
//
//
//
//示例 1：
//
//输入：num = 4
//输出：2
//解释：
//只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。
//示例 2：
//
//输入：num = 30
//输出：14
//解释：
//只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是：
//2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
//
//
//提示：
//
//1 <= num <= 1000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/count-integers-with-even-digit-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func countEven(num int) int {
	check := func(d int) bool {
		sum := 0
		for d > 0 {
			sum += d % 10
			d /= 10
		}
		return sum&0b1 == 0
	}
	cnt := 0
	for i := 1; i <= num; i++ {
		if check(i) {
			cnt++
		}
	}
	return cnt
}
