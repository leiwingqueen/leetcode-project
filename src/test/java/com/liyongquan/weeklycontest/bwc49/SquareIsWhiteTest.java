package com.liyongquan.weeklycontest.bwc49;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.SignatureException;

import static org.junit.Assert.*;

@Slf4j
public class SquareIsWhiteTest {
    private SquareIsWhite siw = new SquareIsWhite();

    @Test
    public void squareIsWhite() {
        boolean res = siw.squareIsWhite("h3");
        log.info("{}", res);
        Assert.assertTrue(res);
    }
}