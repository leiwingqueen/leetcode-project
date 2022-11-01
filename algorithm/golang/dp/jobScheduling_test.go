package dp

import "testing"

func Test_jobScheduling(t *testing.T) {
	type args struct {
		startTime []int
		endTime   []int
		profit    []int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		//startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
		{"t1", args{[]int{1, 2, 3, 3},
			[]int{3, 4, 5, 6}, []int{50, 10, 40, 70}}, 120},
		//[1,1,1]
		//[2,3,4]
		//[5,6,4]
		{"t2", args{[]int{1, 1, 1},
			[]int{2, 3, 4}, []int{5, 6, 4}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := jobScheduling(tt.args.startTime, tt.args.endTime, tt.args.profit); got != tt.want {
				t.Errorf("jobScheduling() = %v, want %v", got, tt.want)
			}
		})
	}
}
