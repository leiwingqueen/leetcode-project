package bwc107

// 给你三个整数 x ，y 和 z 。
//
//这三个整数表示你有 x 个 "AA" 字符串，y 个 "BB" 字符串，和 z 个 "AB" 字符串。你需要选择这些字符串中的部分字符串（可以全部选择也可以一个都不选择），将它们按顺序连接得到一个新的字符串。新字符串不能包含子字符串 "AAA" 或者 "BBB" 。
//
//请你返回新字符串的最大可能长度。
//
//子字符串 是一个字符串中一段连续 非空 的字符序列。
//
//
//
//示例 1：
//
//输入：x = 2, y = 5, z = 1
//输出：12
//解释： 我们可以按顺序连接 "BB" ，"AA" ，"BB" ，"AA" ，"BB" 和 "AB" ，得到新字符串 "BBAABBAABBAB" 。
//字符串长度为 12 ，无法得到一个更长的符合题目要求的字符串。
//示例 2：
//
//输入：x = 3, y = 2, z = 2
//输出：14
//解释：我们可以按顺序连接 "AB" ，"AB" ，"AA" ，"BB" ，"AA" ，"BB" 和 "AA" ，得到新字符串 "ABABAABBAABBAA" 。
//字符串长度为 14 ，无法得到一个更长的符合题目要求的字符串。
//
//
//提示：
//
//1 <= x, y, z <= 50

// 典型的DP
// 假设我们把AA、BB、AB定义为p1,p2,p3，那么可能的组合为
// p1->p2
// p2->p1,p2->p3
// p3->p3,p3->p1
// 假设f1(x,y,z)为以p1结尾，使用了x,y,z个模板组合成的字符串
// f1(x,y,z)=max{f2(x-1,y,z),f3(x-1,y,z)}+2
// f2(x,y,z)=f1(x,y-1,z)+2
// f3(x,y,z)=max{f2(x,y,z-1),f3(x,y,z-1)}+2

func longestString(x int, y int, z int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	mem := make([][][][]int, x+1)
	for i := 0; i <= x; i++ {
		mem[i] = make([][][]int, y+1)
		for j := 0; j <= y; j++ {
			mem[i][j] = make([][]int, z+1)
			for l := 0; l <= z; l++ {
				mem[i][j][l] = make([]int, 3)
				for k := 0; k < 3; k++ {
					mem[i][j][l][k] = -1
				}
			}
		}
	}
	var dfs func(x, y, z, k int) int
	dfs = func(x, y, z, k int) int {
		if x == 0 && y == 0 && z == 0 {
			return 0
		}
		if mem[x][y][z][k] >= 0 {
			return mem[x][y][z][k]
		}
		res := 0
		if k == 0 {
			if x > 0 {
				res = max(dfs(x-1, y, z, 1), dfs(x-1, y, z, 2)) + 2
			}
		} else if k == 1 {
			if y > 0 {
				res = dfs(x, y-1, z, 0) + 2
			}
		} else {
			if z > 0 {
				res = max(dfs(x, y, z-1, 1), dfs(x, y, z-1, 2)) + 2
			}
		}
		mem[x][y][z][k] = res
		return res
	}
	res := 0
	for i := 0; i < 3; i++ {
		r := dfs(x, y, z, i)
		if r > res {
			res = r
		}
	}
	return res
}
