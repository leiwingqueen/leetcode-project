package prefixSum

import "testing"

func Test_mincostToHireWorkers(t *testing.T) {
	type args struct {
		quality []int
		wage    []int
		k       int
	}
	tests := []struct {
		name string
		args args
		want float64
	}{
		// TODO: Add test cases.
		// [25,68,35,62,52,57,35,83,40,51]
		//[147,97,251,129,438,443,120,366,362,343]
		//6
		{"t1", args{[]int{25, 68, 35, 62, 52, 57, 35, 83, 40, 51}, []int{147, 97, 251, 129, 438, 443, 120, 366, 362, 343}, 6}, 1979.31429},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := mincostToHireWorkers(tt.args.quality, tt.args.wage, tt.args.k); got != tt.want {
				t.Errorf("mincostToHireWorkers() = %v, want %v", got, tt.want)
			}
		})
	}
}
