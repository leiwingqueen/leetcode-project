package com.liyongquan.design;

import java.util.Stack;

/**
 * 你有一个只支持单个标签页的 浏览器 ，最开始你浏览的网页是 homepage ，你可以访问其他的网站 url ，也可以在浏览历史中后退 steps 步或前进 steps 步。
 * <p>
 * 请你实现 BrowserHistory 类：
 * <p>
 * BrowserHistory(string homepage) ，用 homepage 初始化浏览器类。
 * void visit(string url) 从当前页跳转访问 url 对应的页面  。执行此操作会把浏览历史前进的记录全部删除。
 * string back(int steps) 在浏览历史中后退 steps 步。如果你只能在浏览历史中后退至多 x 步且 steps > x ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。
 * string forward(int steps) 在浏览历史中前进 steps 步。如果你只能在浏览历史中前进至多 x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
 */
public class BrowserHistory {
    private Stack<String> back = new Stack<>();
    private Stack<String> forward = new Stack<>();

    public BrowserHistory(String homepage) {
        back.push(homepage);
    }

    public void visit(String url) {
        back.push(url);
        forward.clear();
    }

    public String back(int steps) {
        int k = 0;
        while (back.size() > 1 && k < steps) {
            String pop = back.pop();
            forward.push(pop);
            k++;
        }
        return back.peek();
    }

    public String forward(int steps) {
        int k = 0;
        while (k < steps && !forward.isEmpty()) {
            String pop = forward.pop();
            back.push(pop);
            k++;
        }
        return back.peek();
    }
}
