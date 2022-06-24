package com.liyongquan.string;

//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
//
//编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
//
// 
//
//示例 1：
//
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
//示例 2：
//
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
//提示：
//
//0 <= s.length <= 105
//s[i] 为 'A'、'C'、'G' 或 'T'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/repeated-dna-sequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liyongquan
 * @date 2021/10/8
 */
public class FindRepeatedDnaSequences {
    /**
     * 最傻瓜的解法
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return Collections.emptyList();
        }
        Set<String> res = new HashSet<>();
        Set<String> map = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (map.contains(sub)) {
                res.add(sub);
            } else {
                map.add(sub);
            }
        }
        return res.stream().collect(Collectors.toList());
    }

    /**
     * 优化解法，位运算
     * 2个bit存放一个字符,10个字符20个bit。
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        if (s.length() < 10) {
            return Collections.emptyList();
        }
        List<String> res = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        //初始化第一个窗口
        int num = 0;
        for (int i = 0; i < 10; i++) {
            num = (num << 2) + char2int(s.charAt(i));
        }
        map.put(num, 1);
        //滑动窗口
        int l = 1, r = l + 9;
        while (r < s.length()) {
            //删除高位
            num &= 0b111111111111111111;
            num = (num << 2) | char2int(s.charAt(r));
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 2) {
                //res.add(int2string(num));
                res.add(s.substring(l, l + 10));
            }
            l++;
            r++;
        }
        return res;
    }

    private int char2int(char ch) {
        if (ch == 'A') {
            return 0b00;
        } else if (ch == 'C') {
            return 0b01;
        } else if (ch == 'G') {
            return 0b10;
        } else {
            return 0b11;
        }
    }

    private String int2string(int num) {
        char[] arr = new char[10];
        for (int i = 9; i >= 0; i--) {
            int b = num & 0b11;
            if (b == 0) {
                arr[i] = 'A';
            } else if (b == 1) {
                arr[i] = 'C';
            } else if (b == 2) {
                arr[i] = 'G';
            } else {
                arr[i] = 'T';
            }
            num >>= 2;
        }
        return new String(arr);
    }
}