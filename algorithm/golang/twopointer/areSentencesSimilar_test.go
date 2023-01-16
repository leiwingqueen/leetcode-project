package twopointer

import "testing"

func Test_areSentencesSimilar2(t *testing.T) {
	type args struct {
		sentence1 string
		sentence2 string
	}
	tests := []struct {
		name string
		args args
		want bool
	}{
		// TODO: Add test cases.
		{"t1", args{"My name is Haley", "My Haley"}, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := areSentencesSimilar2(tt.args.sentence1, tt.args.sentence2); got != tt.want {
				t.Errorf("areSentencesSimilar2() = %v, want %v", got, tt.want)
			}
		})
	}
}
