package math

// 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
//
//操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
//比方说， "00010" -> "10010"
//操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
//比方说， "00010" -> "00001"
//请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
//
//
//
//示例 1：
//
//输入：binary = "000110"
//输出："111011"
//解释：一个可行的转换为：
//"000110" -> "000101"
//"000101" -> "100101"
//"100101" -> "110101"
//"110101" -> "110011"
//"110011" -> "111011"
//示例 2：
//
//输入：binary = "01"
//输出："01"
//解释："01" 没办法进行任何转换。
//
//
//提示：
//
//1 <= binary.length <= 105
//binary 仅包含 '0' 和 '1' 。

// 从左到右扫描
// 连续的1可以先跳过
// 连续的0可以统一转化成1(n-1个)0
// 直到不能转换，只有一种情况，当前为0，然后下一个数字是1。这里可以尝试看再下一个数字是否0，eg.010可以转化成101。如果下一个数字为1。eg. 011，直接跳到下一位
func maximumBinaryString(binary string) string {
	n := len(binary)
	res := []byte(binary)
	p := 0
	for ; p < n; p++ {
		if res[p] == '0' {
			if p+1 < n && res[p+1] == '0' {
				res[p] = '1'
			} else if p+2 < n && res[p+1] == '1' && res[p+2] == '0' {
				res[p] = '1'
				res[p+1] = '0'
				res[p+2] = '1'
			}
		}
	}
	return string(res)
}

// 超时
func maximumBinaryString2(binary string) string {
	n := len(binary)
	res := []byte(binary)
	p := 0
	for ; p < n; p++ {
		if res[p] == '0' {
			if p+1 < n && res[p+1] == '0' {
				res[p] = '1'
			} else {
				// 尝试把后面的0往前移动
				i := p + 1
				for i < n && res[i] == '1' {
					i++
				}
				if i == n {
					break
				}
				// 交换
				res[p+1] = '0'
				res[i] = '1'
				res[p] = '1'
			}
		}
	}
	return string(res)
}

// 在上面基础上进行优化
func maximumBinaryString3(binary string) string {
	n := len(binary)
	res := []byte(binary)
	p, i := 0, 0
	for ; p < n; p++ {
		if res[p] == '0' {
			if p+1 < n && res[p+1] == '0' {
				res[p] = '1'
			} else {
				// 尝试把后面的0往前移动
				i = max(p+1, i)
				for i < n && res[i] == '1' {
					i++
				}
				if i == n {
					break
				}
				// 交换
				res[p+1] = '0'
				res[i] = '1'
				res[p] = '1'
			}
		}
	}
	return string(res)
}
