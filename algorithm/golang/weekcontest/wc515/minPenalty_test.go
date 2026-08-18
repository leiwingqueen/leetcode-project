package wc515

import "testing"

func Test_minPenalty(t *testing.T) {
	type args struct {
		period      int
		lights      []int
		arrivalTime []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{8, []int{2, 3}, []int{2, 5, 8, 11}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minPenalty(tt.args.period, tt.args.lights, tt.args.arrivalTime); got != tt.want {
				t.Errorf("minPenalty() = %v, want %v", got, tt.want)
			}
		})
	}
}
