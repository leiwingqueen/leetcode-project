package math

//给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
//
//输入为三个整数：day、month 和 year，分别表示日、月、年。
//
//您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
//
// 
//
//示例 1：
//
//输入：day = 31, month = 8, year = 2019
//输出："Saturday"
//示例 2：
//
//输入：day = 18, month = 7, year = 1999
//输出："Sunday"
//示例 3：
//
//输入：day = 15, month = 8, year = 1993
//输出："Sunday"
// 
//
//提示：
//
//给出的日期一定是在 1971 到 2100 年之间的有效日期。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/day-of-the-week
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func dayOfTheWeek(day int, month int, year int) string {
	y := 1971
	m := 1
	d := 1
	//计算距离1971-1-1的天数,那天是周五
	cnt := 0
	//年计算
	for i := y; i < year; i++ {
		//闰年判断
		if i%400 == 0 || (i%100 != 0 && i%4 == 0) {
			cnt += 366
		} else {
			cnt += 365
		}
	}
	//月份
	monthDay := []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	for i := m; i < month; i++ {
		day += monthDay[i-1]
		//二月闰年
		if i == 2 && (year%400 == 0 || (year%100 != 0 && year%4 == 0)) {
			day++
		}
	}
	//天数
	cnt += day - d
	eng := []string{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}
	return eng[(cnt+5)%7]
}
