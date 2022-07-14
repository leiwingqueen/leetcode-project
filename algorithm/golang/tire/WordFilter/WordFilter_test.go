package WordFilter

import (
	"reflect"
	"testing"
)

func Test1(t *testing.T) {
	filter := Constructor([]string{"apple"})
	got := filter.F("a", "e")
	expect := 0
	if !reflect.DeepEqual(got, expect) {
		t.Errorf("got() = %v, want %v", got, expect)
	}
}
