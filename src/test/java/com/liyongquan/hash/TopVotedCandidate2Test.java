package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/12/11
 */
@Slf4j
public class TopVotedCandidate2Test {

    //["TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"]
    //[[[0,1,2,2,1],[20,28,29,54,56]],[28],[53],[57],[29],[29],[28],[30],[20],[56],[55]]
    @Test
    public void q() {
        TopVotedCandidate2 vote = new TopVotedCandidate2(new int[]{0, 1, 2, 2, 1}, new int[]{20, 28, 29, 54, 56});
        int res = vote.q(56);
        Assert.assertEquals(1, res);
    }
}