package externelMergeSort

import (
	"fmt"
	"testing"
)

func Test_mergeSort(t *testing.T) {
	type args struct {
		file File
	}
	tests := []struct {
		name string
		args args
	}{
		// TODO: Add test cases.
		{"t1", args{File{[]int{1, 2, 3}}}},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			mergeSort(tt.args.file)
		})
	}
}

func Test_t2(t *testing.T) {
	n := 500
	file := File{}
	for i := n; i > 0; i-- {
		file.data = append(file.data, i)
	}
	sortFile := mergeSort(file)
	for _, row := range sortFile.data {
		fmt.Println(row)
	}
}
