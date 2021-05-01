package com.liyongquan.design;

import java.util.*;

/**
 * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
 *
 * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
 *
 * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * 输出：11
 * 解释：
 * 员工 1 自身的重要度是 5 ，他有两个直系下属 2 和 3 ，而且 2 和 3 的重要度均为 3 。因此员工 1 的总重要度是 5 + 3 + 3 = 11 。
 *  
 *
 * 提示：
 *
 * 一个员工最多有一个 直系 领导，但是可以有多个 直系 下属
 * 员工数量不超过 2000 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/employee-importance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
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

    /**
     * 其实可以写得更简单一些
     *
     * @param employees
     * @param id
     * @return
     */
    public int getImportance2(List<Employee> employees, int id) {
        int len = employees.size();
        Map<Integer, Employee> map = new HashMap<>(len);
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        int sum = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            Employee employee = map.get(poll);
            sum += employee.importance;
            for (Integer sub : employee.subordinates) {
                queue.add(sub);
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
