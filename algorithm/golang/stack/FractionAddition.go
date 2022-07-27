package stack

import "fmt"

//592. 分数加减运算
//给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。
//
//这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
//
//
//
//示例 1:
//
//输入: expression = "-1/2+1/2"
//输出: "0/1"
// 示例 2:
//
//输入: expression = "-1/2+1/2+1/3"
//输出: "1/3"
//示例 3:
//
//输入: expression = "1/3-1/2"
//输出: "-1/6"
//
//
//提示:
//
//输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。
//输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
//输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
//输入的分数个数范围是 [1,10]。
//最终结果的分子与分母保证是 32 位整数范围内的有效整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func fractionAddition(expression string) string {
	//语法解析
	stack := make([][]int, 0)
	p := 0
	n := len(expression)
	for p < n {
		possitive := true
		if expression[p] == '-' {
			possitive = false
			p++
		} else if expression[p] == '+' {
			possitive = true
			p++
		}
		//分子
		num1 := 0
		for p < n && expression[p] != '/' {
			num1 = num1*10 + int(expression[p]-'0')
			p++
		}
		p++
		if !possitive {
			num1 = -num1
		}
		num2 := 0
		for p < n && expression[p] != '-' && expression[p] != '+' {
			num2 = num2*10 + int(expression[p]-'0')
			p++
		}
		stack = append(stack, []int{num1, num2})
	}
	//计算
	res := stack[0]
	stack = stack[1:]
	for len(stack) > 0 {
		pop := stack[0]
		stack = stack[1:]
		g := gcd(pop[1], res[1])
		n1 := pop[1] / g
		n2 := res[1] / g
		res[0] *= n1
		res[1] *= n1
		pop[0] *= n2
		pop[1] *= n2
		res[0] += pop[0]
		//简化下数字
		g2 := gcd(abs(res[0]), res[1])
		res[0] /= g2
		res[1] /= g2
	}
	return fmt.Sprintf("%d/%d", res[0], res[1])
}

func abs(a int) int {
	if a < 0 {
		return -a
	} else {
		return a
	}
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	} else {
		return gcd(b, a%b)
	}
}
