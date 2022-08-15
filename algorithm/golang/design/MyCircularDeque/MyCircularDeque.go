package MyCircularDeque

type MyCircularDeque struct {
	items   []int
	head    int
	tail    int
	size    int
	maxSize int
}

func Constructor(k int) MyCircularDeque {
	return MyCircularDeque{make([]int, k), 0, 0, 0, k}
}

func (this *MyCircularDeque) InsertFront(value int) bool {
	if this.size == this.maxSize {
		return false
	}
	this.head = (this.head - 1 + this.maxSize) % this.maxSize
	this.items[this.head] = value
	this.size++
	return true
}

func (this *MyCircularDeque) InsertLast(value int) bool {
	if this.size == this.maxSize {
		return false
	}
	this.items[this.tail] = value
	this.tail = (this.tail + 1) % this.maxSize
	this.size++
	return true
}

func (this *MyCircularDeque) DeleteFront() bool {
	if this.size == 0 {
		return false
	}
	this.head = (this.head + 1) % this.maxSize
	this.size--
	return true
}

func (this *MyCircularDeque) DeleteLast() bool {
	if this.size == 0 {
		return false
	}
	this.tail = (this.tail - 1 + this.maxSize) % this.maxSize
	this.size--
	return true
}

func (this *MyCircularDeque) GetFront() int {
	if this.size == 0 {
		return -1
	}
	return this.items[this.head]
}

func (this *MyCircularDeque) GetRear() int {
	if this.size == 0 {
		return -1
	}
	return this.items[(this.tail-1+this.maxSize)%this.maxSize]
}

func (this *MyCircularDeque) IsEmpty() bool {
	return this.size == 0
}

func (this *MyCircularDeque) IsFull() bool {
	return this.size == this.maxSize
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * obj := Constructor(k);
 * param_1 := obj.InsertFront(value);
 * param_2 := obj.InsertLast(value);
 * param_3 := obj.DeleteFront();
 * param_4 := obj.DeleteLast();
 * param_5 := obj.GetFront();
 * param_6 := obj.GetRear();
 * param_7 := obj.IsEmpty();
 * param_8 := obj.IsFull();
 */
