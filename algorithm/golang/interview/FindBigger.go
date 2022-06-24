package interview

//字节面试题
//查找右边第一个比自身大的数字
func findBigger(arr []int) []int {
	stack := make([]int, 0)
	res := make([]int, len(arr))
	for i := len(arr) - 1; i >= 0; i-- {
		//单调栈
		for len(stack) > 0 && stack[len(stack)-1] < arr[i] {
			stack = stack[0 : len(stack)-1]
		}
		if len(stack) > 0 {
			res[i] = stack[len(stack)-1]
		} else {
			res[i] = -1
		}
		stack = append(stack, arr[i])
	}
	return res
}
