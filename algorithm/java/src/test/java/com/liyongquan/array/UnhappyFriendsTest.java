package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/15
 */
@Slf4j
public class UnhappyFriendsTest {
    private UnhappyFriends friends = new UnhappyFriends();

    //4
    //[[1,2,3],[3,2,0],[3,1,0],[1,2,0]]
    //[[0,1],[2,3]]
    @Test
    public void unhappyFriends() {
        int[][] preferences = {
                {1, 2, 3},
                {3, 2, 0},
                {3, 1, 0},
                {1, 2, 0},
        };
        int[][] pairs = {
                {0, 1},
                {2, 3},
        };
        int res = friends.unhappyFriends(4, preferences, pairs);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}