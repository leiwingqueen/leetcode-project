package bwc160

// 给你一个整数 n。
//
//返回 n2 的 十六进制表示 和 n3 的 三十六进制表示 拼接成的字符串。
//
//十六进制 数定义为使用数字 0 – 9 和大写字母 A - F 表示 0 到 15 的值。
//
//三十六进制 数定义为使用数字 0 – 9 和大写字母 A - Z 表示 0 到 35 的值。
//
//
//
//示例 1：
//
//输入：n = 13
//
//输出： "A91P1"
//
//解释：
//
//n2 = 13 * 13 = 169。在十六进制中，它转换为 (10 * 16) + 9 = 169，对应于 "A9"。
//n3 = 13 * 13 * 13 = 2197。在三十六进制中，它转换为 (1 * 362) + (25 * 36) + 1 = 2197，对应于 "1P1"。
//连接两个结果得到 "A9" + "1P1" = "A91P1"。
//示例 2：
//
//输入：n = 36
//
//输出："5101000"
//
//解释：
//
//n2 = 36 * 36 = 1296。在十六进制中，它转换为 (5 * 162) + (1 * 16) + 0 = 1296，对应于 "510"。
//n3 = 36 * 36 * 36 = 46656。在三十六进制中，它转换为 (1 * 363) + (0 * 362) + (0 * 36) + 0 = 46656，对应于 "1000"。
//连接两个结果得到 "510" + "1000" = "5101000"。
//
//
//提示:
//
//1 <= n <= 1000

func concatHex36(n int) string {
	convert16 := func(num int) string {
		if num == 0 {
			return "0"
		}
		var arr []byte
		for num > 0 {
			k := num % 16
			if k < 10 {
				arr = append(arr, byte('0'+k))
			} else {
				arr = append(arr, byte('A'+k-10))
			}
			num /= 16
		}
		l, r := 0, len(arr)-1
		for l < r {
			arr[l], arr[r] = arr[r], arr[l]
			l++
			r--
		}
		return string(arr)
	}
	convert36 := func(num int) string {
		if num == 0 {
			return "0"
		}
		var arr []byte
		for num > 0 {
			k := num % 36
			if k < 10 {
				arr = append(arr, byte('0'+k))
			} else {
				arr = append(arr, byte('A'+k-10))
			}
			num /= 36
		}
		l, r := 0, len(arr)-1
		for l < r {
			arr[l], arr[r] = arr[r], arr[l]
			l++
			r--
		}
		return string(arr)
	}
	return convert16(n*n) + convert36(n*n*n)
}
