package math

//593. 有效的正方形
//给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
//
//点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
//
//一个 有效的正方形 有四条等边和四个等角(90度角)。
//
//
//
//示例 1:
//
//输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
//输出: True
//示例 2:
//
//输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
//输出：false
//示例 3:
//
//输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
//输出：true
//
//
//提示:
//
//p1.length == p2.length == p3.length == p4.length == 2
//-104 <= xi, yi <= 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/valid-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func validSquare(p1 []int, p2 []int, p3 []int, p4 []int) bool {
	arr := make([][]int, 4)
	arr[0] = p1
	arr[1] = p2
	arr[2] = p3
	arr[3] = p4
	check := func(g1 []int, g2 []int) bool {
		d1 := distance(arr[g1[0]], arr[g2[0]])
		d2 := distance(arr[g2[0]], arr[g2[1]])
		if d1 != d2 {
			return false
		}
		d3 := distance(arr[g1[1]], arr[g2[0]])
		d4 := distance(arr[g2[1]], arr[g2[1]])
		if d3 != d4 || d3 != d1 {
			return false
		}
		//对角线，勾股定理
		d5 := distance(arr[g2[0]], arr[g2[1]])
		if d1+d2 == d5 {
			return true
		} else {
			return false
		}
	}
	for i := 0; i < 3; i++ {
		for j := i + 1; j < 4; j++ {
			//分两组(i,j)一组，其他的另外一组
			other := make([]int, 0)
			for k := 0; k < 4; k++ {
				if k != i && k != j {
					other = append(other, k)
				}
			}
			if check([]int{i, j}, other) {
				return true
			}
		}
	}
	return false
}

func distance(p1 []int, p2 []int) int {
	return (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1])
}
