package stack

import "testing"

func Test_parseBoolExpr(t *testing.T) {
	type args struct {
		expression string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"&(|(f))"}, false},
		{"t2", args{"!(f)"}, true},
		{"t3", args{"!(&(!(&(f)),&(t),|(f,f,t)))"}, false},
		{"t4", args{"!(&(f))"}, true},
		{"t5", args{"&(t)"}, true},
		{"t6", args{"|(f,f,t)"}, true},
		{"t7", args{"!(&(t,t,t))"}, false},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := parseBoolExpr(tt.args.expression); got != tt.want {
				t.Errorf("parseBoolExpr() = %v, want %v", got, tt.want)
			}
		})
	}
}
