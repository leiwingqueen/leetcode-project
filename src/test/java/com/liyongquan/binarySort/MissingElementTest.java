package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MissingElementTest {
    private MissingElement me = new MissingElement();

    @Test
    public void missingElement() {
        int[] arr = {
                4, 7, 9, 10
        };
        int res = me.missing(arr, 7);
        log.info("{}", res);
        //int r = me.missingElement(arr, 1);
        //log.info("{}", r);
    }
}