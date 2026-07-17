package wc510

import "testing"

func Test_secondsBetweenTimes(t *testing.T) {
	type args struct {
		startTime string
		endTime   string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"01:00:00", "01:00:25"}, 25},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := secondsBetweenTimes(tt.args.startTime, tt.args.endTime); got != tt.want {
				t.Errorf("secondsBetweenTimes() = %v, want %v", got, tt.want)
			}
		})
	}
}
