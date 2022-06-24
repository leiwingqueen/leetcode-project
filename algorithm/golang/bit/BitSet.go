package bit

//typedef struct BitSet
//{
//	char* _bits;
//	size_t _N;//位个数
//}BitSet;
//void BitSetInit(BitSet* pbs, size_t n);//初始化位图
//void BitSetDestroy(BitSet* pbs);//销毁位图
//void BitSetSet(BitSet* pbs, size_t x);//
//void BitSetReset(BitSet* pbs, size_t x);//
//size_t BitSetTest(BitSet* pbs, size_t x);//

type BitSet struct {
	_bits []byte
	_size int
}

func build(x int) *BitSet {
	n := x / 8
	if x%8 > 0 {
		n++
	}
	return &BitSet{_bits: make([]byte, 0, n), _size: x}
}

func (bit *BitSet) set(x int) {
	k := x / 8
	i := x % 8
	bit._bits[k] |= 1 << i
}

func (bit *BitSet) reset(x int) {
	k := x / 8
	i := x % 8
	mask := 0xFF ^ (1 << i)
	bit._bits[k] &= mask
}

func (bit *BitSet) test(x int) int {
	k := x / 8
	i := x % 8
	if bit._bits[k]&(1<<i) != 0 {
		return 1
	} else {
		return 0
	}
}
