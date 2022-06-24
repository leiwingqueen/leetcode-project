package com.liyongquan.unionfind;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * <p>
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * <p>
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 *  
 * <p>
 * 提示：
 * <p>
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //邮箱-索引号&名称映射
        Map<String, MailInfo> map = new HashMap<>();
        int idx = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!map.containsKey(email)) {
                    map.put(email, new MailInfo(email, idx, name));
                    idx++;
                }
            }
        }
        //并查集合并
        UnionFind uf = new UnionFind(idx);
        for (List<String> account : accounts) {
            //使用第一个邮箱作为代表元
            for (int i = 2; i < account.size(); i++) {
                uf.union(map.get(account.get(1)).idx, map.get(account.get(i)).idx);
            }
        }
        //合并完后做一次遍历输出合并结果
        Map<Integer, Set<String>> m1 = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                MailInfo mailInfo = map.get(account.get(i));
                int parent = uf.find(mailInfo.idx);
                Set<String> set = m1.getOrDefault(parent, new HashSet<>());
                set.add(mailInfo.mail);
                m1.put(parent, set);
            }
        }
        //排序并整理输出结果
        List<List<String>> res = new LinkedList<>();
        for (Map.Entry<Integer, Set<String>> entry : m1.entrySet()) {
            LinkedList<String> list = new LinkedList<>();
            for (String s : entry.getValue()) {
                list.add(s);
            }
            list.sort(String::compareTo);
            //写入用户名
            String name = map.get(list.get(0)).name;
            list.offerFirst(name);
            res.add(list);
        }
        return res;
    }

    private static class MailInfo {
        String mail;
        int idx;
        String name;

        public MailInfo(String mail, int idx, String name) {
            this.mail = mail;
            this.idx = idx;
            this.name = name;
        }
    }

    private static class UnionFind {
        private int[] parent;

        public UnionFind(int len) {
            this.parent = new int[len];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < len; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                //路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                return true;
            } else {
                return false;
            }
        }
    }
}


