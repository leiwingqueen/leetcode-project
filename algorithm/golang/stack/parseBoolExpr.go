package stack

// 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
//
//有效的表达式需遵循以下约定：
//
//"t"，运算结果为 True
//"f"，运算结果为 False
//"!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
//"&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
//"|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
//
//
//示例 1：
//
//输入：expression = "!(f)"
//输出：true
//示例 2：
//
//输入：expression = "|(f,t)"
//输出：true
//示例 3：
//
//输入：expression = "&(t,f)"
//输出：false
//示例 4：
//
//输入：expression = "|(&(t,f,t),!(t))"
//输出：false
//
//
//提示：
//
//1 <= expression.length <= 20000
//expression[i] 由 {'(', ')', '&', '|', '!', 't', 'f', ','} 中的字符组成。
//expression 是以上述形式给出的有效表达式，表示一个布尔值。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/parsing-a-boolean-expression
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 递归，但是写起来好难受
func parseBoolExpr(expression string) bool {
	var dfs func(l int, r int) bool
	dfs = func(l int, r int) bool {
		//[l+2,r-1)是里面的内容
		arr := make([]bool, 0)
		p1 := l + 2
		p2 := p1
		for p2 < r-1 {
			if expression[p2] == ',' {
				p2++
			} else if expression[p2] == 't' {
				arr = append(arr, true)
				p2++
			} else if expression[p2] == 'f' {
				arr = append(arr, false)
				p2++
			} else {
				leftCnt := 1
				p2 += 2
				for leftCnt > 0 {
					if expression[p2] == '(' {
						leftCnt++
					} else if expression[p2] == ')' {
						leftCnt--
					}
					p2++
				}
				sub := dfs(p1, p2)
				arr = append(arr, sub)
			}
			p1 = p2
		}
		if expression[l] == '!' {
			return !arr[0]
		} else if expression[l] == '&' {
			res := true
			for _, item := range arr {
				if !item {
					res = false
					break
				}
			}
			return res
		} else {
			res := false
			for _, item := range arr {
				if item {
					res = true
					break
				}
			}
			return res
		}
	}
	return dfs(0, len(expression))
}

// TODO:栈解法
