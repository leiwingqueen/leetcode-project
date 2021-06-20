package com.liyongquan.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ThroneInheritance {
    private Map<String, ThroneNode> map = new HashMap<>();
    private ThroneNode root;

    public ThroneInheritance(String kingName) {
        root = new ThroneNode(kingName, true);
        map.put(root.name, root);
    }

    public void birth(String parentName, String childName) {
        ThroneNode child = new ThroneNode(childName, true);
        map.get(parentName).addChild(child);
        map.put(child.name, child);
    }

    public void death(String name) {
        map.get(name).death();
    }

    //dfs
    public List<String> getInheritanceOrder() {
        List<String> order = new LinkedList<>();
        dfs(root, order);
        return order;
    }

    private void dfs(ThroneNode node, List<String> order) {
        if (node == null) {
            return;
        }
        if (node.live) {
            order.add(node.name);
        }
        for (ThroneNode child : node.child) {
            dfs(child, order);
        }
    }

    private static class ThroneNode {
        String name;
        boolean live;
        List<ThroneNode> child;

        public ThroneNode(String name, boolean live) {
            this.name = name;
            this.live = live;
            this.child = new LinkedList<>();
        }

        public void addChild(ThroneNode child) {
            this.child.add(child);
        }

        public void death() {
            this.live = false;
        }
    }
}
