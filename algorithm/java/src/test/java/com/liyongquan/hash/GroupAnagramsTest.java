package com.liyongquan.hash;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GroupAnagramsTest {
    private GroupAnagrams ga = new GroupAnagrams();

    @Test
    public void groupAnagrams() {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = ga.groupAnagrams(arr);
        for (List<String> list : lists) {
            System.out.println("======================");
            for (String s : list) {
                System.out.print(s + ",");
            }
            System.out.println();
        }
    }
}