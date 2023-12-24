package wc377

import "testing"

func Test_minimumCost(t *testing.T) {
	type args struct {
		source   string
		target   string
		original []byte
		changed  []byte
		cost     []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// 输入：source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
		//输出：12
		{"t1", args{"aaaa", "bbbb", []byte{'a', 'c'}, []byte{'c', 'b'}, []int{1, 2}}, 12},
		// source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
		{"t2", args{"abcd", "abce", []byte{'a'}, []byte{'e'}, []int{10000}}, -1},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumCost(tt.args.source, tt.args.target, tt.args.original, tt.args.changed, tt.args.cost); got != tt.want {
				t.Errorf("minimumCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
