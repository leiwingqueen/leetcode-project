package com.liyongquan.util.tree;

import com.liyongquan.tree.TreeNode;

import java.util.*;

public class BinaryTreeUtil {
    /**
     * 用队列来构造树，队列保存上一层的所有节点
     *
     * @param arr
     * @return
     */
    public static TreeNode deserialize(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;
        while (queue.size() > 0 && idx < arr.length) {
            int size = queue.size();
            //下一层的节点是上一层的两倍
            for (int i = 0; i < size; i++) {
                TreeNode parent = queue.poll();
                if (idx < arr.length && arr[idx] != null) {
                    parent.left = new TreeNode(arr[idx]);
                    queue.offer(parent.left);
                }
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    parent.right = new TreeNode(arr[idx]);
                    queue.offer(parent.right);
                }
                idx++;
            }
        }
        return root;
    }

    public static List<Integer> serialize(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        result.add(root.val);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode parent = queue.poll();
                if (parent.left != null) {
                    queue.offer(parent.left);
                    result.add(parent.left.val);
                } else {
                    result.add(null);
                }
                if (parent.right != null) {
                    queue.offer(parent.right);
                    result.add(parent.right.val);
                } else {
                    result.add(null);
                }
            }
        }
        //去掉尾部连续的null，简化输出
        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i) == null) {
                result.remove(i);
            } else {
                break;
            }
        }
        return result;
    }

    public static void print(TreeNode root) {
        List<Integer> serialize = serialize(root);
        StringBuilder sb = new StringBuilder();
        for (Integer s : serialize) {
            sb.append((s == null ? "null" : s) + ",");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, null, 15, 14, 17, 7, null, null, null, 2, 12, null, 3, 9, null, null, null, null, 11};
        TreeNode root = BinaryTreeUtil.deserialize(arr);
        List<Integer> serialize = BinaryTreeUtil.serialize(root);
        StringBuilder sb = new StringBuilder();
        for (Integer s : serialize) {
            sb.append((s == null ? "null" : s) + ",");
        }
        System.out.println(sb.toString());
    }
}
