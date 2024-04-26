package SnapshotArray

import "sort"

// 实现支持下列接口的「快照数组」- SnapshotArray：
//
//SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
//void set(index, val) - 会将指定索引 index 处的元素设置为 val。
//int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
//int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
//
//
//示例：
//
//输入：["SnapshotArray","set","snap","set","get"]
//     [[3],[0,5],[],[0,6],[0,0]]
//输出：[null,null,0,null,5]
//解释：
//SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
//snapshotArr.set(0,5);  // 令 array[0] = 5
//snapshotArr.snap();  // 获取快照，返回 snap_id = 0
//snapshotArr.set(0,6);
//snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
//
//
//提示：
//
//1 <= length <= 50000
//题目最多进行50000 次set，snap，和 get的调用 。
//0 <= index < length
//0 <= snap_id < 我们调用 snap() 的总次数
//0 <= val <= 10^9

// 类似mvcc
type Data struct {
	version int
	value   int
}
type SnapshotArray struct {
	currentVer int
	arr        [][]*Data
}

func Constructor(length int) SnapshotArray {
	arr := make([][]*Data, length)
	return SnapshotArray{0, arr}
}

func (this *SnapshotArray) Set(index int, val int) {
	this.arr[index] = append(this.arr[index], &Data{this.currentVer, val})
}

func (this *SnapshotArray) Snap() int {
	this.currentVer++
	return this.currentVer - 1
}

func (this *SnapshotArray) Get(index int, snap_id int) int {
	data := this.arr[index]
	if data == nil || len(data) == 0 {
		return 0
	}
	idx := sort.Search(len(data), func(i int) bool {
		return data[i].version > snap_id
	})
	if idx == 0 {
		return 0
	} else {
		return data[idx-1].value
	}
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * obj := Constructor(length);
 * obj.Set(index,val);
 * param_2 := obj.Snap();
 * param_3 := obj.Get(index,snap_id);
 */
