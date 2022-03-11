package com.liyongquan.tree;

//给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
//
//一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
//
//请你返回有 最高得分 节点的 数目 。
//
// 
//
//示例 1:
//
//
//
//输入：parents = [-1,2,0,2,0]
//输出：3
//解释：
//- 节点 0 的分数为：3 * 1 = 3
//- 节点 1 的分数为：4 = 4
//- 节点 2 的分数为：1 * 1 * 2 = 2
//- 节点 3 的分数为：4 = 4
//- 节点 4 的分数为：4 = 4
//最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
//示例 2：
//
//
//
//输入：parents = [-1,2,0]
//输出：2
//解释：
//- 节点 0 的分数为：2 = 2
//- 节点 1 的分数为：2 = 2
//- 节点 2 的分数为：1 * 1 = 1
//最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
// 
//
//提示：
//
//n == parents.length
//2 <= n <= 105
//parents[0] == -1
//对于 i != 0 ，有 0 <= parents[i] <= n - 1
//parents 表示一棵二叉树。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-nodes-with-the-highest-score
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.liyongquan.linklist.ListNode;

import java.util.HashMap;
import java.util.Map;

public class CountHighestScoreNodes {
    private int[] cnt;

    public int countHighestScoreNodes(int[] parents) {
        //先构造一颗二叉树
        Map<Integer, TreeNode> map = new HashMap<>();
        TreeNode root = null;
        for (int i = 0; i < parents.length; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new TreeNode(i));
            }
            TreeNode cur = map.get(i);
            if (parents[i] < 0) {
                root = cur;
            } else {
                if (!map.containsKey(parents[i])) {
                    map.put(parents[i], new TreeNode(parents[i]));
                }
                TreeNode parent = map.get(parents[i]);
                if (parent.left == null) {
                    parent.left = cur;
                } else {
                    parent.right = cur;
                }
            }
        }
        //dfs扫描，计算每个节点下的节点数量和
        cnt = new int[parents.length];
        dfs(root);
        //分数和数量
        int max = 0;
        int c = 0;
        for (int i = 0; i < parents.length; i++) {

        }
        return 0;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = dfs(root.left) + dfs(root.right) + 1;
        cnt[root.val] = sum;
        return sum;
    }

    private int cal(TreeNode root, int rm, int sum) {
        if (root == null) {
            return 1;
        }
        if (root.val == rm) {
            return cal(root.left, rm, 0) * cal(root.right, rm, 0);
        }
        sum++;
        if (root.left != null && root.left.val == root.val) {
            int l = cal(root.left, rm, 0);
            if (root.right != null) {
                l *= cnt[root.right.val] + 1;
            }
            return l;
        } else if (root.right != null && root.right.val == root.val) {
            int r = cal(root.right, rm, 0);
            if (root.left != null) {
                r *= cnt[root.left.val] + 1;
            }
            return r;
        } else {
            //TODO:
        }
        return 0;
    }
}
