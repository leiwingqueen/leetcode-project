package backtrace

//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
//
//生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
//
//
//
//示例 1：
//
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0
//(2-(1-1)) = 2
//示例 2：
//
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10
//
//
//提示：
//
//1 <= expression.length <= 20
//expression 由数字和算符 '+'、'-' 和 '*' 组成。
//输入表达式中的所有整数值在范围 [0, 99]
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func diffWaysToCompute(expression string) []int {
	var dfs func(l int, r int) []int
	dfs = func(l int, r int) []int {
		if l == r {
			return []int{}
		}
		p := l
		res := make([]int, 0)
		//mp := make(map[int]struct{})
		sum := 0
		//上一次的运算符
		op := byte('+')
		for p < r {
			num := 0
			for p < r && expression[p] >= '0' && expression[p] <= '9' {
				num = num*10 + int(expression[p]-'0')
				p++
			}
			switch op {
			case '+':
				sum += num
			case '-':
				sum -= num
			case '*':
				sum *= num
			case '/':
				sum /= num
			}
			if p == r {
				res = append(res, sum)
				return res
			}
			sub := dfs(p+1, r)
			op = expression[p]
			switch op {
			case '+':
				for _, v := range sub {
					res = append(res, sum+v)
				}
			case '-':
				for _, v := range sub {
					res = append(res, sum-v)
				}
			case '*':
				for _, v := range sub {
					res = append(res, sum*v)
				}
			case '/':
				for _, v := range sub {
					res = append(res, sum/v)
				}
			}
			p++
		}
		return res
	}
	return dfs(0, len(expression))
}
