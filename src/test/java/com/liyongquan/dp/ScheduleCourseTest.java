package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/12/14
 */
@Slf4j
public class ScheduleCourseTest {
    private ScheduleCourse sc = new ScheduleCourse();

    @Test
    public void scheduleCourse() {
        int[][] courses = {
                {3, 2}, {4, 3}
        };
        int r = sc.scheduleCourse(courses);
        log.warn("{}", r);
        Assert.assertEquals(0, r);
    }

    @Test
    public void test1() {
        int[][] courses = {
                {100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}
        };
        int r = sc.scheduleCourse(courses);
        Assert.assertEquals(3, r);
    }
}