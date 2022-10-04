package array

import "testing"

func Test_reformatNumber(t *testing.T) {
	type args struct {
		number string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"1-23-45 6"}, "123-456"},
		{"t2", args{"--17-5 229 35-39475 "}, "175-229-353-94-75"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := reformatNumber(tt.args.number); got != tt.want {
				t.Errorf("reformatNumber() = %v, want %v", got, tt.want)
			}
		})
	}
}
