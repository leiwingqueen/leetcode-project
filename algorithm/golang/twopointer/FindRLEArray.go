package twopointer

//1868. 两个行程编码数组的积
//行程编码（Run-length encoding）是一种压缩算法，能让一个含有许多段连续重复数字的整数类型数组 nums 以一个（通常更小的）二维数组 encoded 表示。每个 encoded[i] = [vali, freqi] 表示 nums 中第 i 段重复数字，其中 vali 是该段重复数字，重复了 freqi 次。
//
//例如， nums = [1,1,1,2,2,2,2,2] 可表示称行程编码数组 encoded = [[1,3],[2,5]] 。对此数组的另一种读法是“三个 1 ，后面有五个 2 ”。
//两个行程编码数组 encoded1 和 encoded2 的积可以按下列步骤计算：
//
//将 encoded1 和 encoded2 分别扩展成完整数组 nums1 和 nums2 。
//创建一个新的数组 prodNums ，长度为 nums1.length 并设 prodNums[i] = nums1[i] * nums2[i] 。
//将 prodNums 压缩成一个行程编码数组并返回之。
//给定两个行程编码数组 encoded1 和 encoded2 ，分别表示完整数组 nums1 和 nums2 。nums1 和 nums2 的长度相同。 每一个 encoded1[i] = [vali, freqi] 表示 nums1 中的第 i 段，每一个 encoded2[j] = [valj, freqj] 表示 nums2 中的第 j 段。
//
//返回 encoded1 和 encoded2 的乘积。
//
//注：行程编码数组需压缩成可能的最小长度。
//
// 
//
//示例 1:
//
//输入: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
//输出: [[6,6]]
//解释n: encoded1 扩展为 [1,1,1,2,2,2] ，encoded2 扩展为 [6,6,6,3,3,3]。
//prodNums = [6,6,6,6,6,6]，压缩成行程编码数组 [[6,6]]。
//示例 2:
//
//输入: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
//输出: [[2,3],[6,1],[9,2]]
//解释: encoded1 扩展为 [1,1,1,2,3,3] ，encoded2 扩展为 [2,2,2,3,3,3]。
//prodNums = [2,2,2,6,9,9]，压缩成行程编码数组 [[2,3],[6,1],[9,2]]。
// 
//
//提示：
//
//1 <= encoded1.length, encoded2.length <= 105
//encoded1[i].length == 2
//encoded2[j].length == 2
//对于每一个 encoded1[i]， 1 <= vali, freqi <= 104  
//对于每一个 encoded2[j]， 1 <= valj, freqj <= 104
//encoded1 和 encoded2 表示的完整数组长度相同。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/product-of-two-run-length-encoded-arrays
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findRLEArray(encoded1 [][]int, encoded2 [][]int) [][]int {
	p1 := 0
	p2 := 0
	res := make([][]int, 0)
	num := 0
	cnt := 0
	for p1 < len(encoded1) && p2 < len(encoded2) {
		e1 := encoded1[p1]
		e2 := encoded2[p2]
		if num == 0 || e1[0]*e2[0] == num {
			num = e1[0] * e2[0]
			min := e1[1]
			if e2[1] < min {
				min = e2[1]
			}
			e1[1] -= min
			if e1[1] == 0 {
				p1++
			}
			e2[1] -= min
			if e2[1] == 0 {
				p2++
			}
			cnt += min
		} else {
			res = append(res, []int{num, cnt})
			num = 0
			cnt = 0
		}
	}
	if num > 0 {
		res = append(res, []int{num, cnt})
	}
	return res
}
