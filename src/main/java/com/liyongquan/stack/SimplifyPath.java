package com.liyongquan.stack;

//给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
//
// 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成
//部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
//
// 请注意，返回的 规范路径 必须遵循下述格式：
//
//
// 始终以斜杠 '/' 开头。
// 两个目录名之间必须只有一个斜杠 '/' 。
// 最后一个目录名（如果存在）不能 以 '/' 结尾。
// 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
//
//
// 返回简化后得到的 规范路径 。
//
//
//
// 示例 1：
//
//
//输入：path = "/home/"
//输出："/home"
//解释：注意，最后一个目录名后面没有斜杠。
//
// 示例 2：
//
//
//输入：path = "/../"
//输出："/"
//解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
//
//
// 示例 3：
//
//
//输入：path = "/home//foo/"
//输出："/home/foo"
//解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
//
//
// 示例 4：
//
//
//输入：path = "/a/./b/../../c/"
//输出："/c"
//
//
//
//
// 提示：
//
//
// 1 <= path.length <= 3000
// path 由英文字母，数字，'.'，'/' 或 '_' 组成。
// path 是一个有效的 Unix 风格绝对路径。
//
// Related Topics 栈 字符串
// 👍 258 👎 0

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {
    /**
     * 以/划分
     * .||空 不需要操作
     * ..出栈
     * 其它入栈
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        int len = path.length();
        int idx = 0;
        Deque<String> deque = new LinkedList<>();
        while (idx < len) {
            //先去掉前缀的/
            while (idx < len && path.charAt(idx) == '/') {
                idx++;
            }
            //找到下一个'/'
            int start = idx;
            while (idx < len && path.charAt(idx) != '/') {
                idx++;
            }
            //[start,idx)为中间的字符
            if (idx - start < 1 || path.substring(start, idx).equals(".")) {
                continue;
            } else if ("..".equals(path.substring(start, idx))) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else {
                deque.offerLast(path.substring(start, idx));
            }
        }
        //输出
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/" + deque.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
