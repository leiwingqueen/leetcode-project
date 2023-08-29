package test

import (
	"fmt"
	"github.com/google/uuid"
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

func TestUuid(t *testing.T) {
	s := uuid.New().String()
	fmt.Printf("%s\n", s)
}
