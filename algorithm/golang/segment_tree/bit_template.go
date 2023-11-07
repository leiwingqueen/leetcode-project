package segment_tree

type BitTemplate struct {
	tree []int
}

func constructBitTemplate(size int) *BitTemplate {
	// 下标为0的点不存储任何信息，这是作为结束判断的特殊条件
	tree := make([]int, size+1)
	return &BitTemplate{tree}
}

// 查询下标为[l,r]之间的数字总和
func (bit *BitTemplate) queryRange(l, r int) int {
	return bit.query(r) - bit.query(l-1)
}

// 查询[0,n]的总和
func (bit *BitTemplate) query(n int) int {
	res := 0
	for i := n + 1; i > 0; i -= i & -i {
		res += bit.tree[i]
	}
	return res
}

func (bit *BitTemplate) update(idx, delta int) {
	for i := idx + 1; i < len(bit.tree); i += i & -i {
		bit.tree[i] += delta
	}
}
