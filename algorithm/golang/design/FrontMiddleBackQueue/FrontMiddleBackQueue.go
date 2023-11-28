package FrontMiddleBackQueue

// Design a queue that supports push and pop operations in the front, middle, and back.
//
//Implement the FrontMiddleBack class:
//
//FrontMiddleBack() Initializes the queue.
//void pushFront(int val) Adds val to the front of the queue.
//void pushMiddle(int val) Adds val to the middle of the queue.
//void pushBack(int val) Adds val to the back of the queue.
//int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
//int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
//int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
//Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:
//
//Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
//Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
//
//
//Example 1:
//
//Input:
//["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
//[[], [1], [2], [3], [4], [], [], [], [], []]
//Output:
//[null, null, null, null, null, 1, 3, 4, 2, -1]
//
//Explanation:
//FrontMiddleBackQueue q = new FrontMiddleBackQueue();
//q.pushFront(1);   // [1]
//q.pushBack(2);    // [1, 2]
//q.pushMiddle(3);  // [1, 3, 2]
//q.pushMiddle(4);  // [1, 4, 3, 2]
//q.popFront();     // return 1 -> [4, 3, 2]
//q.popMiddle();    // return 3 -> [4, 2]
//q.popMiddle();    // return 4 -> [2]
//q.popBack();      // return 2 -> []
//q.popFront();     // return -1 -> [] (The queue is empty)
//
//
//Constraints:
//
//1 <= val <= 109
//At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.

type Node struct {
	Val  int
	Pre  *Node
	Next *Node
}

type FrontMiddleBackQueue struct {
	Head   *Node
	Tail   *Node
	Middle *Node
	Size   int
}

func Constructor() FrontMiddleBackQueue {
	dummy1 := &Node{0, nil, nil}
	dummy2 := &Node{0, nil, nil}
	dummy1.Next = dummy2
	dummy2.Pre = dummy1
	queue := FrontMiddleBackQueue{dummy1, dummy2, nil, 0}
	return queue
}

func (this *FrontMiddleBackQueue) PushFront(val int) {
	node := &Node{val, nil, nil}
	pre := this.Head
	next := this.Head.Next
	// 插入在pre和next之间
	pre.Next = node
	node.Pre = pre
	node.Next = next
	next.Pre = node
	// 更新middle指针
	if this.Size == 0 {
		this.Middle = node
	} else if this.Size%2 == 1 {
		this.Middle = this.Middle.Pre
	}
	this.Size++
}

func (this *FrontMiddleBackQueue) PushMiddle(val int) {
	node := &Node{val, nil, nil}
	var pre, next *Node
	if this.Size > 0 {
		if this.Size%2 == 0 {
			pre = this.Middle
			next = this.Middle.Next
		} else {
			pre = this.Middle.Pre
			next = this.Middle
		}
	} else {
		pre = this.Head
		next = this.Tail
	}
	// 插入在pre和next之间
	pre.Next = node
	node.Pre = pre
	node.Next = next
	next.Pre = node
	// 更新middle指针
	this.Middle = node
	this.Size++
}

func (this *FrontMiddleBackQueue) PushBack(val int) {
	node := &Node{val, nil, nil}
	pre := this.Tail.Pre
	next := this.Tail
	// 插入在pre和next之间
	pre.Next = node
	node.Pre = pre
	node.Next = next
	next.Pre = node
	// 更新middle指针
	if this.Size == 0 {
		this.Middle = node
	} else if this.Size%2 == 0 {
		this.Middle = this.Middle.Next
	}
	this.Size++
}

func (this *FrontMiddleBackQueue) PopFront() int {
	if this.Size == 0 {
		return -1
	}
	if this.Size == 1 {
		this.Middle = nil
	} else if this.Size%2 == 0 {
		this.Middle = this.Middle.Next
	}
	return this.remove(this.Head.Next)
}

func (this *FrontMiddleBackQueue) PopMiddle() int {
	if this.Size == 0 {
		return -1
	}
	node := this.Middle
	if this.Size == 1 {
		this.Middle = nil
	} else {
		if this.Size%2 == 1 {
			this.Middle = this.Middle.Pre
		} else {
			this.Middle = this.Middle.Next
		}
	}
	return this.remove(node)
}

func (this *FrontMiddleBackQueue) PopBack() int {
	if this.Size == 0 {
		return -1
	}
	if this.Size == 1 {
		this.Middle = nil
	} else if this.Size%2 == 1 {
		this.Middle = this.Middle.Pre
	}
	return this.remove(this.Tail.Pre)
}

func (this *FrontMiddleBackQueue) remove(node *Node) int {
	pre := node.Pre
	next := node.Next
	pre.Next = next
	next.Pre = pre

	node.Pre = nil
	node.Next = nil
	this.Size--
	return node.Val
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.PushFront(val);
 * obj.PushMiddle(val);
 * obj.PushBack(val);
 * param_4 := obj.PopFront();
 * param_5 := obj.PopMiddle();
 * param_6 := obj.PopBack();
 */
