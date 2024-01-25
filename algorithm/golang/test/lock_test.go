package test

import (
	"fmt"
	"sync"
	"testing"
)

// 锁测试
func Test_lock_t1(t *testing.T) {
	var mutex sync.RWMutex
	fmt.Println("read lock start")
	mutex.RLock()
	fmt.Println("read lock is locked")
	mutex.Lock()
	fmt.Println("write lock is locked")
	mutex.Unlock()
	fmt.Println("write lock is unlocked")
	mutex.RUnlock()
	fmt.Println("read lock is unlocked")
}
