package dp

import "testing"

func Test_canReach(t *testing.T) {
	type args struct {
		arr     []int
		minJump int
		maxJump int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{0, 0, 1, 1, 0}, 2, 4}, true},
		{"t2", args{[]int{0, 0, 1, 1, 1, 1, 0}, 2, 4}, true},
		{"t3", args{[]int{0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0}, 2, 4}, false},
		{"t4", args{[]int{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0}, 2, 4}, true},
		{"t5", args{[]int{0, 0, 0}, 2, 4}, true},
		{"t6", args{[]int{0, 0, 0, 1, 1, 0}, 1, 1}, false},
		{"t7", args{[]int{0, 1, 0, 0, 1, 0}, 2, 3}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := canReach(tt.args.arr, tt.args.minJump, tt.args.maxJump); got != tt.want {
				t.Errorf("canReach() = %v, want %v", got, tt.want)
			}
		})
	}
}

func Test_canReach2(t *testing.T) {
	type args struct {
		arr     []int
		minJump int
		maxJump int
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		{"t1", args{[]int{0, 1, 0, 0, 1, 0}, 2, 3}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := canReach2(tt.args.arr, tt.args.minJump, tt.args.maxJump); got != tt.want {
				t.Errorf("canReach2() = %v, want %v", got, tt.want)
			}
		})
	}
}
