package tire

import (
	"reflect"
	"testing"
)

func Test1(t *testing.T) {
	magicDictionary := Constructor()
	magicDictionary.BuildDict([]string{"hello", "leetcode"})
	got := magicDictionary.Search("hello")
	expect := false
	if !reflect.DeepEqual(got, expect) {
		t.Errorf("got() = %v, want %v", got, expect)
	}
}
