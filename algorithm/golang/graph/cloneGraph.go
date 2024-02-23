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
	if node == nil {
		return nil
	}
	nodes := make([]*Node, 101)
	var queue []*Node
	queue = append(queue, node)
	nodes[node.Val] = &Node{Val: node.Val}
	for len(queue) > 0 {
		size := len(queue)
		for _, cur := range queue[:size] {
			for _, next := range cur.Neighbors {
				if nodes[next.Val] == nil {
					nodes[next.Val] = &Node{Val: next.Val}
					queue = append(queue, next)
				}
				clone := nodes[next.Val]
				parent := nodes[cur.Val]
				nodes[next.Val] = clone
				parent.Neighbors = append(parent.Neighbors, clone)
			}
		}
		queue = queue[size:]
	}
	return nodes[node.Val]
}
