package DinnerPlates

import "sort"

// 我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量 capacity 都相同。
//
//实现一个叫「餐盘」的类 DinnerPlates：
//
//DinnerPlates(int capacity) - 给出栈的最大容量 capacity。
//void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。
//int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。
//int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -1。
//
//
//示例：
//
//输入：
//["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
//[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
//输出：
//[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
//
//解释：
//DinnerPlates D = DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
//D.push(1);
//D.push(2);
//D.push(3);
//D.push(4);
//D.push(5);         // 栈的现状为：    2  4
//                                    1  3  5
//                                    ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 2。栈的现状为：      4
//                                          1  3  5
//                                          ﹈ ﹈ ﹈
//D.push(20);        // 栈的现状为：  20  4
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.push(21);        // 栈的现状为：  20  4 21
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
//                                            1  3  5
//                                            ﹈ ﹈ ﹈
//D.popAtStack(2);   // 返回 21。栈的现状为：       4
//                                            1  3  5
//                                            ﹈ ﹈ ﹈
//D.pop()            // 返回 5。栈的现状为：        4
//                                            1  3
//                                            ﹈ ﹈
//D.pop()            // 返回 4。栈的现状为：    1  3
//                                           ﹈ ﹈
//D.pop()            // 返回 3。栈的现状为：    1
//                                           ﹈
//D.pop()            // 返回 1。现在没有栈。
//D.pop()            // 返回 -1。仍然没有栈。
//
//
//提示：
//
//1 <= capacity <= 20000
//1 <= val <= 20000
//0 <= index <= 100000
//最多会对 push，pop，和 popAtStack 进行 200000 次调用。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/dinner-plate-stacks
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

type DinnerPlates struct {
	capacity int
	stacks   [][]int
	// firstNotFull int
	// 单调栈
	s1 []int
}

func Constructor(capacity int) DinnerPlates {
	return DinnerPlates{capacity: capacity}
}

func (this *DinnerPlates) Push(val int) {
	if len(this.stacks) == 0 {
		this.stacks = append(this.stacks, make([]int, 0))
		this.s1 = append(this.s1, 0)
	}
	choose := -1
	for len(this.s1) > 0 {
		if len(this.stacks[this.s1[0]]) < this.capacity {
			choose = this.s1[0]
			break
		}
		// pop the top of stack.
		this.s1 = this.s1[1:]
	}
	if choose < 0 {
		this.stacks = append(this.stacks, make([]int, 0))
		this.s1 = append(this.s1, len(this.stacks)-1)
		choose = len(this.stacks) - 1
	}
	this.stacks[choose] = append(this.stacks[choose], val)
}

func (this *DinnerPlates) Pop() int {
	choose := -1
	for len(this.s1) > 0 {
		last := this.s1[len(this.s1)-1]
		if len(this.stacks[last]) > 0 {
			choose = last
			break
		}
		// pop the bottom of stack.
		this.s1 = this.s1[0 : len(this.s1)-1]
	}
	if choose < 0 {
		return -1
	}
	st := this.stacks[choose]
	r := st[len(st)-1]
	this.stacks[choose] = st[0 : len(st)-1]
	return r
}

func (this *DinnerPlates) PopAtStack(index int) int {
	if index < 0 || index >= len(this.stacks) {
		return -1
	}
	st := this.stacks[index]
	if len(st) <= 0 {
		return -1
	}
	r := st[len(st)-1]
	this.stacks[index] = this.stacks[index][0 : len(st)-1]
	// update s1,use binary search to find the index
	searchIdx := sort.Search(len(this.s1), func(i int) bool {
		return this.s1[i] >= index
	})
	if searchIdx < len(this.s1) {
		s3 := this.s1[:searchIdx]
		idx := searchIdx
		if this.s1[searchIdx] == index {
			idx++
		} else {
			s3 = append(s3, index)
		}
		if idx < len(this.s1) {
			s2 := this.s1[idx:]
			for len(s2) > 0 {
				if len(this.stacks[s2[0]]) < len(this.stacks[index]) {
					break
				}
				s2 = s2[1:]
			}
			this.s1 = append(s3, s2...)
		}
	}
	return r
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * obj.Push(val);
 * param_2 := obj.Pop();
 * param_3 := obj.PopAtStack(index);
 */
