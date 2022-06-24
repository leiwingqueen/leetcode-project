package com.liyongquan.weeklycontest.lccpu2022;

import com.liyongquan.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetNumber {
    /**
     * 超时
     *
     * @param root
     * @param ops
     * @return
     */
    public int getNumber(TreeNode root, int[][] ops) {
        Set<Integer> set = new HashSet<>();
        for (int[] op : ops) {
            dfs(root, set, op);
        }
        return set.size();
    }

    private void dfs(TreeNode root, Set<Integer> mp, int[] op) {
        if (root == null) {
            return;
        }
        int type = op[0];
        int from = op[1];
        int to = op[2];
        if (root.val >= from && root.val <= to) {
            if (type == 0) {
                mp.remove(root.val);
            } else {
                mp.add(root.val);
            }
            dfs(root.left, mp, op);
            dfs(root.right, mp, op);
        } else if (root.val > from) {
            dfs(root.left, mp, op);
        } else {
            dfs(root.right, mp, op);
        }
    }


    private List<Integer> list = new ArrayList<>();

    public int getNumbe2(TreeNode root, int[][] ops) {
        Set<Integer> set = new HashSet<>();
        scan(root);
        for (int[] op : ops) {
            int type = op[0];
            int from = op[1];
            int to = op[2];
            int fromIdx = find(from);
            int toIdx = find(to);
            for (int i = fromIdx; i <= toIdx; i++) {
                if (type == 0) {
                    set.remove(list.get(i));
                } else {
                    set.add(list.get(i));
                }
            }
        }
        return set.size();
    }

    private void scan(TreeNode root) {
        if (root == null) {
            return;
        }
        scan(root.left);
        list.add(root.val);
        scan(root.right);
    }

    private int find(int from) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) == from) {
                return mid;
            } else if (list.get(mid) > from) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 还是暴力，但是思路反向
     * <p>
     * 看数据量应该是过不了的，奈何最后还是过了。。
     *
     * @param root
     * @param ops
     * @return
     */
    public int getNumber3(TreeNode root, int[][] ops) {
        return dfs(root, ops);
    }

    private int dfs(TreeNode root, int[][] ops) {
        if (root == null) {
            return 0;
        }
        int n = ops.length;
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            int[] op = ops[i];
            int type = op[0];
            int from = op[1];
            int to = op[2];
            if (root.val >= from && root.val <= to) {
                if (type == 1) {
                    cnt++;
                }
                break;
            }
        }
        cnt += dfs(root.left, ops);
        cnt += dfs(root.right, ops);
        return cnt;
    }
}
