package com.liyongquan.tree;

import javafx.util.Pair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [2,1,3], p = 1
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * 输出: null
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/successor-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InorderSuccessor {
    /**
     * 傻瓜解法，先不考虑任何优化，先做一次中序遍历，把所有节点按中序遍历打印出来，再找到对应的中序后缀
     * <p>
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list);
        //linklist使用迭代器的性能会更好，get(i)的性能是O(n)
        Iterator<TreeNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == p) {
                return iterator.hasNext() ? iterator.next() : null;
            }
        }
        return null;
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(root.left, list);
        }
        list.add(root);
        if (root.right != null) {
            dfs(root.right, list);
        }
    }

    /**
     * 场景1：根节点就是要找的节点p，则中序后继为右子树的第一个节点。(查找第一个节点另外写一个方法即可)
     * 场景2：p在左子树，且找不到左子树的中序后继(最右的叶子节点)，则根节点为中序后继(递归)
     * 场景3：p在右子树，直接递归查找右子树查找中序后续(递归)
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        return findSuccessor(root, p).getKey();
    }

    public Pair<TreeNode, Boolean> findSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return new Pair<>(null, false);
        }
        if (root == p) {
            return new Pair<>(findFirst(root.right), true);
        }
        Pair<TreeNode, Boolean> left = findSuccessor(root.left, p);
        if (left.getKey() != null) {
            return left;
        }
        if (left.getValue()) {
            return new Pair<>(root, true);
        }
        return findSuccessor(root.right, p);
    }

    /**
     * 一直往左边移动即可
     *
     * @param root
     * @return
     */
    private TreeNode findFirst(TreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


    /**
     * 由于是二叉搜索树，所以我们可以方便地判断结点到底在左子树还是右子树
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (root == p) {
            //直接找右子树的第一个结点
            TreeNode right = root.right;
            while (right != null && right.left != null) {
                right = right.left;
            }
            return right;
        }
        //左子树
        if (p.val < root.val) {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }

    /**
     * 大于和等于的场景合并,真的是合理的吗？
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        //左子树
        if (p.val < root.val) {
            TreeNode left = inorderSuccessor4(root.left, p);
            return left != null ? left : root;
        } else {
            return inorderSuccessor4(root.right, p);
        }
    }

    /**
     * BST的中序遍历就是升序的数组，则p的中序遍历的下一个节点就是答案
     * 非递归写法
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor5(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        boolean find = false;
        while (!stack.isEmpty() || root != null) {
            if (root == null) {
                TreeNode parent = stack.pop();
                //访问节点parent
                if (find) {
                    return parent;
                } else if (parent == p) {
                    find = true;
                }
                root = parent.right;
            } else {
                if (root.left != null) {
                    stack.add(root);
                    root = root.left;
                } else {
                    //访问节点root
                    if (find) {
                        return root;
                    } else if (root == p) {
                        find = true;
                    }
                    if (root.right != null) {
                        root = root.right;
                    } else {
                        //下一次父节点
                        root = null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 在上面的基础再继续优化一下，由于是BST，在检索p节点的过程我们可以把效率提高到O(log(n))
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor6(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            if (root.val == p.val) {
                break;
            } else if (root.val > p.val) {
                stack.add(root);
                root = root.left;
            } else {
                //右子树不需要压栈，因为右子树的父节点一定小于当前节点
                root = root.right;
            }
        }
        //存在右子树选择右子树的左下方第一个节点，否则就是栈顶的节点
        if (root.right == null) {
            return stack.isEmpty() ? null : stack.pop();
        } else {
            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

}
