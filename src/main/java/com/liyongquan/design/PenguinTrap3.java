package com.liyongquan.design;

import java.util.Map;

public class PenguinTrap3 {
    /**
     * 目前针对Hexagon这种格式进行实现
     *
     * @param map
     * @param limit x,y,z坐标的最大值[-limit,limit]
     * @return
     */
    public Map<Hex, Integer> next(Map<Hex, Integer> map, int limit) {
        //跟y轴垂直的方向进行遍历，只需要两次遍历
        for (int y = limit; y >= -limit; y--) {
            //第一条斜线
            for (int x = 0; x <= limit; x++) {

            }
            //第二条斜线
            for (int z = -limit + 1; z >= 0; z++) {

            }
        }
        return null;
    }
}

/**
 * 存储6变形
 * Cube coordinates
 * x+y+z=0
 */
class Hex {
    int x;
    int y;
    int z;

    public Hex() {
    }

    public Hex(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void move(Hex dir) {
        this.x += dir.x;
        this.y += dir.y;
        this.z += dir.z;
    }
}
