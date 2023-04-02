package bwc101

import "testing"

func Test_makeSubKSumEqual(t *testing.T) {
	type args struct {
		arr []int
		k   int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [2,5,5,7],3
		{"t1", args{[]int{2, 5, 5, 7}, 3}, 5},
		//[2,10,9]
		//1
		{"t2", args{[]int{2, 10, 9}, 1}, 8},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := makeSubKSumEqual(tt.args.arr, tt.args.k); got != tt.want {
				t.Errorf("makeSubKSumEqual() = %v, want %v", got, tt.want)
			}
		})
	}
}
