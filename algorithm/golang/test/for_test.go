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
	var arr2 []TestItem
	for _, v := range arr {
		mp[v.Name] = &v
		arr2 = append(arr2, v)
	}
	for k, v := range mp {
		fmt.Println(fmt.Sprintf("Name:%s,value:%v", k, v))
	}
	for _, v := range arr2 {
		fmt.Println(v)
	}
}

func Test_t1(t *testing.T) {
	forTest()
}

func Test_t2(t *testing.T) {
	var s []byte
	s = append(s, "Hello,world"...)
	fmt.Println(string(s))
	s = s[:0]
	fmt.Println(string(s))
	s = append(s, "Hello,lyq"...)
	fmt.Println(string(s))
}
