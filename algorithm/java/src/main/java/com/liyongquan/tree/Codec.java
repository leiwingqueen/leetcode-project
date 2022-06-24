package com.liyongquan.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Codec {
    /**
     * 考虑用leetcode最经常用的格式
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val + ",");
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode parent = queue.poll();
                if (parent.left != null) {
                    queue.offer(parent.left);
                    sb.append(parent.left.val + ",");
                } else {
                    sb.append("null,");
                }
                if (parent.right != null) {
                    queue.offer(parent.right);
                    sb.append(parent.right.val + ",");
                } else {
                    sb.append("null,");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] split = data.split(",");
        int len = split.length;
        if (len == 0) {
            return null;
        }
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            if (split[i].equals("null")) {
                arr[i] = null;
            } else {
                arr[i] = Integer.parseInt(split[i]);
            }
        }
        if (arr[0] == null) {
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
}
