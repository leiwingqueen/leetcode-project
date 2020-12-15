package com.liyongquan.heap;

import org.junit.Test;

import javax.lang.model.element.VariableElement;

import static org.junit.Assert.*;

public class MinMeetingRoomsTest {
    private MinMeetingRooms mm = new MinMeetingRooms();

    @Test
    public void minMeetingRooms() {
        int[][] array = {{0, 30}, {5, 10}, {15, 20}};
        int res = mm.minMeetingRooms(array);
        System.out.println(res);
    }

    @Test
    public void test() {
        int[][] array = {{5, 8}, {6, 8}};
        int res = mm.minMeetingRooms(array);
        System.out.println(res);
    }
}