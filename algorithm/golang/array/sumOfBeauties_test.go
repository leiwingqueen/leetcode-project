package array

import "testing"

func Test_sumOfBeauties2(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 4, 6, 4}}, 1},
		{"t2", args{[]int{9, 9, 3, 8, 7, 9, 6, 10}}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := sumOfBeauties2(tt.args.nums); got != tt.want {
				t.Errorf("sumOfBeauties2() = %v, want %v", got, tt.want)
			}
		})
	}
}
