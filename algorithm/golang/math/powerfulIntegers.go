package math

// 给定三个整数 x 、 y 和 bound ，返回 值小于或等于 bound 的所有 强整数 组成的列表 。
//
//如果某一整数可以表示为 xi + yj ，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个 强整数 。
//
//你可以按 任何顺序 返回答案。在你的回答中，每个值 最多 出现一次。
//
//
//
//示例 1：
//
//输入：x = 2, y = 3, bound = 10
//输出：[2,3,4,5,7,9,10]
//解释：
//2 = 20 + 30
//3 = 21 + 30
//4 = 20 + 31
//5 = 21 + 31
//7 = 22 + 31
//9 = 23 + 30
//10 = 20 + 32
//示例 2：
//
//输入：x = 3, y = 5, bound = 15
//输出：[2,4,6,8,10,14]
//
//
//提示：
//
//1 <= x, y <= 100
//0 <= bound <= 106
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/powerful-integers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func powerfulIntegers(x int, y int, bound int) []int {
	if x == 1 && y == 1 {
		if bound < 2 {
			return []int{}
		} else {
			return []int{2}
		}
	}
	if x == 1 {
		var res []int
		yn := 1
		for 1+yn <= bound {
			res = append(res, 1+yn)
			yn *= y
		}
		return res
	}
	if y == 1 {
		var res []int
		xn := 1
		for 1+xn <= bound {
			res = append(res, 1+xn)
			xn *= x
		}
		return res
	}
	xn, yn := 1, 1
	mp := make(map[int]struct{})
	for xn+yn <= bound {
		for xn+yn <= bound {
			mp[xn+yn] = struct{}{}
			yn *= y
		}
		yn = 1
		xn *= x
	}
	res := make([]int, 0)
	for k := range mp {
		res = append(res, k)
	}
	return res
}

// 写法上进行一些优化
func powerfulIntegers2(x int, y int, bound int) []int {
	mp := make(map[int]struct{})
	xn, yn := 1, 1
	for i := 0; i < 20 && xn+yn <= bound; i++ {
		for j := 0; j < 20; j++ {
			if xn+yn > bound {
				break
			}
			mp[xn+yn] = struct{}{}
			yn *= y
		}
		xn *= x
		yn = 1
	}
	var res []int
	for k := range mp {
		res = append(res, k)
	}
	return res
}
