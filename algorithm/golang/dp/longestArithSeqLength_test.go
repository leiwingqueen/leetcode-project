package dp

import "testing"

func Test_longestArithSeqLength2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{3, 6, 9, 12}}, 4},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := longestArithSeqLength2(tt.args.nums); got != tt.want {
				t.Errorf("longestArithSeqLength2() = %v, want %v", got, tt.want)
			}
		})
	}
}
