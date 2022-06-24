package com.liyongquan.weeklycontest.s313;

//为了不断提高用户使用的体验，开发团队正在对产品进行全方位的开发和优化。
//已知开发团队共有若干名成员，skills[i] 表示第 i 名开发人员掌握技能列表。如果两名成员各自拥有至少一门对方未拥有的技能，则这两名成员可以「合作开发」。
//请返回当前有多少对开发成员满足「合作开发」的条件。由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
//
//注意：
//
//对于任意 skills[i] 均升序排列。
//示例 1：
//
//输入：
//skills = [[1,2,3],[3],[2,4]]
//
//输出: 2
//
//解释：
//开发成员 [1,2,3] 和成员 [2,4] 满足「合作开发」的条件，技能 1 和 4 分别是对方未拥有的技术
//开发成员 [3] 和成员 [2,4] 满足「合作开发」的条件，技能 3 和 4 分别是对方未拥有的技术
//开发成员 [1,2,3] 和成员 [3] 不满足「合作开发」的条件，由于开发成员 [3] 没有对方未拥有的技术
//因此有 2 对开发成员满足「合作开发」的条件。
//
//示例 2：
//
//输入：
//skills = [[3],[6]]
//
//输出: 1
//
//解释：
//开发成员 [3] 和成员 [6] 满足「合作开发」的条件
//因此有 1 对开发成员满足「合作开发」的条件。
//
//提示：
//
//2 <= skills.length <= 10^5
//1 <= skills[i].length <= 4
//1 <= skills[i][j] <= 1000
//skills[i] 中不包含重复元素

import java.util.HashSet;
import java.util.Set;

public class CoopDevelop {
    /**
     * 暴力解法
     *
     * 超时
     *
     * @param skills
     * @return
     */
    public int coopDevelop(int[][] skills) {
        int len = skills.length;
        int mod = 1_000_000_007;
        int cnt = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (check(skills[i], skills[j])) {
                    cnt = (cnt + 1) % mod;
                }
            }
        }
        return cnt;
    }

    private boolean check(int[] s1, int[] s2) {
        if (s1.length < s2.length) {
            int[] tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : s1) {
            set.add(i);
        }
        for (int i : s2) {
            if (!set.contains(i)) {
                return true;
            }
        }
        return false;
    }
}
