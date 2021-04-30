package com.liyongquan.design;

import java.util.*;

public class GetImportance {
    public int getImportance(List<Employee> employees, int id) {
        //构造树
        Map<Integer, TreeNode> map = new HashMap<>();
        for (Employee employee : employees) {
            TreeNode node = map.getOrDefault(employee.id, new TreeNode(employee.importance));
            node.value = employee.importance;
            map.put(employee.id, node);
            for (Integer sub : employee.subordinates) {
                TreeNode subNode = map.getOrDefault(sub, new TreeNode(0));
                node.next.add(subNode);
                map.put(sub, subNode);
            }
        }
        //bfs遍历
        TreeNode root = map.get(id);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            sum += poll.value;
            for (TreeNode treeNode : poll.next) {
                queue.add(treeNode);
            }
        }
        return sum;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}

class TreeNode {
    int value;
    List<TreeNode> next;

    public TreeNode(int value) {
        this(value, new ArrayList<>());
    }

    public TreeNode(int value, List<TreeNode> next) {
        this.value = value;
        this.next = next;
    }
}
