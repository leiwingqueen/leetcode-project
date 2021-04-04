package com.liyongquan.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 781. 森林中的兔子
 * <p>
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * <p>
 * 返回森林中兔子的最少数量。
 * <p>
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 * <p>
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 * <p>
 * 输入: answers = []
 * 输出: 0
 * 说明:
 * <p>
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RabbitsInForest {
    /**
     * hashmap
     * <p>
     * key--对应颜色的数量，value--已经说话的兔子数量
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * <p>
     * 不通过
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            int cnt = answer + 1;
            //数量相同的尽量放在同一种颜色中，这样会让数量尽量少
            if (map.containsKey(cnt) && map.get(cnt) < cnt) {
                map.put(cnt, map.get(cnt) + 1);
            } else {
                map.put(cnt, 1);
            }
        }
        //统计数量
        int sum = 0;
        for (Integer key : map.keySet()) {
            sum += key;
        }
        return sum;
    }

    /**
     * 数量相同的尽量放在同一种颜色中，这样会让数量尽量少。
     * 并且同一种颜色为N的兔子最多只会有N个兔子说话，当第N+1个兔子说还有N个兔子的颜色相同时还需要新开一个颜色。
     * hashmap
     * <p>
     * key--对应颜色的数量，value--已经说话的兔子数量
     *
     * 当map中的一个key满了之后需要新开一个key
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * <p>
     * @param answers
     * @return
     */
    public int numRabbits2(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int answer : answers) {
            int cnt = answer + 1;
            //数量相同的尽量放在同一种颜色中，这样会让数量尽量少
            if (!map.containsKey(cnt) || map.get(cnt) >= cnt) {
                map.put(cnt, 1);
                sum += cnt;
            } else {
                map.put(cnt, map.get(cnt) + 1);
            }
        }
        return sum;
    }
}
