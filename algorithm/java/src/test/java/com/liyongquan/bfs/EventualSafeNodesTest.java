package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/5
 */
@Slf4j
public class EventualSafeNodesTest {
    private EventualSafeNodes esn = new EventualSafeNodes();

    @Test
    public void eventualSafeNodes() {
        int[][] graph = {
                {}, {0, 2, 3, 4}, {3}, {4}, {}
        };
        List<Integer> res = esn.eventualSafeNodes(graph);
        for (Integer num : res) {
            log.info("{}", num);
        }
    }
}