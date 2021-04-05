package com.liyongquan.weeklycontest.spring2021;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class OrchestraLayoutTest {
    private OrchestraLayout orchestraLayout = new OrchestraLayout();

    @Test
    public void orchestraLayout() {
        int res = orchestraLayout.orchestraLayout(449572, 209397, 306801);
        log.info("{}", res);
    }
}