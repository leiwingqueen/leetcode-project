package string

import "testing"

func Test_capitalizeTitle(t *testing.T) {
	type args struct {
		title string
	}
	tests := []struct {
		name string
		args args
		want string
	}{
		// TODO: Add test cases.
		{"t1", args{"capiTalIze tHe titLe"}, "Capitalize The Title"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := capitalizeTitle(tt.args.title); got != tt.want {
				t.Errorf("capitalizeTitle() = %v, want %v", got, tt.want)
			}
		})
	}
}
