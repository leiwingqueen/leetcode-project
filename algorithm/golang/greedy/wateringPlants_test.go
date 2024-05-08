package greedy

import "testing"

func Test_wateringPlants(t *testing.T) {
	type args struct {
		plants   []int
		capacity int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{2, 2, 3, 3}, 5}, 14},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := wateringPlants(tt.args.plants, tt.args.capacity); got != tt.want {
				t.Errorf("wateringPlants() = %v, want %v", got, tt.want)
			}
		})
	}
}
