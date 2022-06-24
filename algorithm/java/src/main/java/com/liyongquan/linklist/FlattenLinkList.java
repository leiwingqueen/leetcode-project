package com.liyongquan.linklist;

/**
 * @author liyongquan
 * @date 2021/9/25
 */
public class FlattenLinkList {
    public Node flatten(Node head) {
        return dfs(head)[0];
    }

    private Node[] dfs(Node head) {
        if (head == null) {
            return new Node[]{null, null};
        }
        if (head.child == null && head.next == null) {
            return new Node[]{head, head};
        }
        if (head.child == null) {
            //System.out.println(head.val);
            Node[] r = dfs(head.next);
            head.next = r[0];
            r[0].prev = head;
            return new Node[]{head, r[1]};
        } else {
            Node[] child = dfs(head.child);
            Node nextNode = head.next;
            head.next = child[0];
            child[0].prev = head;
            head.child = null;
            Node last = child[1];
            if (nextNode != null) {
                Node[] next = dfs(nextNode);
                child[1].next = next[0];
                next[0].prev = child[1];
                last = next[1];
            }
            return new Node[]{head, last};
        }
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
