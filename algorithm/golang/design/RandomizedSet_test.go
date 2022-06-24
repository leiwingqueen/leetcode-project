package design

import (
	"fmt"
	"testing"
)

func TestT1(t *testing.T) {
	rs := Constructor4()
	rs.Insert(1)
	rs.Remove(2)
	rs.Insert(2)
	r := rs.GetRandom()
	fmt.Printf("%d", r)
}
