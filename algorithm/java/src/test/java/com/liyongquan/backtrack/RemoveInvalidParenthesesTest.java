package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/24
 */
@Slf4j
public class RemoveInvalidParenthesesTest {
    private RemoveInvalidParentheses parentheses = new RemoveInvalidParentheses();

    @Test
    public void removeInvalidParentheses() {
        List<String> res = parentheses.removeInvalidParentheses("()())()");
        for (String s : res) {
            log.info("{}", s);
        }
    }

    @Test
    public void test2() {
        List<String> res = parentheses.removeInvalidParentheses("x(");
        for (String s : res) {
            log.info("{}", s);
        }
    }
}