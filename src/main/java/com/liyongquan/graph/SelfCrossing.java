package com.liyongquan.graph;

//335. 路径交叉
//给你一个整数数组 distance 。
//
//从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
//
//判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//
//输入：distance = [2,1,1,2]
//输出：true
//示例 2：
//
//
//输入：distance = [1,2,3,4]
//输出：false
//示例 3：
//
//
//输入：distance = [1,1,1,1]
//输出：true
// 
//
//提示：
//
//1 <= distance.length <= 105
//1 <= distance[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/self-crossing
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author liyongquan
 * @date 2021/10/29
 */
public class SelfCrossing {
    public static final int[][] DIRS = {
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 0},
    };

    public boolean isSelfCrossing(int[] distance) {
        //分别是水平线和竖线
        TreeMap<Integer, TreeMap<Integer, int[]>> horizon = new TreeMap<>();
        TreeMap<Integer, TreeMap<Integer, int[]>> cross = new TreeMap<>();
        int dir = 0;
        int[] pos = {0, 0};
        for (int i = 0; i < distance.length; i++) {
            int x = DIRS[dir][0] * distance[i];
            int y = DIRS[dir][1] * distance[i];
            int[] next = {pos[0] + x, pos[1] + y};
            //水平线移动
            if (x > 0) {
                if (!horizon.containsKey(next[1])) {
                    horizon.put(next[1], new TreeMap<>());
                }
                //判断跟前面的竖线有没交集
                int x1 = pos[0], x2 = next[0];
                if (x1 > x2) {
                    x1 = next[0];
                    x2 = pos[0];
                }
                SortedMap<Integer, TreeMap<Integer, int[]>> subMap = cross.subMap(x1, true, x2, true);
                //遍历每一条线，判断纵坐标是否在里面
                int cnt = 0;
                for (TreeMap<Integer, int[]> line : subMap.values()) {
                    Map.Entry<Integer, int[]> entry = line.floorEntry(pos[1]);
                    if (entry != null && entry.getValue()[1] >= pos[1]) {
                        cnt++;
                        if (cnt > 1) {
                            return true;
                        }
                    }
                }
                //横线也要对比
                if (horizon.containsKey(next[1])) {
                    Map.Entry<Integer, int[]> l1 = horizon.get(next[1]).floorEntry(x1);
                    if (l1 != null && l1.getValue()[1] >= x1) {
                        return true;
                    }
                    Map.Entry<Integer, int[]> l2 = horizon.get(next[1]).floorEntry(x2);
                    if (l2 != null && l2.getValue()[1] >= x2) {
                        return true;
                    }
                }
                //更新横线
                horizon.get(next[1]).put(x1, new int[]{x1, x2});
            } else {
                if (!cross.containsKey(next[0])) {
                    cross.put(next[0], new TreeMap<>());
                }
                //判断跟前面的横线有没交集
                int y1 = pos[1], y2 = next[1];
                if (y1 > y2) {
                    y1 = next[1];
                    y2 = pos[1];
                }
                SortedMap<Integer, TreeMap<Integer, int[]>> subMap = horizon.subMap(y1, true, y2, true);
                //遍历每一条线，判断x坐标是否在里面
                int cnt = 0;
                for (TreeMap<Integer, int[]> line : subMap.values()) {
                    Map.Entry<Integer, int[]> entry = line.floorEntry(pos[0]);
                    if (entry != null && entry.getValue()[0] >= pos[0]) {
                        cnt++;
                        if (cnt > 1) {
                            return true;
                        }
                    }
                }
                //纵也要对比
                if (cross.containsKey(next[0])) {
                    Map.Entry<Integer, int[]> l1 = cross.get(next[0]).floorEntry(y1);
                    if (l1 != null && l1.getValue()[1] >= y1) {
                        return true;
                    }
                    Map.Entry<Integer, int[]> l2 = cross.get(next[0]).floorEntry(y2);
                    if (l2 != null && l2.getValue()[1] >= y2) {
                        return true;
                    }
                }
                //更新竖线
                cross.get(next[0]).put(y1, new int[]{y1, y2});
            }
            pos = next;
            //更新方向
            dir = (dir + 1) % 4;
        }
        return false;
    }
}
