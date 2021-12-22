package com.liyongquan.array;

import com.liyongquan.interview.FindBigger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class InterviewTest {
    private FindBigger it = new FindBigger();

    @Test
    public void find() {
        int[] res = it.find(new int[]{1, 5, 3, 6, 4, 8, 9, 10});
        log.info("{}", res);
    }

    @Test
    public void test2() {
        int[] arr = {8, 2, 5, 4, 3, 9, 7, 2, 5};
        int[] res = it.find(arr);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}