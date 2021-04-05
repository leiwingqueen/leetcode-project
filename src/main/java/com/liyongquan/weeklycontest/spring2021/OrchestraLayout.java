package com.liyongquan.weeklycontest.spring2021;

public class OrchestraLayout {
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
                cnt += (edge - 1) * 4;
            }
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
}
