package com.liyongquan.tree;

import com.liyongquan.util.tree.BinaryTreeUtil;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * <p>
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * <p>
 * 如果有多种构造方法，请你返回任意一种。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *  
 * <p>
 * 提示：
 * <p>
 * 树节点的数目在 1 到 10^4 之间。
 * 树节点的值互不相同，且在 1 到 10^5 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balance-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalanceBST {
    /**
     * 不通过，这种旋转的方法真的不是那么简单
     *
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        return balance(root).getKey();
    }

    private Pair<TreeNode, Integer> balance(TreeNode root) {
        if (root == null) {
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> l = balance(root.left);
        Pair<TreeNode, Integer> r = balance(root.right);
        //不需要旋转
        if (Math.abs(l.getValue() - r.getValue()) <= 1) {
            return new Pair<>(root, Math.max(l.getValue(), r.getValue()) + 1);
        }
        //左旋
        TreeNode nr;
        if (l.getValue() < r.getValue()) {
            root.right = null;
            root.left = l.getKey();
            nr = r.getKey();
            if (nr.left == null) {
                nr.left = root;
            } else {
                //把根节点放在左下角
                TreeNode tmp = nr;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                tmp.left = root;
            }
        } else {
            //右旋
            root.left = null;
            root.right = r.getKey();
            nr = l.getKey();
            if (nr.right == null) {
                nr.right = root;
            } else {
                //把根节点放在右下角
                TreeNode tmp = nr;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root;
            }
        }
        System.out.println(String.format("旋转前的根节点:%s,旋转后的根节点:%s", root.val, nr.val));
        BinaryTreeUtil.print(nr);
        return new Pair<>(nr, Math.max(l.getValue(), r.getValue()));
    }

    /**
     * 先通过中序遍历转成有序的数组。
     * 选择(l+r)/2的节点作为根节点。然后递归地重新构造左右子树
     * <p>
     * 索然无味
     *
     * @param root
     * @return
     */
    public TreeNode balanceBST2(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return build(inorder, 0, inorder.size() - 1);
    }

    private TreeNode build(List<Integer> arr, int l, int r) {
        if (l == r) {
            return new TreeNode(arr.get(l));
        }
        if (l > r) {
            return null;
        }
        int middle = (l + r) >> 1;
        TreeNode root = new TreeNode(arr.get(middle));
        TreeNode left = build(arr, l, middle - 1);
        TreeNode right = build(arr, middle + 1, r);
        root.left = left;
        root.right = right;
        return root;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    //**********************旋转的方法****************************
    /**
     *
     * 作者：burning-summer
     *     链接：https://leetcode-cn.com/problems/balance-a-binary-search-tree/solution/shou-si-avlshu-wo-bu-guan-wo-jiu-shi-yao-xuan-zhua/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * node节点左旋
     *
     * @param node       node
     * @param nodeHeight node高度缓存
     * @return 旋转后的当前节点
     */
    public TreeNode rotateLeft(TreeNode node, Map<TreeNode, Integer> nodeHeight) {
        // ---旋转进行指针调整
        TreeNode right = node.right;
        node.right = right.left;
        right.left = node;
        // ---高度更新
        // 先更新node节点的高度，这个时候node是right节点的左孩子
        int newNodeHeight = getCurNodeNewHeight(node, nodeHeight);
        // 更新node节点高度
        nodeHeight.put(node, newNodeHeight);
        // newNodeHeight是现在right节点左子树高度。
        // 原理一样，取现在right左右子树最大高度+1
        int newRightHeight = Math.max(newNodeHeight, nodeHeight.getOrDefault(right.right, 0)) + 1;
        // 更新原right节点高度
        nodeHeight.put(right, newRightHeight);
        return right;
    }

    //获取当前节点的新高度
    private int getCurNodeNewHeight(TreeNode node, Map<TreeNode, Integer> nodeHeight) {
        // node节点的高度，为现在node左右子树最大高度+1
        return Math.max(nodeHeight.getOrDefault(node.left, 0), nodeHeight.getOrDefault(node.right, 0)) + 1;
    }

    public static void initHeight(TreeNode node, int parentHeight, Map<TreeNode, Integer> nodeHeight) {
        if (node == null) {
            return;
        }
        nodeHeight.put(node, parentHeight + 1);
        initHeight(node.left, parentHeight + 1, nodeHeight);
        initHeight(node.right, parentHeight + 1, nodeHeight);
    }
}
