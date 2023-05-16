package dp

import "testing"

func Test_minDifficulty(t *testing.T) {
	type args struct {
		jobDifficulty []int
		d             int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [6,5,4,3,2,1]
		//2
		{"t1", args{[]int{6, 5, 4, 3, 2, 1}, 2}, 7},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minDifficulty(tt.args.jobDifficulty, tt.args.d); got != tt.want {
				t.Errorf("minDifficulty() = %v, want %v", got, tt.want)
			}
		})
	}
}
