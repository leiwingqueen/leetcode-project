package wc400

import "testing"

func Test_countDays(t *testing.T) {
	type args struct {
		days     int
		meetings [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		{"t1", args{57,
			[][]int{
				{3, 49}, {23, 44}, {21, 56}, {26, 55}, {23, 52}, {2, 9}, {1, 48}, {3, 31},
			},
		}, 1,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countDays(tt.args.days, tt.args.meetings); got != tt.want {
				t.Errorf("countDays() = %v, want %v", got, tt.want)
			}
		})
	}
}
