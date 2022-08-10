package math

import "fmt"

// 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
//
//如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
//
//题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
//
//
//
//示例 1：
//
//输入: equation = "x+5-3+x=6+x-2"
//输出: "x=2"
//示例 2:
//
//输入: equation = "x=x"
//输出: "Infinite solutions"
//示例 3:
//
//输入: equation = "2x=x"
//输出: "x=0"
//
//
//提示:
//
//3 <= equation.length <= 1000
//equation 只有一个 '='.
//equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。
//​​​
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/solve-the-equation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func solveEquation(equation string) string {
	n := len(equation)
	//l := 0
	r := 0
	//x的系数
	p1 := 0
	//常数
	p2 := 0
	//等号的右侧需要做反转
	revert := false
	for r < n {
		if equation[r] == '=' {
			revert = true
			r++
			continue
		}
		positive := true
		if equation[r] == '+' {
			positive = true
			r++
		} else if equation[r] == '-' {
			positive = false
			r++
		}
		n1 := 0
		n2 := 0
		if equation[r] == 'x' {
			n1 = 1
			r++
		} else {
			//扫描数字
			num := 0
			for r < n && equation[r] >= '0' && equation[r] <= '9' {
				num = num*10 + int(equation[r]-'0')
				r++
			}
			if r < n && equation[r] == 'x' {
				n1 = num
				r++
			} else {
				n2 = num
			}
		}
		if (positive && !revert) || (!positive && revert) {
			p1 += n1
			p2 += n2
		} else {
			p1 -= n1
			p2 -= n2
		}
	}
	if p1 == 0 {
		if p2 == 0 {
			return "Infinite solutions"
		} else {
			return "No solution"
		}
	} else {
		return fmt.Sprintf("x=%d", -p2/p1)
	}
}
