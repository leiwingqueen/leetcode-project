package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/27
 */
@Slf4j
public class RandomFlipMatrixTest {

    @Test
    public void flip() {
        RandomFlipMatrix rfm = new RandomFlipMatrix(3, 1);
        rfm.flip();
        rfm.flip();
        rfm.flip();
        rfm.reset();
        rfm.flip();
    }
}