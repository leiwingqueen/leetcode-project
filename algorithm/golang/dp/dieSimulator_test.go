package dp

import "testing"

func Test_dieSimulator2(t *testing.T) {
	type args struct {
		n       int
		rollMax []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		//2
		//[1,1,2,2,2,3]
		{"t1", args{2, []int{1, 1, 2, 2, 2, 3}}, 34},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := dieSimulator2(tt.args.n, tt.args.rollMax); got != tt.want {
				t.Errorf("dieSimulator2() = %v, want %v", got, tt.want)
			}
		})
	}
}
