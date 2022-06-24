package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/10
 */
@Slf4j
public class SlideWindowTest {
    @Test
    public void inc() throws InterruptedException {
        SlideWindow sw = new SlideWindow();
        for (int i = 0; i < 50; i++) {
            int qps = sw.inc(1);
            log.info("qps:{}", qps);
            Thread.sleep(i*10);
        }
    }
}