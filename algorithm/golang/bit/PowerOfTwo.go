package bit

/**
给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。

如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。

 

示例 1：

输入：n = 1
输出：true
解释：20 = 1
示例 2：

输入：n = 16
输出：true
解释：24 = 16
示例 3：

输入：n = 3
输出：false
示例 4：

输入：n = 4
输出：true
示例 5：

输入：n = 5
输出：false
 

提示：

-231 <= n <= 231 - 1
 

进阶：你能够不使用循环/递归解决此问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/power-of-two
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

func isPowerOfTwo(n int) bool {
	for i := 0; i < 31; i++ {
		if n == (1 << i) {
			return true
		}
	}
	return false
}

func isPowerOfTwo2(n int) bool {
	if n == 1<<31 {
		return false
	}
	return n > 0 && n&(n-1) == 0
}
