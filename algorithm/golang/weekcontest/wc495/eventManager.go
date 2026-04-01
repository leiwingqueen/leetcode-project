package wc495

import "container/heap"

type EventManager struct {
	eventVersion map[int]int
	h            *hp
}

func Constructor(events [][]int) EventManager {
	n := len(events)
	eventVersion := make(map[int]int, n)
	h := make(hp, n)
	for i := range events {
		id, priority := events[i][0], events[i][1]
		e := event{priority, id, 0}
		eventVersion[id] = 0
		h[i] = e
	}
	heap.Init(&h)
	return EventManager{eventVersion, &h}
}

func (this *EventManager) UpdatePriority(eventId int, newPriority int) {
	this.eventVersion[eventId]++
	heap.Push(this.h, event{newPriority, eventId, this.eventVersion[eventId]})
}

func (this *EventManager) PollHighest() int {
	for this.h.Len() > 0 {
		e := heap.Pop(this.h).(event)
		if e.version == this.eventVersion[e.id] {
			return e.id
		}
	}
	return -1
}

/**
 * Your EventManager object will be instantiated and called as such:
 * obj := Constructor(events);
 * obj.UpdatePriority(eventId,newPriority);
 * param_2 := obj.PollHighest();
 */

type event struct{ priority, id, version int }
type hp []event

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	return h[i].priority > h[j].priority || h[i].priority == h[j].priority && h[i].id < h[j].id
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(event)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
