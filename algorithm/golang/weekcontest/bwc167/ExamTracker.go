package bwc167

import (
	"sort"
)

// 前缀和
type pair struct {
	time  int
	score int
}
type ExamTracker struct {
	totalScore int64
	records    []pair
	prefixSum  []int64
}

func Constructor() ExamTracker {
	return ExamTracker{prefixSum: []int64{0}}
}

func (this *ExamTracker) Record(time int, score int) {
	this.totalScore += int64(score)
	this.records = append(this.records, pair{time: time, score: score})
	this.prefixSum = append(this.prefixSum, this.totalScore)
}

func (this *ExamTracker) TotalScore(startTime int, endTime int) int64 {
	endIdx := sort.Search(len(this.records), func(i int) bool {
		return this.records[i].time > endTime
	})
	startIdx := sort.Search(len(this.records), func(i int) bool {
		return this.records[i].time >= startTime
	})
	return this.prefixSum[endIdx] - this.prefixSum[startIdx]
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Record(time,score);
 * param_2 := obj.TotalScore(startTime,endTime);
 */
