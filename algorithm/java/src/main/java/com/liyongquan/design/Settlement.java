package com.liyongquan.design;

public class Settlement {
    /**
     * @param price 奖池。[0.6,0.3,0.1,0]表示第一个人拿到60%,第二个人拿走30%,第三个人拿到10,最后一个人拿到0%。
     *              并列的情况会出现平摊的场景，假设第一名有两个人，相当于第一名两个人平摊90%。
     * @param rank  [1,1,3,4]表示分别是第1,1,3,4名
     *              <p>
     *              其中price.length==rank.length==n
     * @return 每个人平分奖池的比例
     */
    public double[] cal(double[] price, int[] rank, int n) {
        double[] prefixSum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + price[i - 1];
        }
        double[] res = new double[n];
        int l = 0, r = 0;
        while (r < n) {
            //找出同名次的范围[l,r)
            while (r < n && rank[l] == rank[r]) {
                r++;
            }
            double d = (prefixSum[r] - prefixSum[l]) / (r - l);
            for (int i = l; i < r; i++) {
                res[i] = d;
            }
            l = r;
        }
        return res;
    }
}
