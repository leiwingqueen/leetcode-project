package com.liyongquan.dfs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KuohaoTest {
    private Kuohao kuohao=new Kuohao();

    @Test
    public void generateParenthesis() {
        List<String> strings = kuohao.generateParenthesis(3);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}