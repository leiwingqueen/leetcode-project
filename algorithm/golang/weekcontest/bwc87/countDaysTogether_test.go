package bwc87

import "testing"

func Test_countDaysTogether(t *testing.T) {
	type args struct {
		arriveAlice string
		leaveAlice  string
		arriveBob   string
		leaveBob    string
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{"10-01", "10-31", "11-01", "12-31"}, 0},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := countDaysTogether(tt.args.arriveAlice, tt.args.leaveAlice, tt.args.arriveBob, tt.args.leaveBob); got != tt.want {
				t.Errorf("countDaysTogether() = %v, want %v", got, tt.want)
			}
		})
	}
}
