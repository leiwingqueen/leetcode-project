package com.liyongquan.stack;

//给定一个化学式formula（作为字符串），返回每种原子的数量。
//
// 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
//
// 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
//
//
// 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
//
// 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
//
// 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数
//量（如果数量大于 1），以此类推。
//
// 示例 1:
//
//
//输入:
//formula = "H2O"
//输出: "H2O"
//解释:
//原子的数量是 {'H': 2, 'O': 1}。
//
//
// 示例 2:
//
//
//输入:
//formula = "Mg(OH)2"
//输出: "H2MgO2"
//解释:
//原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
//
//
// 示例 3:
//
//
//输入:
//formula = "K4(ON(SO3)2)2"
//输出: "K4N2O14S4"
//解释:
//原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
//
//
// 注意:
//
//
// 所有原子的第一个字母为大写，剩余字母都是小写。
// formula的长度在[1, 1000]之间。
// formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
//
// Related Topics 栈 递归 哈希表
// 👍 119 👎 0


import java.util.*;

public class CountOfAtoms {
    /**
     * 递归解法
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = dfs(formula);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> dfs(String formula) {
        int len = formula.length();
        if (len == 0) {
            return new HashMap<>();
        }
        Map<String, Integer> map = new TreeMap<>();
        int idx = 0;
        while (idx < len) {
            Map<String, Integer> sub = new TreeMap<>();
            if (formula.charAt(idx) == '(') {
                //找到最右边的合法的括号
                idx++;
                int start = idx;
                int left = 1;
                while (idx < len && left > 0) {
                    if (formula.charAt(idx) == ')') {
                        left--;
                    } else if (formula.charAt(idx) == '(') {
                        left++;
                    }
                    idx++;
                }
                //[start,idx-1)
                if (idx - 1 - start < 1) {
                    sub = dfs(formula.substring(start, idx - 1));
                }

            } else {
                int start = idx;
                idx++;
                while (idx < len && formula.charAt(idx) >= 'a' && formula.charAt(idx) <= 'z') {
                    idx++;
                }
                sub.put(formula.substring(start, idx), 1);
            }
            //找到下一个数字
            int num = 0;
            while (idx < len && formula.charAt(idx) >= '0' && formula.charAt(idx) <= '9') {
                num = num * 10 + formula.charAt(idx) - '0';
                idx++;
            }
            if (num == 0) {
                num = 1;
            }
            for (Map.Entry<String, Integer> entry : sub.entrySet()) {
                map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + entry.getValue() * num);
            }
        }
        return map;
    }

    /**
     * 涉及到括号，首先想到利用栈进行计算
     *
     * @param formula
     * @return
     */
    public String countOfAtoms2(String formula) {
        List<Token> tokens = lex(formula);
        //在元素后面补1
        List<Token> list = new LinkedList<>();
        Token before = null;
        for (Token token : tokens) {
            if (before != null && before instanceof Element && (token instanceof Element || token instanceof Bracket)) {
                list.add(new Number(1));
            }
            if (before instanceof Bracket && ((Bracket) before).value == ')' && !(token instanceof Number)) {
                list.add(new Number(1));
            }
            list.add(token);
            before = token;
        }
        if (before instanceof Bracket || before instanceof Element) {
            list.add(new Number(1));
        }
        Stack<Token> stack = new Stack<>();
        Iterator<Token> it = list.iterator();
        while (it.hasNext()) {
            Token token = it.next();
            if (token instanceof Number || token instanceof Element) {
                stack.add(token);
            } else if (token instanceof Bracket) {
                Bracket bracket = (Bracket) token;
                if (bracket.value == '(') {
                    stack.add(token);
                } else {
                    //后面一定跟着一个数字？
                    int next = 1;
                    if (it.hasNext()) {
                        Number number = (Number) it.next();
                        next = number.value;
                    }
                    Map<String, Integer> map = new HashMap<>();
                    //pop出所有节点，然后重新计算
                    while (stack.size() > 0) {
                        if (stack.peek() instanceof Bracket) {
                            break;
                        }
                        Number num = (Number) stack.pop();
                        Element ele = (Element) stack.pop();
                        map.put(ele.name, map.getOrDefault(ele.name, 0) + num.value);
                    }
                    //pop出左空格
                    stack.pop();
                    //重新放入栈中
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        stack.add(new Element(entry.getKey()));
                        stack.add(new Number(next * entry.getValue()));
                    }
                }
            }
        }
        Map<String, Integer> map = new TreeMap<>();
        while (!stack.isEmpty()) {
            Number num = (Number) stack.pop();
            Element ele = (Element) stack.pop();
            map.put(ele.name, map.getOrDefault(ele.name, 0) + num.value);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    /**
     * 词法分析
     *
     * @param formula
     * @return
     */
    private List<Token> lex(String formula) {
        List<Token> res = new LinkedList<>();
        StringBuilder name = new StringBuilder();
        int num = -1;
        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            //大写字母
            if (ch >= 'A' && ch <= 'Z') {
                if (name.length() > 0) {
                    res.add(new Element(name.toString()));
                    name = new StringBuilder();
                }
                if (num >= 0) {
                    res.add(new Number(num));
                    num = -1;
                }
                name.append(ch);
            } else if (ch >= 'a' && ch <= 'z') {
                if (name.length() > 0) {
                    name.append(ch);
                }
            } else if (ch >= '0' && ch <= '9') {
                if (name.length() > 0) {
                    res.add(new Element(name.toString()));
                    name = new StringBuilder();
                }
                if (num < 0) {
                    num = ch - '0';
                } else {
                    num = num * 10 + (ch - '0');
                }
            } else if (ch == '(' || ch == ')') {
                if (name.length() > 0) {
                    res.add(new Element(name.toString()));
                    name = new StringBuilder();
                }
                if (num >= 0) {
                    res.add(new Number(num));
                    num = -1;
                }
                res.add(new Bracket(ch));
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (name.length() > 0) {
            res.add(new Element(name.toString()));
        }
        if (num >= 0) {
            res.add(new Number(num));
        }
        return res;
    }

    private interface Token {
    }

    private static class Element implements Token {
        String name;

        public Element(String name) {
            this.name = name;
        }
    }

    private static class Number implements Token {
        int value;

        public Number(int value) {
            this.value = value;
        }
    }

    private static class Bracket implements Token {
        char value;

        public Bracket(char value) {
            this.value = value;
        }
    }

}
