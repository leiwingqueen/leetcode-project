package hash

import "testing"

func Test_tupleSameProduct(t *testing.T) {
	type args struct {
		nums []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		// [2,3,4,6,8,12]
		{"t1", args{[]int{2, 3, 4, 6, 8, 12}}, 40},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := tupleSameProduct(tt.args.nums); got != tt.want {
				t.Errorf("tupleSameProduct() = %v, want %v", got, tt.want)
			}
		})
	}
}
