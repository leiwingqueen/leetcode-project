package com.liyongquan.unionfind;

/**
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * <p>
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * <p>
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * <p>
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 100
 * 1 <= strs[i].length <= 1000
 * sum(strs[i].length) <= 2 * 104
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 *  
 * <p>
 * 备注：
 * <p>
 *       字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/similar-string-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSimilarGroups {
    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                //先判断是否连通图，如果已经是连通图就没必要判断字母异位了
                int rootI = uf.find(i);
                int rootJ = uf.find(j);
                if (rootI != rootJ) {
                    //判断是否单字母异位
                    if (singleAnagram(strs[i], strs[j])) {
                        uf.union(i, j);
                    }
                }
            }
        }
        return uf.count;
    }

    private boolean singleAnagram(String a, String b) {
        int diff = 0, len = a.length();
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return diff <= 2;
    }

    /**
     * 并查集模板
     */
    private static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int size) {
            this.parent = new int[size];
            //初始化，每个节点的父节点就是自己
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                count++;
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

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
