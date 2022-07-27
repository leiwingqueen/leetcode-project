package wc297

import "testing"

func Test_distinctNames2(t *testing.T) {
	type args struct {
		ideas []string
	}
	tests := []struct {
		name string
		args args
		want int64
	}{
		// TODO: Add test cases.
		{"t1", args{[]string{"coffee", "donuts", "time", "toffee"}}, 6},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := distinctNames2(tt.args.ideas); got != tt.want {
				t.Errorf("distinctNames2() = %v, want %v", got, tt.want)
			}
		})
	}
}
