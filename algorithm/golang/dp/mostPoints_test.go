package dp

import "testing"

func Test_mostPoints(t *testing.T) {
	type args struct {
		questions [][]int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[][]int{
			{3, 2}, {4, 3}, {4, 4}, {2, 5},
		}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := mostPoints(tt.args.questions); got != tt.want {
				t.Errorf("mostPoints() = %v, want %v", got, tt.want)
			}
		})
	}
}
