package test

import (
	"fmt"
	"testing"
	"time"
)

func TestSearchInsert(t *testing.T) {
	go func() {
		for true {
			fmt.Printf("hello\n")
			time.Sleep(time.Duration(2) * time.Second)
		}
	}()
	time.Sleep(10 * time.Second)
}
