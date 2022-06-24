package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class BusiestServersTest {
    private BusiestServers servers = new BusiestServers();

    /**
     * 3
     * [1,2,3,4,5]
     * [5,2,3,3,3]
     */
    @Test
    public void busiestServers2() {
        List<Integer> res = servers.busiestServers2(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3});
        for (Integer re : res) {
            log.info("{}", re);
        }
    }
}