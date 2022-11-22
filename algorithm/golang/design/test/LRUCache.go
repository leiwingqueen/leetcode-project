package test

// 面试前默写一遍
type LRUNode struct {
	key   int
	value int
	next  *LRUNode
	pre   *LRUNode
}

type LRUCache struct {
	mp       map[int]*LRUNode
	head     *LRUNode
	tail     *LRUNode
	size     int
	capacity int
}

func Constructor(capacity int) LRUCache {
	head := &LRUNode{0, 0, nil, nil}
	tail := &LRUNode{0, 0, nil, nil}
	head.next = tail
	tail.pre = head
	return LRUCache{make(map[int]*LRUNode), head, tail, 0, capacity}
}

func (this *LRUCache) Get(key int) int {
	if _, exist := this.mp[key]; !exist {
		return -1
	}
	node := this.mp[key]
	// 把node移动到队尾
	this.remove(node)
	this.append(node)
	return node.value
}

func (this *LRUCache) Put(key int, value int) {
	if _, exist := this.mp[key]; exist {
		this.mp[key].value = value
		node := this.mp[key]
		// 把node移动到队尾
		this.remove(node)
		this.append(node)
	} else {
		node := &LRUNode{key: key, value: value}
		this.mp[key] = node
		this.append(node)
		if this.size > this.capacity {
			rmNode := this.head.next
			this.remove(rmNode)
			delete(this.mp, rmNode.key)
		}
	}
}

func (this *LRUCache) append(node *LRUNode) {
	pre := this.tail.pre
	node.pre = pre
	node.next = this.tail

	pre.next = node
	this.tail.pre = node
	this.size++
}

func (this *LRUCache) remove(node *LRUNode) {
	pre := node.pre
	next := node.next
	pre.next = next
	next.pre = pre
	this.size--
}
