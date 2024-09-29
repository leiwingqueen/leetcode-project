package wc417

import "testing"

func Test_kthCharacter2(t *testing.T) {
	type args struct {
		k          int64
		operations []int
	}
	tests := []struct {
		name string
		args args
		want byte
	}{
		// TODO: Add test cases.
		{"t1", args{3, []int{1, 0}}, 'a'},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := kthCharacter2(tt.args.k, tt.args.operations); got != tt.want {
				t.Errorf("kthCharacter2() = %v, want %v", got, tt.want)
			}
		})
	}
}
