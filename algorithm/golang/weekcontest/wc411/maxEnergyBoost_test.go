package wc411

import "testing"

func Test_maxEnergyBoost(t *testing.T) {
	type args struct {
		energyDrinkA []int
		energyDrinkB []int
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		// [1,3,1]
		//[3,1,1]
		{"t1", args{[]int{1, 3, 1}, []int{3, 1, 1}}, 5},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := maxEnergyBoost(tt.args.energyDrinkA, tt.args.energyDrinkB); got != tt.want {
				t.Errorf("maxEnergyBoost() = %v, want %v", got, tt.want)
			}
		})
	}
}
