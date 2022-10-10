package wc314

//给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。执行以下操作之一，直到 s 和 t 都变成空字符串：
//
//删除字符串 s 的 第一个 字符，并将该字符给机器人。机器人把这个字符添加到 t 的尾部。
//删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
//请你返回纸上能写出的字典序最小的字符串。

func robotWithString(s string) string {
	stack := make([]int, 0)
	for i := len(s) - 1; i >= 0; i-- {
		if len(stack) == 0 || s[i] < s[stack[len(stack)-1]] {
			stack = append(stack, i)
		}
	}
	res := make([]byte, 0)
	pre := 0
	for len(stack) > 0 {
		//[pre,stack[len(stack)-1]]进行反转
		cur := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		for i := cur; i >= pre; i-- {
			res = append(res, s[i])
		}
		pre = cur + 1
	}
	return string(res)
}

func robotWithString2(s string) string {
	stack := make([]int, 0)
	for i := len(s) - 1; i >= 0; i-- {
		if len(stack) == 0 || s[i] < s[stack[len(stack)-1]] {
			stack = append(stack, i)
		}
	}
	n := len(s)
	res := make([]byte, 0)
	p1 := 0
	s2 := make([]int, 0)
	for p1 < n || len(s2) > 0 {
		cur := stack[len(stack)-1]
		for p1 <= cur {
			s2 = append(s2, p1)
			p1++
		}
		stack = stack[:len(stack)-1]
		if len(s2) == 0 || p1 <= stack[len(stack)-1] {

		} else {
			stack = stack[:len(stack)-1]
			res = append(res, s[s2[len(s2)-1]])
			s2 = s2[:len(s2)-1]
		}
	}
	return string(res)
}
