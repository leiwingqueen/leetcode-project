package com.liyongquan.weeklycontest.wc285;

public class LongestRepeating {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryCharacters.length();
        int[] res = new int[k];
        char[] arr = s.toCharArray();
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            arr[idx] = ch;
            int l = 0, r = 0;
            int max = 0;
            while (r < n) {
                while (r < n && arr[r] == arr[l]) {
                    r++;
                }
                max = Math.max(max, r - l);
                l = r;
            }
            res[i] = max;
        }
        return res;
    }

    //TODO:线段树
    public int[] longestRepeating2(String s, String queryCharacters, int[] queryIndices) {
        return null;
    }

    private static class SegmentData {
        //左右两边的字符
        char lch;
        char rch;
        //范围
        int size;
        //前后缀的最长字符
        int pre;
        int suf;

        public SegmentData(int size) {
            this.pre = 0;
            this.suf = 0;
            this.size = size;
        }

        public SegmentData(char ch) {
            this.lch = ch;
            this.rch = ch;
            this.pre = 1;
            this.suf = 1;
            this.size = 1;
        }

        public SegmentData merge(SegmentData right) {
            SegmentData data = new SegmentData(this.size + right.size);
            data.rch = right.rch;
            if (this.rch != right.lch || right.suf != right.size) {
                data.suf = right.rch;
            } else {
                data.suf = right.size + this.suf;
            }
            data.lch = this.lch;
            if (this.lch != this.size || this.lch != right.lch) {
                data.pre = this.pre;
            } else {
                data.pre = this.size + right.pre;
            }
            return data;
        }
    }

    private static class SegmentNode {
        SegmentData data;
        int l;
        int r;
        SegmentNode left;
        SegmentNode right;

        public SegmentNode(SegmentData data, int l, int r) {
            this.data = data;
            this.l = l;
            this.r = r;
        }

        public static SegmentNode build(char[] arr, int l, int r) {
            if (l == r) {
                SegmentData data = new SegmentData(arr[l]);
                return new SegmentNode(data, l, r);
            }
            int mid = l + (r - l) / 2;
            //左区间[l,mid],右区间[mid+1,r]
            SegmentNode left = build(arr, l, mid);
            SegmentNode right = build(arr, mid + 1, r);
            SegmentData data = left.data.merge(right.data);
            SegmentNode cur = new SegmentNode(data, l, r);
            cur.left = left;
            cur.right = right;
            return cur;
        }
    }
}
