package graph

/**
 * Definition for a Node.
 **/
type Node struct {
	Val       int
	Neighbors []*Node
}

// bfs
func cloneGraph(node *Node) *Node {
	nodes := make([]*Node, 101)
	var queue []*Node
	queue = append(queue, node)
	nodes[node.Val] = &Node{Val: node.Val}
	for len(queue) > 0 {
		size := len(queue)
		for _, cur := range queue[:size] {
			for _, next := range cur.Neighbors {
				if nodes[next.Val] == nil {
					clone := &Node{Val: next.Val}
					nodes[next.Val] = clone
					nodes[cur.Val].Neighbors = append(nodes[cur.Val].Neighbors, clone)
					queue = append(queue, next)
				}
			}
		}
		queue = queue[size:]
	}
	return nodes[node.Val]
}
