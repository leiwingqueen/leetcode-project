package binarysearch

// 给你一个函数  f(x, y) 和一个目标结果 z，函数公式未知，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。满足条件的结果数对可以按任意顺序返回。
//
//尽管函数的具体式子未知，但它是单调递增函数，也就是说：
//
//f(x, y) < f(x + 1, y)
//f(x, y) < f(x, y + 1)
//函数接口定义如下：
//
//interface CustomFunction {
//public:
//  // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
//  int f(int x, int y);
//};
//你的解决方案将按如下规则进行评判：
//
//判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
//判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
//判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
//如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。
//
//
//示例 1：
//
//输入：function_id = 1, z = 5
//输出：[[1,4],[2,3],[3,2],[4,1]]
//解释：function_id = 1 暗含的函数式子为 f(x, y) = x + y
//以下 x 和 y 满足 f(x, y) 等于 5：
//x=1, y=4 -> f(1, 4) = 1 + 4 = 5
//x=2, y=3 -> f(2, 3) = 2 + 3 = 5
//x=3, y=2 -> f(3, 2) = 3 + 2 = 5
//x=4, y=1 -> f(4, 1) = 4 + 1 = 5
//示例 2：
//
//输入：function_id = 2, z = 5
//输出：[[1,5],[5,1]]
//解释：function_id = 2 暗含的函数式子为 f(x, y) = x * y
//以下 x 和 y 满足 f(x, y) 等于 5：
//x=1, y=5 -> f(1, 5) = 1 * 5 = 5
//x=5, y=1 -> f(5, 1) = 5 * 1 = 5
//
//
//提示：
//
//1 <= function_id <= 9
//1 <= z <= 100
//题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。
//在 1 <= x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

// 先暴力
func findSolution(customFunction func(int, int) int, z int) [][]int {
	res := make([][]int, 0)
	for x := 1; x <= 1000; x++ {
		for y := 1; y <= 1000; y++ {
			if customFunction(x, y) == z {
				res = append(res, []int{x, y})
			}
		}
	}
	return res
}

// 既然有单调性，我们很容易想到二分
func findSolution2(customFunction func(int, int) int, z int) [][]int {
	res := make([][]int, 0)
	for x := 1; x <= 1000; x++ {
		if customFunction(x, 1) > z || customFunction(x, 1000) < z {
			continue
		}
		l := 1
		r := 1000
		for l <= r {
			mid := l + (r-l)/2
			if customFunction(x, mid) == z {
				res = append(res, []int{x, mid})
				break
			} else if customFunction(x, mid) < z {
				l = mid + 1
			} else {
				r = mid - 1
			}
		}
	}
	return res
}
