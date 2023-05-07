package wc344

type FrequencyTracker struct {
	mp1 map[int]int
	mp2 map[int]int
}

func Constructor() FrequencyTracker {
	return FrequencyTracker{make(map[int]int), make(map[int]int)}
}

func (this *FrequencyTracker) Add(number int) {
	old := this.mp1[number]
	this.mp1[number]++
	this.mp2[old+1]++
	if old > 0 {
		this.mp2[old]--
	}
}

func (this *FrequencyTracker) DeleteOne(number int) {
	old := this.mp1[number]
	if old > 0 {
		this.mp1[number]--
		this.mp2[old]--
		if old-1 > 0 {
			this.mp2[old-1]++
		}
	}
}

func (this *FrequencyTracker) HasFrequency(frequency int) bool {
	return this.mp2[frequency] > 0
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * obj.DeleteOne(number);
 * param_3 := obj.HasFrequency(frequency);
 */
