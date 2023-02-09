package array

import (
	"fmt"
	"math/rand"
)

func main() {
	// 库存数量
	arr := []int{1, 3, 3, 3, 3}
	// 对应价值
	price := []int{1, 1, 1, 1, 1}
	// 期望roi
	expectRoi := 0.1

	cnt := check(arr, price, expectRoi)
	fmt.Println(cnt)
}

// 返回玩家抽奖次数
func check(arr []int, price []int, expectRoi float64) int {
	roiIn := 0
	roiOut := 0
	cnt := 0
	for {
		// 轮盘赌随机
		sum := 0
		for _, num := range arr {
			sum += num
		}
		r := rand.Intn(sum)
		s := 0
		idx := 0
		for i, num := range arr {
			s += num
			if r < s {
				idx = i
				break
			}
		}
		// 更新roi和库存
		roiIn += 1
		roiOut += price[idx]
		cnt++
		if float64(roiOut)/float64(roiIn) < expectRoi {
			break
		}
	}
	return cnt
}
