package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/19
 */
@Slf4j
public class BadmintonTest {
    private Badminton badminton = new Badminton();

    @Test
    public void match() {
        Badminton.Player[] players = {
                new Badminton.Player("权", 100),
                new Badminton.Player("礼", 100),
                new Badminton.Player("锋", 300),
                new Badminton.Player("海真", 300),
                new Badminton.Player("健宁", 50),
                new Badminton.Player("命文", 50),
                new Badminton.Player("毅", 120),
                new Badminton.Player("明", 150),
        };
        List<String[]> res = badminton.match(players);
        System.out.println("==============对战名单==============");
        for (String[] re : res) {
            System.out.println(String.format("%s 和 %s VS %s 和 %s", re[0], re[1], re[2], re[3]));
        }
    }
}