package com.liyongquan.string;

//552. 学生出勤记录 II
//可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
//'A'：Absent，缺勤
//'L'：Late，迟到
//'P'：Present，到场
//如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
//
//按 总出勤 计，学生缺勤（'A'）严格 少于两天。
//学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
//给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
//
// 
//
//示例 1：
//
//输入：n = 2
//输出：8
//解释：
//有 8 种长度为 2 的记录将被视为可奖励：
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
//示例 2：
//
//输入：n = 1
//输出：3
//示例 3：
//
//输入：n = 10101
//输出：183236316
// 
//
//提示：
//
//1 <= n <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/student-attendance-record-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/8/17
 */
public class StudentAttendanceRecord2 {
    /**
     * 终于通过了
     *
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        int mod = 1_000_000_007;
        //第一维是前N个数字，二维是最后连续迟到的天数，最后为缺席的总数
        int[][][] dp = new int[n][3][2];
        //初始化
        //P
        dp[0][0][0] = 1;
        //L
        dp[0][1][0] = 1;
        //A
        dp[0][0][1] = 1;
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    if (i == 0) {
                        //最后一天到场
                        for (int l = 0; l < 3; l++) {
                            dp[k][i][j] = (dp[k][i][j] + dp[k - 1][l][j]) % mod;
                        }
                        //最后一天缺席
                        if (j > 0) {
                            for (int l = 0; l < 3; l++) {
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][l][j - 1]) % mod;
                            }
                        }
                    } else {
                        //最后一天迟到
                        dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - 1][j]) % mod;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                res = (res + dp[n - 1][i][j]) % mod;
            }
        }
        return res;
    }
}
