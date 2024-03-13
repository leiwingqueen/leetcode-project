package linkList

import (
	"fmt"
	"testing"
)

func Test_t1(t *testing.T) {
	dummy := &ListNode{0, nil}
	pre := dummy
	for i := 0; i < 5; i++ {
		pre.Next = &ListNode{i + 1, nil}
		pre = pre.Next
	}
	head := dummy.Next
	node := reverseKGroup(head, 2)
	for ; node != nil; node = node.Next {
		fmt.Println(node.Val)
	}
}
