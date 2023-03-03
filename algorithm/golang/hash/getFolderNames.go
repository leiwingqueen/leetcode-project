package hash

import "fmt"

// 给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
//
//由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
//
//返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
//
//
//
//示例 1：
//
//输入：names = ["pes","fifa","gta","pes(2019)"]
//输出：["pes","fifa","gta","pes(2019)"]
//解释：文件系统将会这样创建文件名：
//"pes" --> 之前未分配，仍为 "pes"
//"fifa" --> 之前未分配，仍为 "fifa"
//"gta" --> 之前未分配，仍为 "gta"
//"pes(2019)" --> 之前未分配，仍为 "pes(2019)"
//示例 2：
//
//输入：names = ["gta","gta(1)","gta","avalon"]
//输出：["gta","gta(1)","gta(2)","avalon"]
//解释：文件系统将会这样创建文件名：
//"gta" --> 之前未分配，仍为 "gta"
//"gta(1)" --> 之前未分配，仍为 "gta(1)"
//"gta" --> 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。
//"avalon" --> 之前未分配，仍为 "avalon"
//示例 3：
//
//输入：names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
//输出：["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
//解释：当创建最后一个文件夹时，最小的正有效 k 为 4 ，文件名变为 "onepiece(4)"。
//示例 4：
//
//输入：names = ["wano","wano","wano","wano"]
//输出：["wano","wano(1)","wano(2)","wano(3)"]
//解释：每次创建文件夹 "wano" 时，只需增加后缀中 k 的值即可。
//示例 5：
//
//输入：names = ["kaido","kaido(1)","kaido","kaido(1)"]
//输出：["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
//解释：注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
//
//
//提示：
//
//1 <= names.length <= 5 * 10^4
//1 <= names[i].length <= 20
//names[i] 由小写英文字母、数字和/或圆括号组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/making-file-names-unique
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 超时
func getFolderNames(names []string) []string {
	mp := make(map[string]struct{})
	n := len(names)
	res := make([]string, n)
	for i := 0; i < n; i++ {
		name := names[i]
		if _, exist := mp[name]; !exist {
			mp[name] = struct{}{}
			res[i] = name
		} else {
			// 这里会超时
			k := 1
			for {
				s := fmt.Sprintf("%s(%d)", name, k)
				if _, ex := mp[s]; !ex {
					mp[s] = struct{}{}
					res[i] = s
					break
				}
				k++
			}
		}
	}
	return res
}

// 稍微优化一下
func getFolderNames2(names []string) []string {
	mp1 := make(map[string]struct{})
	mp2 := make(map[string]int)
	n := len(names)
	res := make([]string, n)
	for i := 0; i < n; i++ {
		name := names[i]
		if _, exist := mp1[name]; !exist {
			mp1[name] = struct{}{}
			res[i] = name
		} else {
			k := mp2[name] + 1
			for {
				s := fmt.Sprintf("%s(%d)", name, k)
				if _, ex := mp1[s]; !ex {
					mp1[s] = struct{}{}
					mp2[name] = k
					res[i] = s
					break
				}
				k++
			}
		}
	}
	return res
}
