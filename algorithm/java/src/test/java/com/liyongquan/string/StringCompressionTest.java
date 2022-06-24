package com.liyongquan.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/21
 */
@Slf4j
public class StringCompressionTest {
    private StringCompression compression = new StringCompression();

    @Test
    public void compress() {
        char[] arr = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int res = compression.compress(arr);
        log.info("{}", String.valueOf(arr));
        Assert.assertEquals(4, res);
    }

    @Test
    public void int2arr() {
        char[] arr = compression.int2arr(12);
        log.info("{}", String.valueOf(arr));
        Assert.assertEquals("12", String.valueOf(arr));
    }
}