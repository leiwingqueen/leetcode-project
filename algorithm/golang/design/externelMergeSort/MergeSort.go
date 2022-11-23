package externelMergeSort

import "sort"

// 模拟k路归并的文件排序

type File struct {
	data []int
}

var M = 10

func mergeSort(file File) File {
	var divide func(file File) []File
	var merge func(files []File, k int) File
	divide = func(file File) []File {
		if len(file.data) <= M {
			return []File{file}
		}
		idx := 0
		res := make([]File, 0)
		for idx < len(file.data) {
			r := idx + M
			if len(file.data) < r {
				r = len(file.data)
			}
			res = append(res, File{file.data[idx:r]})
			idx += M
		}
		return res
	}
	merge = func(files []File, k int) File {
		if len(files) == 1 {
			return files[0]
		}
		idx := 0
		if len(files) < k {
			k = len(files)
		}
		fileRes := make([]File, 0)
		for idx < len(files) {
			res := File{}
			rIdx := idx + k
			if len(files) < rIdx {
				rIdx = len(files)
			}
			p := make([]int, k)
			fileMerge := files[idx:rIdx]
			for {
				selectFileIdx := -1
				for i, f := range fileMerge {
					offset := p[i]
					if offset < len(f.data) && (selectFileIdx < 0 || f.data[offset] < fileMerge[selectFileIdx].data[p[selectFileIdx]]) {
						selectFileIdx = i
					}
				}
				if selectFileIdx < 0 {
					break
				}
				res.data = append(res.data, fileMerge[selectFileIdx].data[p[selectFileIdx]])
				p[selectFileIdx]++
			}
			fileRes = append(fileRes, res)
			idx = rIdx
		}
		return merge(fileRes, k)
	}
	// 分成k个文件
	files := divide(file)
	// 再对文件进行排序
	for _, f := range files {
		sort.Slice(f.data, func(i, j int) bool {
			return f.data[i] < f.data[j]
		})
	}
	// 再进行归并
	res := merge(files, M)
	return res
}
