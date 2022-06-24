package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ValidIPAddressTest {
    private ValidIPAddress validIPAddress = new ValidIPAddress();

    @Test
    public void validIPAddress() {
        String res = validIPAddress.validIPAddress("172.16.254.1");
        Assert.assertEquals("IPv4", res);
    }
}