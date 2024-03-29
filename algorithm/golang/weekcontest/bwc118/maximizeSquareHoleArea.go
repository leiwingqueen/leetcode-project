package bwc118

import "sort"

// 给你一个网格图，由 n + 2 条 横线段 和 m + 2 条 竖线段 组成，一开始所有区域均为 1 x 1 的单元格。
//
//所有线段的编号从 1 开始。
//
//给你两个整数 n 和 m 。
//
//同时给你两个整数数组 hBars 和 vBars 。
//
//hBars 包含区间 [2, n + 1] 内 互不相同 的横线段编号。
//vBars 包含 [2, m + 1] 内 互不相同的 竖线段编号。
//如果满足以下条件之一，你可以 移除 两个数组中的部分线段：
//
//如果移除的是横线段，它必须是 hBars 中的值。
//如果移除的是竖线段，它必须是 vBars 中的值。
//请你返回移除一些线段后（可能不移除任何线段），剩余网格图中 最大正方形 空洞的面积，正方形空洞的意思是正方形 内部 不含有任何线段。

// 这写起来好绕
func maximizeSquareHoleArea(n int, m int, hBars []int, vBars []int) int {
	sort.Ints(hBars)
	sort.Ints(vBars)
	l, r := hBars[0]-1, 0
	rMax := 1
	for r < len(hBars) {
		if r == 0 || hBars[r]-hBars[r-1] <= 1 || hBars[r]-l <= 1 {
			r++
		} else {
			// [l,r)为连续的空间,h[r-1]-h[l]+1为空间大小
			row := hBars[r-1] + 1 - l
			if row > rMax {
				rMax = row
			}
			l = hBars[r] - 1
		}
	}
	// 右边界
	row := hBars[r-1] + 1 - l
	if row > rMax {
		rMax = row
	}
	cMax := 1
	l, r = vBars[0]-1, 0
	for r < len(vBars) {
		if r == 0 || vBars[r]-vBars[r-1] <= 1 || vBars[r]-l <= 1 {
			r++
		} else {
			col := vBars[r-1] - l + 1
			if col > cMax {
				cMax = col
			}
			l = vBars[r] - 1
		}
	}
	col := vBars[r-1] - l + 1
	if col > cMax {
		cMax = col
	}
	if rMax < cMax {
		return rMax * rMax
	} else {
		return cMax * cMax
	}
}
