package com.liyongquan.tree;

//给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
//
//有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
//
//一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
//
//1 <= xi.length <= 4
//xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
//在 xi 中允许前导零。
//例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
//
// 
//
//示例 1：
//
//输入：queryIP = "172.16.254.1"
//输出："IPv4"
//解释：有效的 IPv4 地址，返回 "IPv4"
//示例 2：
//
//输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
//输出："IPv6"
//解释：有效的 IPv6 地址，返回 "IPv6"
//示例 3：
//
//输入：queryIP = "256.256.256.256"
//输出："Neither"
//解释：既不是 IPv4 地址，又不是 IPv6 地址
// 
//
//提示：
//
//queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/validate-ip-address
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.function.Function;

public class ValidIPAddress {
    public String validIPAddress(String queryIP) {
        boolean ipV4 = check(queryIP, '.', s -> {
            if (s.length() <= 0 || s.length() > 3) {
                return false;
            }
            //前导0
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false;
            }
            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch < '0' || ch > '9') {
                    return false;
                }
                num = num * 10 + ch - '0';
            }
            return num >= 0 && num <= 255;
        }, 4);
        if (ipV4) {
            return IP.IPv4.name();
        }
        boolean ipV6 = check(queryIP, ':', s -> {
            if (s.length() == 0 || s.length() > 4) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F')) {
                    return false;
                }
            }
            return true;
        }, 8);
        if (ipV6) {
            return IP.IPv6.name();
        }
        return IP.Neither.name();
    }

    /**
     * 检测方法抽象
     *
     * @param queryIP
     * @param split      分隔符
     * @param func       每个分段的检测
     * @param expectSize 期望每个分段的长度
     * @return
     */
    public boolean check(String queryIP, char split, Function<String, Boolean> func, int expectSize) {
        int l = 0, r = 0;
        int cnt = 0;
        while (r < queryIP.length()) {
            if (queryIP.charAt(r) == split) {
                //[l,r)是一个分段
                if (r - l == 0) {
                    return false;
                }
                if (!func.apply(queryIP.substring(l, r))) {
                    return false;
                }
                r++;
                l = r;
                cnt++;
                if (cnt > expectSize) {
                    return false;
                }
            } else {
                r++;
            }
        }
        //最后一个段
        if (r - l == 0) {
            return false;
        }
        if (!func.apply(queryIP.substring(l, r))) {
            return false;
        }
        cnt++;
        return expectSize == cnt;
    }

    private enum IP {
        IPv4,
        IPv6,
        Neither
    }
}
