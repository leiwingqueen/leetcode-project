package wc394

import (
	"reflect"
	"testing"
)

func Test_dijkstra(t *testing.T) {
	type args struct {
		n     int
		edges [][]int
	}
	tests := []struct {
		name string
		args args
		want []bool
	}{
		{"t1", args{6, [][]int{
			{0, 1, 4}, {0, 2, 1}, {1, 3, 2}, {1, 4, 3}, {1, 5, 1}, {2, 3, 1}, {3, 5, 3}, {4, 5, 2},
		}}, []bool{true, true, true, false, true, true, true, false}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := findAnswer(tt.args.n, tt.args.edges); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("dijkstra() = %v, want %v", got, tt.want)
			}
		})
	}
}
