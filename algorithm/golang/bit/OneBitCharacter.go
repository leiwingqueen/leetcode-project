package bit

//有两种特殊字符：
//
//第一种字符可以用一个比特 0 来表示
//第二种字符可以用两个比特(10 或 11)来表示、
//给定一个以 0 结尾的二进制数组 _bits ，如果最后一个字符必须是一位字符，则返回 true 。
//
//
//
//示例 1:
//
//输入: _bits = [1, 0, 0]
//输出: true
//解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
//所以最后一个字符是一比特字符。
//示例 2:
//
//输入: _bits = [1, 1, 1, 0]
//输出: false
//解释: 唯一的编码方式是两比特字符和两比特字符。
//所以最后一个字符不是一比特字符。
//
//
//提示:
//
//1 <= _bits.length <= 1000
//_bits[i] == 0 or 1
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
func isOneBitCharacter(bits []int) bool {
	idx := 0
	last := 1
	for idx < len(bits) {
		if bits[idx] == 0 {
			last = 1
			idx++
		} else {
			last = 2
			idx += 2
		}
	}
	return last == 1
}
