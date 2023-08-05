package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class GroupAlgTest {
    private GroupAlg groupAlg = new GroupAlg();

    @Test
    public void group() {
        List<List<String>> group = groupAlg.group(new String[]{"a", "a", "a", "b", "b", "b", "b", "c", "d", "e", "f", "", ""});
        for (List<String> list : group) {
            log.info("{}", list);
        }
    }
}