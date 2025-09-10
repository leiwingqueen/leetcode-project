package array

import "testing"

func Test_minimumTeachings(t *testing.T) {
	type args struct {
		n           int
		languages   [][]int
		friendships [][]int
	}
	tests := []struct {
		name string
		args args
		want int
	}{
		// TODO: Add test cases.
		{"t1", args{3, [][]int{
			{2}, {1, 3}, {1, 2}, {3},
		}, [][]int{
			{1, 4}, {1, 2}, {3, 4}, {2, 3},
		}}, 2},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := minimumTeachings(tt.args.n, tt.args.languages, tt.args.friendships); got != tt.want {
				t.Errorf("minimumTeachings() = %v, want %v", got, tt.want)
			}
		})
	}
}
