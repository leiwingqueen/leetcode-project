package com.liyongquan.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//838. 推多米诺
//n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
//
//每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
//
//如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
//
//就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
//
//给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
//
//dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
//dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
//dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
//返回表示最终状态的字符串。
//
// 
//示例 1：
//
//输入：dominoes = "RR.L"
//输出："RR.L"
//解释：第一张多米诺骨牌没有给第二张施加额外的力。
//示例 2：
//
//
//输入：dominoes = ".L.R...LR..L.."
//输出："LL.RR.LLRRLL.."
// 
//
//提示：
//
//n == dominoes.length
//1 <= n <= 105
//dominoes[i] 为 'L'、'R' 或 '.'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/push-dominoes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        Queue<Integer> queue = new LinkedList<>();
        char[] res = dominoes.toCharArray();
        boolean[] visit = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (dominoes.charAt(i) != '.') {
                queue.add(i);
                visit[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                if (res[poll] == 'L') {
                    if (poll - 1 >= 0 && !visit[poll - 1]) {
                        //刚好两边受力平衡
                        if (res[poll - 1] == 'R') {
                            res[poll - 1] = '.';
                        } else {
                            res[poll - 1] = 'L';
                        }
                        set.add(poll - 1);
                    }
                } else {
                    if (poll + 1 < len && !visit[poll + 1]) {
                        //刚好两边受力平衡
                        if (res[poll + 1] == 'L') {
                            res[poll + 1] = '.';
                        } else {
                            res[poll + 1] = 'R';
                        }
                        set.add(poll + 1);
                    }
                }
            }
            //更新visit，注意，这里才能更新visit，同一层的节点不能因为遍历的先后顺序来决定最终的结果
            for (Integer idx : set) {
                visit[idx] = true;
                if (res[idx] != '.') {
                    queue.add(idx);
                }
            }
        }
        return new String(res);
    }
}
