package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TagValidatorTest {
    private TagValidator validator = new TagValidator();

    @Test
    public void isValid() {
        boolean res = validator.isValid("<A>  <B> </A>   </B>");
        Assert.assertEquals(false, res);
    }

    //"<DIV>This is the first line <![CDATA[<div>]]></DIV>"
    @Test
    public void test() {
        boolean res = validator.isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>");
        Assert.assertEquals(true, res);
    }


    //"<DIV>This is the first line <![CDATA[<div>]]><DIV>"
    @Test
    public void test2() {
        boolean res = validator.isValid("<DIV>This is the first line <![CDATA[<div>]]><DIV>");
        Assert.assertEquals(false, res);
    }
}