package main

import (
	"fmt"
	"sync"
	"time"
)

func writeLetter() {
	write := func(b byte) {
		fmt.Println(string(b))
	}
	// fmt.Println("A")
	for {
		var wg sync.WaitGroup
		wg.Add(4)
		ch := make(chan byte, 4)
		ch <- 'A'
		for b := range ch {
			go func() {
				defer wg.Done()
				write(b)
				if b < 'D' {
					ch <- b + 1
				}
			}()
		}
		close(ch)
		wg.Wait()
		time.Sleep(1 * time.Second)
	}
}

func printLetter(ch chan byte) {
	b := <-ch
	fmt.Println(string(b))
	if b < 'D' {
		ch <- b + 1
	}
}

func main() {
	ch := make(chan byte, 4)
	go func() {
		for {
			select {
			case letter, ok := <-ch:
				if !ok {
					return
				}
				go func() {
					fmt.Println(string(letter))
					if letter < 'D' {
						ch <- letter + 1
					}
				}()
			}
		}
	}()
	ch <- 'A'
	time.Sleep(100 * time.Second)
}
