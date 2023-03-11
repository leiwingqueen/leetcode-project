package hash

// 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
//
//返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
//
//示例 1:
//
//输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
//
//输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
//示例 2:
//
//输入: ["A","A"]
//
//输出: []
//提示：
//
//array.length <= 100000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-longest-subarray-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

func findLongestSubarray(array []string) []string {
	n := len(array)
	mp := make(map[int]int)
	mp[0] = -1
	sum := 0
	res := -1
	l := 0
	for i := 0; i < n; i++ {
		if array[i][0] >= '0' && array[i][0] <= '9' {
			sum--
		} else {
			sum++
		}
		if idx, exist := mp[-sum]; exist {
			if res < 0 || i-idx < res {
				res = i - idx
				l = i
			}
		}
		if _, exist := mp[sum]; !exist {
			mp[sum] = i
		}
	}
	if res > 0 {
		r := make([]string, res)
		for i := 0; i < res; i++ {
			r[i+l] = array[i]
		}
		return r
	} else {
		return []string{}
	}
}
