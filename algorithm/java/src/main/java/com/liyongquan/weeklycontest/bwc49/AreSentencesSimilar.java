package com.liyongquan.weeklycontest.bwc49;

public class AreSentencesSimilar {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" "), words2 = sentence2.split(" ");
        if (words1.length < words2.length) {
            String[] tmp = words1;
            words1 = words2;
            words2 = tmp;
        }
        //找到左边最大匹配的单词数量
        int i = 0;
        while (i < words2.length && words1[i].equals(words2[i])) {
            i++;
        }
        if (i == words2.length) {
            return true;
        }
        //右边最大匹配
        int j = words2.length - 1, k = words1.length - 1;
        while (j >= i && words1[k].equals(words2[j])) {
            j--;
            k--;
        }
        return j < i;
    }
}
