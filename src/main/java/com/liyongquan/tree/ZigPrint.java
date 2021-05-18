package com.liyongquan.tree;

import java.util.*;

public class ZigPrint {
    List<List<Integer>> print(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean inverse = false;
        List<List<Integer>> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            if (inverse) {
                Stack<Integer> stack = new Stack<>();
                for (Integer num : list) {
                    stack.add(num);
                }
                List<Integer> nlist = new ArrayList<>(list.size());
                while (!stack.isEmpty()) {
                    nlist.add(stack.pop());
                }
                list = nlist;
            }
            inverse = !inverse;
            res.add(list);
        }
        return res;
    }



}
