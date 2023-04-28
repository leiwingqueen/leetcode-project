package DinnerPlates

import (
	"fmt"
	"testing"
)

func Test(t *testing.T) {
	plates := Constructor(2)
	plates.Push(1)
	plates.Push(2)
	plates.Push(3)
	plates.Push(4)
	plates.Push(5)

	p := plates.PopAtStack(0)
	fmt.Println(p)

	plates.Push(20)
	plates.Push(21)

	p = plates.PopAtStack(0)
	fmt.Println(p)

	p = plates.PopAtStack(2)
	fmt.Println(p)

}
