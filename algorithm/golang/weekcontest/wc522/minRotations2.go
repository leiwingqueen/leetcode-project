package wc522

// 跟上面一题唯一的区别是这次能反转
// 其实反转只能变更一个差值。k-1,k会变更为k-1和n-1
func minRotations2(n int, s string) int {
	abs := func(num int) int {
		if num < 0 {
			return -num
		}
		return num
	}
	// 等价于这个写法
	cal := func(pre, target int) int {
		return min(abs(target-pre), 10-abs(target-pre))
	}
	pre := 0
	res := 0
	maxDiff := 0
	lastNum := int(s[len(s)-1] - '0')
	for _, ch := range s {
		target := int(ch - '0')
		d1 := cal(pre, target)
		d2 := cal(pre, lastNum)
		diff := d1 - d2
		maxDiff = max(maxDiff, diff)
		res += d1
		pre = target
	}
	return res - maxDiff
}
