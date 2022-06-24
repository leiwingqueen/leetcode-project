package com.liyongquan.greedy;


/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Candy {
    /**
     * 贪心算法
     * 1.rating[i-1]=rating[i]
     * 则candy[i]=candy[i-1]
     * 2.rating[i-1]>rating[i]
     * 则candy[i]=candy[i-1]-1
     * 如果candy[i]=0，则前面所有用户都+1
     * 3.rating[i-1]<rating[i]
     * candy[i]=candy[i-1]+1
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        int count = 1;
        int[] candy = new int[ratings.length];
        candy[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] == ratings[i]) {
                candy[i] = 1;
            } else if (ratings[i - 1] > ratings[i]) {
                candy[i] = candy[i - 1] - 1;
                //前面所有孩子都增加一个糖果
                if (candy[i] == 0) {
                    count += i;
                    candy[i] = 1;
                }
            } else {
                candy[i] = candy[i - 1] + 1;
            }
            count += candy[i];
        }
        return count;
    }

    public int candy2(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }
        int[] candy = new int[len];
        candy[0] = 1;
        int sum = 1;
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
                sum += candy[i];
            } else if (ratings[i] == ratings[i - 1]) {
                candy[i] = 1;
                sum += 1;
            } else {
                candy[i] = 1;
                sum += 1;
                int j = i - 1;
                //前面的元素升一层
                while (j >= 0 && ratings[j] > ratings[j + 1] && candy[j] <= candy[j + 1]) {
                    candy[j] += 1;
                    sum += 1;
                    j--;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
        int c = candy.candy(new int[]{1, 3, 2, 2, 1});
        System.out.println(c);
    }
}
