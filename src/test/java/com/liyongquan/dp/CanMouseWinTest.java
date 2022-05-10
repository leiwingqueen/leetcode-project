package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class CanMouseWinTest {
    //["####F","#C...","M...."]
    //1
    //2
    @Test
    public void canMouseWin() {
        CanMouseWin win = new CanMouseWin();
        boolean b = win.canMouseWin(new String[]{"####F", "#C...", "M...."}, 1, 2);
        Assert.assertEquals(true, b);
    }
}