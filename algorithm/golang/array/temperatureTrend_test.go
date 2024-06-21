package array

import "testing"

func Test_temperatureTrend(t *testing.T) {
	type args struct {
		temperatureA []int
		temperatureB []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{[]int{21, 18, 18, 18, 31}, []int{34, 32, 16, 16, 17}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := temperatureTrend(tt.args.temperatureA, tt.args.temperatureB); got != tt.want {
				t.Errorf("temperatureTrend() = %v, want %v", got, tt.want)
			}
		})
	}
}
