package com.liyongquan.hash;

//391. 完美矩形
//给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
//
//如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
//
// 
//示例 1：
//
//
//输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
//输出：true
//解释：5 个矩形一起可以精确地覆盖一个矩形区域。
//示例 2：
//
//
//输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
//输出：false
//解释：两个矩形之间有间隔，无法覆盖成一个矩形。
//示例 3：
//
//
//输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
//输出：false
//解释：图形顶端留有空缺，无法覆盖成一个矩形。
//示例 4：
//
//
//输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
//输出：false
//解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
// 
//
//提示：
//
//1 <= rectangles.length <= 2 * 104
//rectangles[i].length == 4
//-105 <= xi, yi, ai, bi <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/perfect-rectangle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/11/16
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        //统计上下左右，4条边分别的范围
        Map<Integer, int[]> top = new HashMap<>(), down = new HashMap<>(),
                left = new HashMap<>(), right = new HashMap<>();
        Arrays.sort(rectangles, Comparator.comparingInt(o -> o[0]));
        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0], y1 = rectangle[1];
            int x2 = rectangle[2], y2 = rectangle[3];
            //top
            if (handle(top, x1, x2, y2)) return false;
            //down
            if (handle(down, x1, x2, y1)) return false;
            //left
            if (handle(left, y1, y2, x1)) return false;
            //right
            if (handle(right, y1, y2, x2)) return false;
        }
        //上下边进行抵消？
        if (remove(top, down)) return false;
        //左右边进行抵消
        if (remove(left, right)) return false;
        return true;
    }

    private boolean remove(Map<Integer, int[]> left, Map<Integer, int[]> right) {
        List<Integer> del = new LinkedList<>();
        for (Map.Entry<Integer, int[]> entry : left.entrySet()) {
            Integer y = entry.getKey();
            if (right.containsKey(y)) {
                int[] t1 = entry.getValue();
                int[] t2 = right.get(y);
                if (t1[0] != t2[0] || t1[1] != t2[1]) {
                    return true;
                }
                del.add(y);
            }
        }
        for (Integer d : del) {
            left.remove(d);
            right.remove(d);
        }
        if (left.size() != 1 || right.size() != 1) {
            return true;
        }
        int[] l = left.entrySet().iterator().next().getValue();
        int[] r = right.entrySet().iterator().next().getValue();
        if (l[0] != r[0] || l[1] != r[1]) {
            return true;
        }
        return false;
    }

    private boolean handle(Map<Integer, int[]> map, int x1, int x2, int y) {
        if (!map.containsKey(y)) {
            map.put(y, new int[]{x1, x2});
        } else {
            int[] t = map.get(y);
            if (t[1] != x1) {
                return true;
            }
            map.put(y, new int[]{t[0], x2});
        }
        return false;
    }
}
