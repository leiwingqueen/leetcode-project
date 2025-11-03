package array

import "testing"

func Test_minCost(t *testing.T) {
	type args struct {
		colors     string
		neededTime []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"abaac", []int{1, 2, 3, 4, 5}}, 3},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minCost(tt.args.colors, tt.args.neededTime); got != tt.want {
				t.Errorf("minCost() = %v, want %v", got, tt.want)
			}
		})
	}
}
