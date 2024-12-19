package random_point

import "math/rand"

// 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
//
//实现 Solution 类:
//
//Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
//randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
//
//
//示例 1：
//
//输入:
//["Solution","randPoint","randPoint","randPoint"]
//[[1.0, 0.0, 0.0], [], [], []]
//输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
//解释:
//Solution solution = new Solution(1.0, 0.0, 0.0);
//solution.randPoint ();//返回[-0.02493，-0.38077]
//solution.randPoint ();//返回[0.82314,0.38945]
//solution.randPoint ();//返回[0.36572,0.17248]
//
//
//提示：
//
//0 < radius <= 108
//-107 <= x_center, y_center <= 107
//randPoint 最多被调用 3 * 104 次

type Solution struct {
	x      float64
	y      float64
	radius float64
}

func Constructor(radius float64, x_center float64, y_center float64) Solution {
	return Solution{x_center, y_center, radius}
}

func (this *Solution) RandPoint() []float64 {
	// [x-radius,x+radius]
	// [y-radius,y+radius]
	randPoint := func() []float64 {
		x := rand.Float64()*2*this.radius + this.x - this.radius
		y := rand.Float64()*2*this.radius + this.y - this.radius
		return []float64{x, y}
	}
	for {
		point := randPoint()
		if (point[0]-this.x)*(point[0]-this.x)+(point[1]-this.y)*(point[1]-this.y) <= this.radius*this.radius {
			return point
		}
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(radius, x_center, y_center);
 * param_1 := obj.RandPoint();
 */
