package wc315

import "testing"

func Test_countSubarrays(t *testing.T) {
	type args struct {
		nums []int
		minK int
		maxK int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{1, 3, 5, 2, 7, 5}, 1, 5}, 2},
		{"t2", args{[]int{1, 1, 1, 1}, 1, 1}, 10},
		//[35054,398719,945315,945315,820417,945315,35054,945315,171832,945315,35054,109750,790964,441974,552913]
		//35054
		//945315
		{"t3", args{[]int{35054, 398719, 945315, 945315, 820417, 945315, 35054, 945315, 171832, 945315, 35054, 109750, 790964, 441974, 552913}, 35054, 945315}, 81},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countSubarrays(tt.args.nums, tt.args.minK, tt.args.maxK); got != tt.want {
				t.Errorf("countSubarrays() = %v, want %v", got, tt.want)
			}
		})
	}
}
