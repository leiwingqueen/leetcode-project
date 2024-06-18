package array

// You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
//
//Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.
//
//Return an array of the k digits representing the answer.
//
//
//
//Example 1:
//
//Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
//Output: [9,8,6,5,3]
//Example 2:
//
//Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
//Output: [6,7,6,0,4]
//Example 3:
//
//Input: nums1 = [3,9], nums2 = [8,9], k = 3
//Output: [9,8,9]
//
//
//Constraints:
//
//m == nums1.length
//n == nums2.length
//1 <= m, n <= 500
//0 <= nums1[i], nums2[i] <= 9
//1 <= k <= m + n

// 超时
func maxNumber(nums1 []int, nums2 []int, k int) []int {
	m, n := len(nums1), len(nums2)
	compare := func(arr1, arr2 []int) int {
		for i := 0; i < len(arr1); i++ {
			if arr1[i] != arr2[i] {
				return arr1[i] - arr2[i]
			}
		}
		return 0
	}
	var dfs func(p1, p2, cnt int) []int
	dfs = func(p1, p2, cnt int) []int {
		if cnt == 0 {
			return []int{}
		}
		// 选择[p1,m-1-max(cnt-(n-p2),0)],[p2,n-1-max(cnt-(m-p1),0)]
		r1 := m - 1 - max(cnt-1-(n-p2), 0)
		r2 := n - 1 - max(cnt-1-(m-p1), 0)
		c1 := p1
		for i := p1; i <= r1; i++ {
			if nums1[i] > nums1[c1] {
				c1 = i
			}
		}
		c2 := p2
		for i := p2; i <= r2; i++ {
			if nums2[i] > nums2[c2] {
				c2 = i
			}
		}
		var res []int
		if c2 == n || c1 != m && nums1[c1] > nums2[c2] {
			res = append(res, nums1[c1])
			sub := dfs(c1+1, p2, cnt-1)
			res = append(res, sub...)
		} else if c1 == m || c2 != n && nums1[c1] < nums2[c2] {
			res = append(res, nums2[c2])
			sub := dfs(p1, c2+1, cnt-1)
			res = append(res, sub...)
		} else {
			sub1 := dfs(c1+1, p2, cnt-1)
			sub2 := dfs(p1, c2+1, cnt-1)
			if compare(sub1, sub2) >= 0 {
				res = append(res, nums1[c1])
				res = append(res, sub1...)
			} else {
				res = append(res, nums2[c2])
				res = append(res, sub2...)
			}
		}
		return res
	}
	return dfs(0, 0, k)
}
