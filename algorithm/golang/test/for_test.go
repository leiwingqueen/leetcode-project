package test

import (
	"fmt"
	"testing"
)

type TestItem struct {
	Name string
	Age  int
}

func forTest() {
	var arr []TestItem
	arr = append(arr, TestItem{"a", 1})
	arr = append(arr, TestItem{"b", 2})
	arr = append(arr, TestItem{"c", 3})
	mp := make(map[string]*TestItem)
	for _, v := range arr {
		tmp := v
		mp[v.Name] = &tmp
	}
	for k, v := range mp {
		fmt.Println(fmt.Sprintf("Name:%s,value:%v", k, v))
	}
}

func Test_t1(t *testing.T) {
	forTest()
}
