package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/9
 */
@Slf4j
public class FullJustifyTest {
    private FullJustify justify = new FullJustify();

    @Test
    public void fullJustify() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = justify.fullJustify(words, 16);
        for (String re : res) {
            System.out.println(re);
        }
        //"This    is    an",
        //"example  of text",
        //"justification.  "]
    }
}