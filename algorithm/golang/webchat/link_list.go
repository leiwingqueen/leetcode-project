package webchat

type Node struct {
	Val  int
	Next *Node
}

type LinkList struct {
	head *Node
	tail *Node
	size int
}

func (list *LinkList) add(val int) {
	node := &Node{Val: val}
	if list.size == 0 {
		list.head = node
		list.tail = node
	} else {
		list.tail.Next = node
		list.head = node
	}
	list.size++
}

func (list *LinkList) del() int {
	if list.size == 0 {
		return -1
	}
	next := list.head.Next
	nodeDel := list.head
	list.head = next
	list.size--
	if list.size == 0 {
		list.tail = nil
	}
	return nodeDel.Val
}

func (list *LinkList) revert() {
	if list.size <= 1 {
		return
	}
	p1 := list.head
	p2 := p1.Next
	p1.Next = nil
	for p2 != nil {
		p3 := p2.Next
		p2.Next = p1
		p1 = p2
		p2 = p3
	}
	list.head, list.tail = list.tail, list.head
}
