package com.liyongquan.string;


//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
//比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
//请你实现这个将字符串进行指定行数变换的函数：
//
//string convert(string s, int numRows);
// 
//
//示例 1：
//
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
//示例 2：
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//示例 3：
//
//输入：s = "A", numRows = 1
//输出："A"
// 
//
//提示：
//
//1 <= s.length <= 1000
//s 由英文字母（小写和大写）、',' 和 '.' 组成
//1 <= numRows <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/zigzag-conversion
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    /**
     * 模拟
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        //计算列数
        int col = 0;
        int len = s.length();
        while (len > 0) {
            //第一列
            len -= Math.min(len, numRows);
            col++;
            if (len <= 0) {
                break;
            }
            //中间
            if (numRows > 2) {
                int mid = Math.min(numRows - 2, len);
                col += mid;
                len -= mid;
            }
        }
        char[][] matrix = new char[numRows][col];
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            //第一列
            for (int k = 0; k < numRows && i < s.length(); k++) {
                matrix[k][j] = s.charAt(i++);
            }
            if (i == s.length()) {
                break;
            }
            j++;
            //中间n列
            if (numRows > 2) {
                for (int k = numRows - 2; k > 0 && i < s.length(); k--) {
                    matrix[k][j++] = s.charAt(i++);
                }
            }
        }
        //转换
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            for (int l = 0; l < col; l++) {
                if (matrix[k][l] != '\u0000') {
                    sb.append(matrix[k][l]);
                }
            }
        }
        return sb.toString();
    }

    public String convert2(String s, int numRows) {
        List<char[]> matrix = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            char[] col = new char[numRows];
            //第一列
            for (int k = 0; k < numRows && idx < s.length(); k++) {
                col[k] = s.charAt(idx++);
            }
            matrix.add(col);
            if (idx == s.length()) {
                break;
            }
            //中间n列
            if (numRows > 2) {
                for (int k = numRows - 2; k > 0 && idx < s.length(); k--) {
                    col = new char[numRows];
                    col[k] = s.charAt(idx++);
                    matrix.add(col);
                }
            }
        }
        //转换
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < matrix.size(); j++) {
                char ch = matrix.get(j)[i];
                if (ch != '\u0000') {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
}
