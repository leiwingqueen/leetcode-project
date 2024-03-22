package bwc126

func sumOfEncryptedInt(nums []int) int {
	encrypt := func(num int) int {
		mx := 0
		size := 0
		for num > 0 {
			i := num % 10
			if i > mx {
				mx = i
			}
			num /= 10
			size++
		}
		res := 0
		for j := 0; j < size; j++ {
			res = res*10 + mx
		}
		return res
	}
	res := 0
	for _, num := range nums {
		res += encrypt(num)
	}
	return res
}
