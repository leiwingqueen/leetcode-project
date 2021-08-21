package com.liyongquan.string;

//对于一个压缩字符串，设计一个数据结构，它支持如下两种操作： next 和 hasNext。
//
//给定的压缩字符串格式为：每个字母后面紧跟一个正整数，这个整数表示该字母在解压后的字符串里连续出现的次数。
//
//next() - 如果压缩字符串仍然有字母未被解压，则返回下一个字母，否则返回一个空格。
//hasNext() - 判断是否还有字母仍然没被解压。
//
//注意：
//
//请记得将你的类在 StringIterator 中 初始化 ，因为静态变量或类变量在多组测试数据中不会被自动清空。更多细节请访问 这里 。
//
//示例：
//
//StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
//
//iterator.next(); // 返回 'L'
//iterator.next(); // 返回 'e'
//iterator.next(); // 返回 'e'
//iterator.next(); // 返回 't'
//iterator.next(); // 返回 'C'
//iterator.next(); // 返回 'o'
//iterator.next(); // 返回 'd'
//iterator.hasNext(); // 返回 true
//iterator.next(); // 返回 'e'
//iterator.hasNext(); // 返回 false
//iterator.next(); // 返回 ' '
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/design-compressed-string-iterator
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/8/21
 */
public class StringIterator {
    private char ch;
    private int cnt;
    private int maxLen;
    //指向当前的下标和下一次的下标
    private int cur;
    private int next;
    private String str;

    public StringIterator(String compressedString) {
        str = compressedString;
        cur = 0;
        roll();
    }

    /**
     * 滚动到下一个字母
     */
    private void roll() {
        cnt = 0;
        ch = str.charAt(cur);
        int i = cur + 1;
        while (i < str.length() && isNumber(str.charAt(i))) {
            i++;
        }
        maxLen = Integer.valueOf(str.substring(cur + 1, i));
        next = i;
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        if (this.cnt < this.maxLen) {
            cnt++;
            return ch;
        }
        cur = next;
        roll();
        cnt++;
        return ch;
    }

    public boolean hasNext() {
        return this.cnt < this.maxLen || next < str.length();
    }

    private boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
