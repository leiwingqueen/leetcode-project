package FrontMiddleBackQueue

import (
	"fmt"
	"testing"
)

// FrontMiddleBackQueue q = new FrontMiddleBackQueue();
//q.pushFront(1);   // [1]
//q.pushBack(2);    // [1, 2]
//q.pushMiddle(3);  // [1, 3, 2]
//q.pushMiddle(4);  // [1, 4, 3, 2]
//q.popFront();     // return 1 -> [4, 3, 2]
//q.popMiddle();    // return 3 -> [4, 2]
//q.popMiddle();    // return 4 -> [2]
//q.popBack();      // return 2 -> []
//q.popFront();     // return -1 -> [] (The queue is empty)

func Test_t1(t *testing.T) {
	queue := Constructor()
	queue.PushFront(1)
	queue.PushBack(2)
	queue.PushMiddle(3)
	queue.PushMiddle(4)
	p := queue.PopFront()
	fmt.Println(p)
	p = queue.PopMiddle()
	fmt.Println(p)
	p = queue.PopMiddle()
	fmt.Println(p)
}
