package design

import "math/rand"

type RandomizedSet struct {
	arr []int
	mp  map[int]int
}

func Constructor4() RandomizedSet {
	return RandomizedSet{make([]int, 0), make(map[int]int)}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.mp[val]; ok {
		return false
	}
	this.arr = append(this.arr, val)
	this.mp[val] = len(this.arr) - 1
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	idx, ok := this.mp[val]
	if !ok {
		return false
	}
	n := len(this.arr)
	if idx != n-1 {
		//swap
		this.mp[this.arr[n-1]] = idx
		tmp := this.arr[n-1]
		this.arr[n-1] = this.arr[idx]
		this.arr[idx] = tmp
	}
	this.arr = this.arr[0 : n-1]
	delete(this.mp, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	i := rand.Intn(len(this.arr))
	return this.arr[i]
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */
