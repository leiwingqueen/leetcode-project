package random

import "math/rand"

//给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
//
//在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
//
//请注意 ，整数点是具有整数坐标的点。
//
//实现 Solution 类:
//
//Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
//int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
// 
//
//示例 1：
//
//
//
//输入:
//["Solution", "pick", "pick", "pick", "pick", "pick"]
//[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
//输出:
//[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
//
//解释：
//Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
//solution.pick(); // 返回 [1, -2]
//solution.pick(); // 返回 [1, -1]
//solution.pick(); // 返回 [-1, -2]
//solution.pick(); // 返回 [-2, -2]
//solution.pick(); // 返回 [0, 0]
// 
//
//提示：
//
//1 <= rects.length <= 100
//rects[i].length == 4
//-109 <= ai < xi <= 109
//-109 <= bi < yi <= 109
//xi - ai <= 2000
//yi - bi <= 2000
//所有的矩形不重叠。
//pick 最多被调用 104 次。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//带权的随机
type RectangleRandom struct {
	rects   [][]int
	weights []int
	sum     int
}

func Constructor(rects [][]int) RectangleRandom {
	weights := make([]int, len(rects))
	sum := 0
	for i, r := range rects {
		sum += (r[2] - r[0] + 1) * (r[3] - r[1] + 1)
		weights[i] = sum
	}
	return RectangleRandom{rects: rects, weights: weights, sum: sum}
}

func (this *RectangleRandom) Pick() []int {
	//随机到具体的块，然后再均等随机
	seed := rand.Intn(this.sum)
	l := 0
	r := len(this.weights)
	for l < r {
		mid := l + (r-l)/2
		if this.weights[mid] > seed {
			r = mid
		} else {
			l = mid + 1
		}
	}
	choose := this.rects[l]
	xRand := rand.Intn(choose[2] - choose[0] + 1)
	yRand := rand.Intn(choose[3] - choose[1] + 1)
	return []int{choose[0] + xRand, choose[1] + yRand}
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(rects);
 * param_1 := obj.Pick();
 */
