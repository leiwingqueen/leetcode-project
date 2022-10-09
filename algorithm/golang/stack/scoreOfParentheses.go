package stack

// 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
//
//() 得 1 分。
//AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
//(A) 得 2 * A 分，其中 A 是平衡括号字符串。
//
//
//示例 1：
//
//输入： "()"
//输出： 1
//示例 2：
//
//输入： "(())"
//输出： 2
//示例 3：
//
//输入： "()()"
//输出： 2
//示例 4：
//
//输入： "(()(()))"
//输出： 6
//
//
//提示：
//
//S 是平衡括号字符串，且只含有 ( 和 ) 。
//2 <= S.length <= 50
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/score-of-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func scoreOfParentheses(s string) int {
	stack := []int{0}
	for i := 0; i < len(s); i++ {
		if s[i] == '(' {
			stack = append(stack, 0)
		} else {
			var num int
			if stack[len(stack)-1] == 0 {
				num = 1
			} else {
				num = stack[len(stack)-1] * 2
			}
			stack = stack[0 : len(stack)-1]
			stack[len(stack)-1] += num
		}
	}
	return stack[0]
}
