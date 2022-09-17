package segment_tree

import (
	"math"
)

// SegmentTree https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
type SegmentTree struct {
	st []int
	n  int
}

// Construct
/* Constructor to construct segment tree from given array. This
   constructor  allocates memory for segment tree and calls
   constructSTUtil() to  fill the allocated memory *
*/
func Construct(arr []int) *SegmentTree {
	// Allocate memory for segment tree
	//Height of segment tree
	n := len(arr)
	x := math.Ceil(math.Log(float64(n)) / math.Log(2))
	//Maximum size of segment tree
	max_size := 2 * int(math.Pow(2, x)-1)

	st := make([]int, max_size) // Memory allocation
	var constructSTUtil func(arr []int, ss int, se int, si int) int
	constructSTUtil = func(arr []int, ss int, se int, si int) int {
		if ss == se {
			st[si] = arr[ss]
			return arr[ss]
		}
		// If there are more than one elements, then recur for left and
		// right subtrees and store the sum of values in this node
		mid := ss + (se-ss)/2
		st[si] = constructSTUtil(arr, ss, mid, si*2+1) + constructSTUtil(arr, mid+1, se, si*2+2)
		return st[si]
	}
	constructSTUtil(arr, 0, n-1, 0)
	return &SegmentTree{st: st, n: n}
}

func (tree *SegmentTree) getSum(qs int, qe int) int {
	// invalid input
	if qs < 0 || qe > tree.n-1 || qs > qe {
		return -1
	}
	/*  A recursive function to get the sum of values in given range
	      of the array.  The following are parameters for this function.

	    st    --> Pointer to segment tree
	    si    --> Index of current node in the segment tree. Initially
	              0 is passed as root is always at index 0
	    ss & se  --> Starting and ending indexes of the segment represented
	                  by current node, i.e., st[si]
	    qs & qe  --> Starting and ending indexes of query range */
	var getSumUtil func(ss int, se int, qs int, qe int, si int) int
	getSumUtil = func(ss int, se int, qs int, qe int, si int) int {
		if qs <= ss && qe >= se {
			return tree.st[si]
		}
		if se < qs || ss > qe {
			return 0
		}
		mid := ss + (se-ss)/2
		return getSumUtil(ss, mid, qs, qe, 2*si+1) + getSumUtil(mid+1, se, qs, qe, 2*si+2)
	}
	return getSumUtil(0, tree.n-1, qs, qe, 0)
}
