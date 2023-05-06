package stack

//给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以
//croakOfFrogs 中会混合多个 “croak” 。
//
// 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
//
// 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会
//发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：croakOfFrogs = "croakcroak"
//输出：1
//解释：一只青蛙 “呱呱” 两次
//
//
// 示例 2：
//
//
//输入：croakOfFrogs = "crcoakroak"
//输出：2
//解释：最少需要两只青蛙，“呱呱” 声用黑体标注
//第一只青蛙 "crcoakroak"
//第二只青蛙 "crcoakroak"
//
//
// 示例 3：
//
//
//输入：croakOfFrogs = "croakcrook"
//输出：-1
//解释：给出的字符串不是 "croak" 的有效组合。
//
//
//
//
// 提示：
//
//
// 1 <= croakOfFrogs.length <= 10⁵
// 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
//
//
// Related Topics 字符串 计数 👍 154 👎 0

// 错误，题目要求的是最小的青蛙数量
func minNumberOfFrogs(croakOfFrogs string) int {
	n := len(croakOfFrogs)
	arr := make([]int, n)
	for i := 0; i < n; i++ {
		ch := croakOfFrogs[i]
		switch ch {
		case 'c':
			arr[i] = 0
		case 'r':
			arr[i] = 1
		case 'o':
			arr[i] = 2
		case 'a':
			arr[i] = 3
		case 'k':
			arr[i] = 4
		}
	}
	var stack []int
	var tmp []int
	res := 0
	for i := 0; i < n; i++ {
		if arr[i] == 4 {
			cur := 4
			for cur > 0 && len(stack) > 0 {
				if stack[len(stack)-1] == cur-1 {
					stack = stack[:len(stack)-1]
					cur--
				} else {
					tmp = append(tmp, stack[len(stack)-1])
					stack = stack[:len(stack)-1]
				}
			}
			if cur > 0 {
				return -1
			}
			res++
			for len(tmp) > 0 {
				stack = append(stack, tmp[len(tmp)-1])
				tmp = tmp[:len(tmp)-1]
			}
		} else {
			stack = append(stack, arr[i])
		}
	}
	if len(stack) > 0 {
		return -1
	} else {
		return res
	}
}

func minNumberOfFrogs2(croakOfFrogs string) int {
	mp := map[byte]int{
		'c': 0,
		'r': 1,
		'o': 2,
		'a': 3,
		'k': 4,
	}
	n := len(croakOfFrogs)
	cnt := make([]int, 5)
	for i := 0; i < n; i++ {
		idx := mp[croakOfFrogs[i]]
		if idx == 0 {
			if cnt[4] > 0 {
				cnt[4]--
			}
			cnt[0]++
		} else {
			if cnt[idx-1] == 0 {
				return -1
			}
			cnt[idx-1]--
			cnt[idx]++
		}
	}
	for i := 0; i < 4; i++ {
		if cnt[i] != 0 {
			return -1
		}
	}
	return cnt[4]
}
