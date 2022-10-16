package wc315

func countSubarrays(nums []int, minK int, maxK int) int64 {
	l := 0
	r := 0
	n := len(nums)
	var res int64
	for r < n {
		for l < n && (nums[l] < minK || nums[l] > maxK) {
			l++
		}
		if l == n {
			break
		}
		min := nums[l]
		max := nums[l]
		r = l
		for r < n && nums[r] >= minK && nums[r] <= maxK {
			if nums[r] < min {
				min = nums[r]
			}
			if nums[r] > max {
				max = nums[r]
			}
			r++
		}
		if min != minK || max != maxK {
			l = r
			continue
		}
		// 找到最小的匹配段
		queue := make([][]int, 0)
		p1 := l
		var p2 int
		for p1 < r {
			for p1 < r && (nums[p1] != minK && nums[p1] != maxK) {
				p1++
			}
			if p1 == r {
				break
			}
			p2 = p1
			if nums[p1] == minK {
				for p2 < r && nums[p2] != maxK {
					p2++
				}
			} else {
				for p2 < r && nums[p2] != minK {
					p2++
				}
			}
			if p2 == r {
				break
			}
			queue = append(queue, []int{p1, p2})
			p1 = p2 + 1
		}
		//[p1,p2]就是最小的核心段
		for i, arr := range queue {
			p3 := r
			if i < len(queue)-1 {
				p3 = queue[i+1][1]
			}
			res += int64(arr[0]-l+1) * int64(p3-arr[1])
		}
		l = r
	}
	return res
}
