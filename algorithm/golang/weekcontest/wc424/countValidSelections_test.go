package wc424

import "testing"

func Test_countValidSelections(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 0, 2, 0, 3}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countValidSelections(tt.args.nums); got != tt.want {
				t.Errorf("countValidSelections() = %v, want %v", got, tt.want)
			}
		})
	}
}
