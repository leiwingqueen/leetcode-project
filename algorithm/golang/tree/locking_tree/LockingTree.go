package locking_tree

// 给你一棵 n 个节点的树，编号从 0 到 n - 1 ，以父节点数组 parent 的形式给出，其中 parent[i] 是第 i 个节点的父节点。树的根节点为 0 号节点，所以 parent[0] = -1 ，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。
//
//数据结构需要支持如下函数：
//
//Lock：指定用户给指定节点 上锁 ，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。
//Unlock：指定用户给指定节点 解锁 ，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。
//Upgrade：指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁 。只有如下 3 个条件 全部 满足时才能执行升级操作：
//指定节点当前状态为未上锁。
//指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
//指定节点没有任何上锁的祖先节点。
//请你实现 LockingTree 类：
//
//LockingTree(int[] parent) 用父节点数组初始化数据结构。
//lock(int num, int user) 如果 id 为 user 的用户可以给节点 num 上锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 id 为 user 的用户 上锁 。
//unlock(int num, int user) 如果 id 为 user 的用户可以给节点 num 解锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 变为 未上锁 状态。
//upgrade(int num, int user) 如果 id 为 user 的用户可以给节点 num 升级，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 升级 。

type LockTreeNode struct {
	Parent   *LockTreeNode
	Child    []*LockTreeNode
	LockUser int
}

type LockingTree struct {
	nodes []*LockTreeNode
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	nodes := make([]*LockTreeNode, n)
	for i := 0; i < n; i++ {
		nodes[i] = &LockTreeNode{Parent: nil}
	}
	for i, p := range parent {
		if p >= 0 {
			nodes[i].Parent = nodes[p]
			nodes[p].Child = append(nodes[p].Child, nodes[i])
		}
	}
	return LockingTree{nodes}
}

func (this *LockingTree) Lock(num int, user int) bool {
	node := this.nodes[num]
	if node.LockUser > 0 {
		return false
	} else {
		node.LockUser = user
		return true
	}
}

func (this *LockingTree) Unlock(num int, user int) bool {
	node := this.nodes[num]
	if node.LockUser == 0 {
		return false
	} else {
		if node.LockUser == user {
			node.LockUser = 0
			return true
		} else {
			return false
		}
	}
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	node := this.nodes[num]
	if node.LockUser > 0 {
		return false
	}
	var checkChild func(node *LockTreeNode) bool
	checkChild = func(node *LockTreeNode) bool {
		for _, child := range node.Child {
			if child.LockUser > 0 || checkChild(child) {
				return true
			}
		}
		return false
	}
	var checkParent func(node *LockTreeNode) bool
	checkParent = func(node *LockTreeNode) bool {
		for node.Parent != nil {
			if node.Parent.LockUser > 0 {
				return false
			}
			node = node.Parent
		}
		return true
	}
	var releaseChildLock func(node *LockTreeNode)
	releaseChildLock = func(node *LockTreeNode) {
		for _, child := range node.Child {
			if child.LockUser > 0 {
				child.LockUser = 0
			}
			releaseChildLock(child)
		}
	}
	if checkChild(node) && checkParent(node) {
		node.LockUser = user
		releaseChildLock(node)
		return true
	} else {
		return false
	}
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * obj := Constructor(parent);
 * param_1 := obj.Lock(num,user);
 * param_2 := obj.Unlock(num,user);
 * param_3 := obj.Upgrade(num,user);
 */
