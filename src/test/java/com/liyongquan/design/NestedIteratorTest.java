package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class NestedIteratorTest {
    @Test
    public void next() {
        NestedIntegerImpl ni = new NestedIntegerImpl();
        ni.add(new NestedIntegerImpl());
        NestedIterator it = new NestedIterator(Arrays.asList(ni));
        if (it.hasNext()) {
            log.info("{}", it.next());
        }
    }
}

class NestedIntegerImpl implements NestedInteger {
    private List<NestedInteger> list;
    private boolean isInt;
    private int num;

    public NestedIntegerImpl() {
        list = new ArrayList<>();
        isInt = false;
    }

    public NestedIntegerImpl(int num) {
        list = new ArrayList<>();
        isInt = true;
        this.num = num;
    }

    public void add(NestedInteger ni) {
        list.add(ni);
    }

    @Override
    public boolean isInteger() {
        return isInt;
    }

    @Override
    public Integer getInteger() {
        return num;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}