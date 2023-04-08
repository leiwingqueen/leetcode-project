package dp

// 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
//
//所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
//
//例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
//请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。
//
//
//
//示例 1：
//
//输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
//输出：[0,2]
//示例 2：
//
//输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
//输出：[1,2]
//
//
//提示：
//
//1 <= req_skills.length <= 16
//1 <= req_skills[i].length <= 16
//req_skills[i] 由小写英文字母组成
//req_skills 中的所有字符串 互不相同
//1 <= people.length <= 60
//0 <= people[i].length <= 16
//1 <= people[i][j].length <= 16
//people[i][j] 由小写英文字母组成
//people[i] 中的所有字符串 互不相同
//people[i] 中的每个技能是 req_skills 中的技能
//题目数据保证「必要团队」一定存在
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/smallest-sufficient-team
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 转化成二进制表示，就可以很轻松地使用DP求解了，这里先不需要返回团队的组成
func smallestSufficientTeam(req_skills []string, people [][]string) int {
	m, n := len(req_skills), len(people)
	mp := make(map[string]int)
	for i, skill := range req_skills {
		mp[skill] = i
	}
	p := make([]int, n)
	for i, item := range people {
		num := 0
		for _, s := range item {
			num |= 1 << mp[s]
		}
		p[i] = num
	}
	k := 1 << m
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, k)
	}
	for i := 1; i < k; i++ {
		dp[0][i] = -1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j < k; j++ {
			// 不选
			dp[i][j] = dp[i-1][j]
			// 选
			l := j ^ (j & p[i-1])
			if dp[i-1][l] >= 0 && (dp[i][j] < 0 || dp[i-1][l]+1 < dp[i][j]) {
				dp[i][j] = dp[i-1][l] + 1
			}
		}
	}
	return dp[n][k-1]
}

// 增加路径检索，通过
func smallestSufficientTeam2(req_skills []string, people [][]string) []int {
	m, n := len(req_skills), len(people)
	mp := make(map[string]int)
	for i, skill := range req_skills {
		mp[skill] = i
	}
	p := make([]int, n)
	for i, item := range people {
		num := 0
		for _, s := range item {
			num |= 1 << mp[s]
		}
		p[i] = num
	}
	k := 1 << m
	dp := make([][]int, n+1)
	path := make([][]bool, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, k)
		path[i] = make([]bool, k)
	}
	for i := 1; i < k; i++ {
		dp[0][i] = -1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j < k; j++ {
			// 不选
			dp[i][j] = dp[i-1][j]
			// 选
			l := j ^ (j & p[i-1])
			if dp[i-1][l] >= 0 && (dp[i][j] < 0 || dp[i-1][l]+1 < dp[i][j]) {
				dp[i][j] = dp[i-1][l] + 1
				path[i][j] = true
			}
		}
	}
	// 得出选择路径
	var res []int
	i := n
	l := k - 1
	for i > 0 {
		if path[i][l] {
			res = append(res, i-1)
			l = l ^ (l & p[i-1])
		}
		i--
	}
	return res
}
