package wc487

// 给你一个整数 n。
//
//如果一个整数的二进制表示中所有位都相同，则称其为 单比特数（Monobit）。
//
//返回范围[0, n]（包括两端）内 单比特数 的个数。
//
//
//
//示例 1：
//
//输入： n = 1
//
//输出： 2
//
//解释：
//
//范围[0, 1]内的整数对应的二进制表示为"0"和"1"。
//每个表示都由相同的位组成，因此答案是2。
//示例 2：
//
//输入： n = 4
//
//输出： 3
//
//解释：
//
//范围[0, 4]内的整数对应的二进制表示为"0"、"1"、"10"、"11"和"100"。
//只有0、1和3满足单比特条件。因此答案是3。
//
//
//提示：
//
//0 <= n <= 1000

func countMonobit(n int) int {
	check := func(num int) bool {
		var arr []int
		for num > 0 {
			arr = append(arr, num&0b1)
			num >>= 1
		}
		if len(arr) == 0 {
			return true
		}
		for i := 1; i < len(arr); i++ {
			if arr[i] != arr[0] {
				return false
			}
		}
		return true
	}
	res := 0
	for i := 0; i <= n; i++ {
		if check(i) {
			res++
		}
	}
	return res
}
