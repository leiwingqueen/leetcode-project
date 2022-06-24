package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/20
 */
@Slf4j
public class AddOperatorsTest {
    /**
     * "1000000009"
     * 9
     */
    @Test
    public void addOperators() {
        AddOperators operators = new AddOperators();
        List<String> res = operators.addOperators("10009", 9);
        for (String re : res) {
            System.out.println(re);
        }
    }
}