package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class GoodDaysToRobBankTest {
    private GoodDaysToRobBank bank = new GoodDaysToRobBank();

    @Test
    public void goodDaysToRobBank2() {
        List<Integer> res = bank.goodDaysToRobBank2(new int[]{5, 3, 3, 3, 5, 6, 2},
                2);
        for (Integer r : res) {
            log.info("{}", r);
        }
    }
}