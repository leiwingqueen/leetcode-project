package array

//给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
//
//注意：请不要在超过该数组长度的位置写入元素。
//
//要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
//
//
//
//示例 1：
//
//输入：[1,0,2,3,0,4,5,0]
//输出：null
//解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
//示例 2：
//
//输入：[1,2,3]
//输出：null
//解释：调用函数后，输入的数组将被修改为：[1,2,3]
//
//
//提示：
//
//1 <= arr.length <= 10000
//0 <= arr[i] <= 9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/duplicate-zeros
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func duplicateZeros(arr []int) {
	idx := 0
	for idx < len(arr) {
		if arr[idx] != 0 {
			idx++
		} else {
			//后面所有元素右移
			for i := len(arr) - 1; i > idx+1; i-- {
				arr[i] = arr[i-1]
			}
			if idx+1 >= len(arr) {
				return
			}
			arr[idx+1] = 0
			idx += 2
		}
	}
}

//关键还是从后往前扫描，能用这个解法的其实不能算简单题
func duplicateZeros2(arr []int) {
	//先求出最后存放的元素
	p1 := 0
	p2 := 0
	for p2 < len(arr) {
		if arr[p1] == 0 {
			p2 += 2
		} else {
			p2++
		}
		p1++
	}
	p1--
	p2--
	//从后往前扫描
	for p2 >= 0 {
		if arr[p1] == 0 {
			if p2 < len(arr) {
				arr[p2] = 0
			}
			p2--
			arr[p2] = 0
			p2--
		} else {
			arr[p2] = arr[p1]
			p2--
		}
		p1--
	}
}
