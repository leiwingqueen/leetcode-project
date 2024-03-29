package bwc110

// bfs，贪心，但是有问题
func minimumSeconds(nums []int) int {
	n := len(nums)
	mp := make(map[int]int)
	// 出现次数最多的数字
	mx := 0
	for _, num := range nums {
		mp[num]++
		if mp[num] > mp[mx] {
			mx = num
		}
	}
	queue := make([]int, 0)
	for i, num := range nums {
		if num == mx {
			queue = append(queue, i)
		}
	}
	depth := 0
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			idx := queue[i]
			if nums[(idx-1+n)%n] != mx {
				nums[(idx-1+n)%n] = mx
				queue = append(queue, (idx-1+n)%n)
			}
			if nums[(idx+1)%n] != mx {
				nums[(idx+1)%n] = mx
				queue = append(queue, (idx+1)%n)
			}
		}
		queue = queue[size:]
		depth++
	}
	return depth - 1
}

func minimumSeconds2(nums []int) int {
	n := len(nums)
	mp := make(map[int][]int)
	for i, num := range nums {
		mp[num] = append(mp[num], i)
	}
	res := n
	for _, arr := range mp {
		dis := 0
		if len(arr) == 1 {
			dis = n
		} else {
			for i, idx := range arr {
				next := (i + 1) % len(arr)
				d := 0
				if arr[next] > idx {
					d = arr[next] - idx
				} else {
					d = n - idx + arr[next]
				}
				if d > dis {
					dis = d
				}
			}
		}
		if dis < res {
			res = dis
		}
	}
	return res / 2
}
