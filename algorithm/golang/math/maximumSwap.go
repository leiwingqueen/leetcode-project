package math

//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
//
//示例 1 :
//
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
//示例 2 :
//
//输入: 9973
//输出: 9973
//解释: 不需要交换。
//注意:
//
//给定数字的范围是 [0, 108]
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-swap
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 数据量小，直接暴力
func maximumSwap(num int) int {
	if num <= 9 {
		return num
	}
	arr := make([]int, 0)
	tmp := num
	for tmp > 0 {
		arr = append(arr, tmp%10)
		tmp /= 10
	}
	n := len(arr)
	res := num
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			arr[i], arr[j] = arr[j], arr[i]
			k := 0
			for l := n - 1; l >= 0; l-- {
				k = k*10 + arr[l]
			}
			if k > res {
				res = k
			}
			arr[i], arr[j] = arr[j], arr[i]
		}
	}
	return res
}
