package com.liyongquan.weeklycontest.bwc52;

public class SortSentence {
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        String[] arr = new String[words.length];
        for (String word : words) {
            int idx = Integer.parseInt(word.substring(word.length() - 1)) - 1;
            arr[idx] = word.substring(0, word.length() - 1);
        }
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
