package com.liyongquan.design;

// 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
//
//当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
//
//返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
//
// 
//
//示例：
//
//输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
//输出：[null,0,9,4,2,null,5]
//解释：
//ExamRoom(10) -> null
//seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
//seat() -> 9，学生最后坐在 9 号座位上。
//seat() -> 4，学生最后坐在 4 号座位上。
//seat() -> 2，学生最后坐在 2 号座位上。
//leave(4) -> null
//seat() -> 5，学生最后坐在 5 号座位上。
// 
//
//提示：
//
//1 <= N <= 10^9
//在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
//保证在调用 ExamRoom.leave(p) 时有学生正坐在座位 p 上。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/exam-room
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.TreeMap;
import java.util.TreeSet;

// 等价于男生上厕所问题，先来一个最简单的解法
public class ExamRoom {
    private TreeSet<Integer> set;
    private int n;

    public ExamRoom(int n) {
        set = new TreeSet<>();
        this.n = n;
    }

    // 假设有n个区间，则每一次检查位置相当于O(n)，这里能否进行优化?
    public int seat() {
        if (set.size() == 0) {
            set.add(0);
            return 0;
        }
        int pre = -1;
        int res = -1;
        int mx = 0;
        for (Integer num : set) {
            if (pre >= 0) {
                if ((num - pre) / 2 > mx) {
                    mx = (num - pre) / 2;
                    res = pre + (num - pre) / 2;
                }
            }
            pre = num;
        }
        // 左右两个点特殊处理
        if (!set.contains(0)) {
            if (set.first() >= mx) {
                mx = set.first();
                res = 0;
            }
        }
        if (!set.contains(n - 1)) {
            if (n - 1 - set.last() > mx) {
                res = n - 1;
            }
        }
        if (res >= 0) {
            set.add(res);
        }
        return res;
    }

    public void leave(int p) {
        set.remove(p);
    }
}
