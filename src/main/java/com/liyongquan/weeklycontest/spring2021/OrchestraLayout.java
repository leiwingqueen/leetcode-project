package com.liyongquan.weeklycontest.spring2021;

// LCP 29. 乐团站位
// 某乐团的演出场地可视作 `num * num` 的二维矩阵 `grid`（左上角坐标为 `[0,0]`)，每个位置站有一位成员。乐团共有 `9` 种乐器，乐
//器编号为 `1~9`，每位成员持有 `1` 个乐器。
//
//为保证声乐混合效果，成员站位规则为：自 `grid` 左上角开始顺时针螺旋形向内循环以 `1，2，...，9` 循环重复排列。例如当 num = `5` 时
//，站位如图所示
//
//![image.png](https://pic.leetcode-cn.com/1616125411-WOblWH-image.png)
//
//
//请返回位于场地坐标 [`Xpos`,`Ypos`] 的成员所持乐器编号。
//
//**示例 1：**
//>输入：`num = 3, Xpos = 0, Ypos = 2`
//>
//>输出：`3`
//>
//>解释：
//![image.png](https://pic.leetcode-cn.com/1616125437-WUOwsu-image.png)
//
//
//**示例 2：**
//>输入：`num = 4, Xpos = 1, Ypos = 2`
//>
//>输出：`5`
//>
//>解释：
//![image.png](https://pic.leetcode-cn.com/1616125453-IIDpxg-image.png)
//
//
//**提示：**
//- `1 <= num <= 10^9`
//- `0 <= Xpos, Ypos < num` 👍 17 👎 0

public class OrchestraLayout {
    /**
     * 不通过，写法有点绕
     *
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout(int num, int xPos, int yPos) {
        double middle = (num - 1) / 2d;
        int round = num / 2 - (int) Math.ceil(Math.max(Math.abs(xPos - middle), Math.abs(yPos - middle)));
        //前面的环的数量
        int cnt = 0;
        for (int i = 0; i < round; i++) {
            int edge = num - i * 2;
            if (edge == 1) {
                cnt += 1;
            } else {
                cnt += ((edge - 1) * 4) % 9;
            }
            cnt %= 9;
        }
        //x,y所在的环
        int edge = num - round * 2;
        if (edge == 1) {
            cnt += 1;
        } else {
            //左上角的坐标
            int x0 = round, y0 = round;
            if (x0 == xPos) {
                cnt += yPos - y0 + 1;
            } else if (y0 + edge - 1 == yPos) {
                cnt += edge - 1;
                cnt += xPos - x0 + 1;
            } else if (x0 + edge - 1 == xPos) {
                cnt += 2 * (edge - 1);
                cnt += Math.abs(y0 + edge - 1 - yPos) + 1;
            } else {
                cnt += 3 * (edge - 1);
                cnt += Math.abs(xPos - (x0 + edge - 1)) + 1;
            }
        }
        return cnt % 9 == 0 ? 9 : cnt % 9;
    }

    /**
     * 本质上求[x,y]所在的序号
     *
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout2(int num, int xPos, int yPos) {
        //求所在的层数(距离4条边的最近的距离)
        long round = Math.min(xPos, Math.min(yPos, Math.min(num - 1 - xPos, num - 1 - yPos)));
        //一共的层数[0,ceil(num/2)]
        //int maxRound = (num + 1) / 2;
        //前面每个环的数量 f(i)=(n-i*2-1)*4;
        //数学公式推导得到前i个环的总和为f(i)=4*n*(i+1)-4*(i+1)-i*(i+1)，注意这里有可能溢出，我们需要先取mod
        //int cnt = (4 * num * round - 4 * round - 4 * (round - 1) * round) % 9;
        long cnt = ((round % 9) * ((4 * (long)num - 4 * round) % 9)) % 9;
        //x,y所在的环
        long edge = num - round * 2;
        if (edge == 1) {
            cnt += 1;
        } else {
            //左上角的坐标
            long x0 = round, y0 = round;
            if (x0 == xPos) {
                cnt += yPos - y0 + 1;
            } else if (y0 + edge - 1 == yPos) {
                cnt += edge - 1;
                cnt += xPos - x0 + 1;
            } else if (x0 + edge - 1 == xPos) {
                cnt += 2 * (edge - 1);
                cnt += Math.abs(y0 + edge - 1 - yPos) + 1;
            } else {
                cnt += 3 * (edge - 1);
                cnt += Math.abs(xPos - (x0 + edge - 1)) + 1;
            }
        }
        return cnt % 9 == 0 ? 9 : (int) (cnt % 9);
    }
}
