package linkList

import (
	"fmt"
	"testing"
)

func Test_removeZeroSumSublists(t *testing.T) {
	arr := []int{1, 2, -3, 3, 1}
	dummy := &ListNode{Val: 0}
	cur := dummy
	for _, v := range arr {
		cur.Next = &ListNode{Val: v}
		cur = cur.Next
	}
	head := dummy.Next
	sublists := removeZeroSumSublists(head)
	for sublists != nil {
		fmt.Println(sublists.Val)
		sublists = sublists.Next
	}
}
