package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FreqStackTest {

    @Test
    public void push() {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        int pop = freqStack.pop();
        Assert.assertEquals(5, pop);
        pop = freqStack.pop();
        Assert.assertEquals(7, pop);
    }
}