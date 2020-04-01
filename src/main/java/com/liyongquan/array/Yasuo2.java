package com.liyongquan.array;

import java.util.HashSet;
import java.util.Set;

public class Yasuo2 {
    public int minimumLengthEncoding(String[] words) {
        TreeNode head=new TreeNode('#');
        Set<TreeNode> nodes=new HashSet<>();
        for (int j = 0, wordsLength = words.length; j < wordsLength; j++) {
            String word = words[j];
            TreeNode c = head;
            for (int i = word.length() - 1; i >= 0; i--) {
                c = c.addNode(word.charAt(i));
            }
            nodes.add(c);
        }
        int count=0;
        for (TreeNode treeNode : nodes) {
            if (treeNode.leaf) {
                count+=(treeNode.heigh+1);
            }
        }
        return count;
    }

    class TreeNode{
        char value;
        TreeNode[] childern=new TreeNode[26];
        int heigh=0;
        boolean leaf=true;
        public TreeNode(char c){
            this.value=c;
        }
        public TreeNode addNode(char c){
            leaf=false;
            if (childern[c-'a']==null) {
                TreeNode treeNode = new TreeNode(c);
                treeNode.heigh = heigh + 1;
                childern[c - 'a'] = treeNode;
            }
            return childern[c-'a'];
        }
    }

    public static void main(String[] args) {
        Yasuo2 yasuo=new Yasuo2();
        String[] a=new String[]{"time", "time", "time", "time"};
        int result = yasuo.minimumLengthEncoding(a);
        System.out.println(result);
    }
}
