package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ReadBinaryWatchTest {
    private ReadBinaryWatch rbw = new ReadBinaryWatch();

    @Test
    public void readBinaryWatch() {
        List<String> res = rbw.readBinaryWatch(1);
        for (String re : res) {
            log.info("{}", re);
        }
    }
}