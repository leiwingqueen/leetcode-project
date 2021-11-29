package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/29
 */
@Slf4j
public class FindAnagramsTest {
    private FindAnagrams fa = new FindAnagrams();

    @Test
    public void findAnagrams3() {
        List<Integer> res = fa.findAnagrams3("cbaebabacd"
                , "abc");
        for (Integer re : res) {
            log.info("{}", re);
        }
    }
}