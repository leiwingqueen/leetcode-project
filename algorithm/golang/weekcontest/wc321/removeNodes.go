package wc321

// 给你一个链表的头节点 head 。
//
//移除每个右侧有一个更大数值的节点。
//
//返回修改后链表的头节点 head 。
//
//
//
//示例 1：
//
//
//
//输入：head = [5,2,13,3,8]
//输出：[13,8]
//解释：需要移除的节点是 5 ，2 和 3 。
//- 节点 13 在节点 5 右侧。
//- 节点 13 在节点 2 右侧。
//- 节点 8 在节点 3 右侧。
//示例 2：
//
//输入：head = [1,1,1,1]
//输出：[1,1,1,1]
//解释：每个节点的值都是 1 ，所以没有需要移除的节点。
//
//
//提示：
//
//给定列表中的节点数目在范围 [1, 105] 内
//1 <= Node.val <= 105

type ListNode struct {
	Val  int
	Next *ListNode
}

func removeNodes(head *ListNode) *ListNode {
	var arr []int
	node := head
	for node != nil {
		arr = append(arr, node.Val)
		node = node.Next
	}
	n := len(arr)
	delArr := make([]bool, n)
	mx := 0
	for i := n - 1; i >= 0; i-- {
		if arr[i] < mx {
			delArr[i] = true
			// fmt.Println(i)
		} else {
			mx = arr[i]
		}
	}
	dummy := &ListNode{0, head}
	pre := dummy
	cur := head
	idx := 0
	for cur != nil {
		if delArr[idx] {
			pre.Next = cur.Next
		} else {
			pre = cur
		}
		cur = cur.Next
		idx++
	}
	return dummy.Next
}

func removeNodes2(head *ListNode) *ListNode {
	var dfs func(node *ListNode) (int, *ListNode)
	dfs = func(node *ListNode) (int, *ListNode) {
		if node == nil {
			return 0, nil
		}
		mx, h := dfs(node.Next)
		if node.Val >= mx {
			node.Next = h
			mx = node.Val
		} else {
			// 需要删除节点
			node.Next = nil
			node = h
		}
		return mx, node
	}
	_, node := dfs(head)
	return node
}
