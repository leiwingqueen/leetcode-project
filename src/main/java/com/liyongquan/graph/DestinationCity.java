package com.liyongquan.graph;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/1
 */
public class DestinationCity {
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        Iterator<String> it = map.values().iterator();
        String end = it.next();
        while (map.containsKey(end)) {
            end = map.get(end);
        }
        return end;
    }
}
