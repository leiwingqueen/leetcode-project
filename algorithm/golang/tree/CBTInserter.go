package tree

//完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
//
//设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
//
//实现 CBTInserter 类:
//
//CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
//CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
//CBTInserter.get_root() 将返回树的头节点。
//
//
//示例 1：
//
//
//
//输入
//["CBTInserter", "insert", "insert", "get_root"]
//[[[1, 2]], [3], [4], []]
//输出
//[null, 1, 2, [1, 2, 3, 4]]
//
//解释
//CBTInserter cBTInserter = new CBTInserter([1, 2]);
//cBTInserter.insert(3);  // 返回 1
//cBTInserter.insert(4);  // 返回 2
//cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
//
//
//提示：
//
//树中节点数量范围为 [1, 1000]
//0 <= Node.val <= 5000
//root 是完全二叉树
//0 <= val <= 5000
//每个测试用例最多调用 insert 和 get_root 操作 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/complete-binary-tree-inserter
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

type CBTInserter struct {
	//可以方便地查找父节点
	arr []*TreeNode
}

func Constructor(root *TreeNode) CBTInserter {
	arr := make([]*TreeNode, 0)
	if root != nil {
		queue := make([]*TreeNode, 0)
		queue = append(queue, root)
		for len(queue) > 0 {
			size := len(queue)
			for i := 0; i < size; i++ {
				pop := queue[0]
				queue = queue[1:]
				arr = append(arr, pop)
				if pop.Left != nil {
					queue = append(queue, pop.Left)
				}
				if pop.Right != nil {
					queue = append(queue, pop.Right)
				}
			}
		}
	}
	return CBTInserter{arr: arr}
}

func (this *CBTInserter) Insert(val int) int {
	node := &TreeNode{val, nil, nil}
	this.arr = append(this.arr, node)
	idx := len(this.arr) - 1
	if idx == 0 {
		return -1
	}
	parent := this.arr[(idx-1)/2]
	if parent.Left == nil {
		parent.Left = node
	} else {
		parent.Right = node
	}
	return parent.Val
}

func (this *CBTInserter) Get_root() *TreeNode {
	if len(this.arr) == 0 {
		return nil
	}
	return this.arr[0]
}
