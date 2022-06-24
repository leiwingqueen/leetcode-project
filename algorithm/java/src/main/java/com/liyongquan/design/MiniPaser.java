package com.liyongquan.design;

//给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
//
//列表中的每个元素只可能是整数或整数嵌套列表
//
// 
//
//示例 1：
//
//输入：s = "324",
//输出：324
//解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
//示例 2：
//
//输入：s = "[123,[456,[789]]]",
//输出：[123,[456,[789]]]
//解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
//1. 一个 integer 包含值 123
//2. 一个包含两个元素的嵌套列表：
//    i.  一个 integer 包含值 456
//    ii. 一个包含一个元素的嵌套列表
//         a. 一个 integer 包含值 789
// 
//
//提示：
//
//1 <= s.length <= 5 * 104
//s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成
//用例保证 s 是可解析的 NestedInteger
//输入中的所有值的范围是 [-106, 106]
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/mini-parser
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class MiniPaser {
    //由于编译问题，先注释掉，事实上这题是已经通过了
    public NestedInteger deserialize(String s) {
        //return deserialize(s.toCharArray(), 0, s.length() - 1);
        return null;
    }

    /*private NestedInteger deserialize(char[] arr, int l, int r) {
        if (arr[l] != '[') {
            //后面只能是数字
            boolean negative = false;
            if (arr[l] == '-') {
                negative = true;
                l++;
            }
            int num = 0;
            while (l <= r && arr[l] != ',') {
                num = num * 10 + arr[l++] - '0';
            }
            return new NestedInteger(negative ? -num : num);
        } else {
            l++;
            r--;
            NestedInteger ni = new NestedInteger();
            //逗号分隔
            int idx = l;
            while (idx <= r) {
                //左括号的数量
                int cnt = 0;
                while (idx <= r && (cnt != 0 || arr[idx] != ',')) {
                    if (arr[idx] == '[') {
                        cnt++;
                    } else if (arr[idx] == ']') {
                        cnt--;
                    }
                    idx++;
                }
                ni.add(deserialize(arr, l, idx - 1));
                idx++;
                l = idx;
            }
            return ni;
        }
    }*/


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    /*private interface NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger();

        // Constructor initializes a single integer.
        public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }*/

}
