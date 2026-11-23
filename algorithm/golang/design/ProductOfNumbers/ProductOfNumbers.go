package ProductOfNumbers

const DefaultSize = 40_000

type ProductOfNumbers struct {
	arr []int
}

func Constructor() ProductOfNumbers {
	return ProductOfNumbers{arr: make([]int, 0, DefaultSize)}
}

func (this *ProductOfNumbers) Add(num int) {
	this.arr = append(this.arr, num)
}

func (this *ProductOfNumbers) GetProduct(k int) int {
	res := 1
	for i := 0; i < k; i++ {
		res *= this.arr[len(this.arr)-i-1]
	}
	return res
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(num);
 * param_2 := obj.GetProduct(k);
 */
