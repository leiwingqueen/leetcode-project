package graph

import (
	"fmt"
	"testing"
)

func Test_t1(t *testing.T) {
	node1 := &Node{Val: 1}
	node2 := &Node{Val: 2}
	node3 := &Node{Val: 3}
	node1.Neighbors = append(node1.Neighbors, node2)
	node1.Neighbors = append(node1.Neighbors, node3)
	graph := cloneGraph(node1)
	fmt.Println(fmt.Sprintf("%v", graph))
}
