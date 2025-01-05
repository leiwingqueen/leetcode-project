package ATM

type ATM struct {
	counter []int
}

func Constructor() ATM {
	return ATM{make([]int, 5)}
}

func (this *ATM) Deposit(banknotesCount []int) {
	for i, c := range banknotesCount {
		this.counter[i] += c
	}
}

func (this *ATM) Withdraw(amount int) []int {
	ticket := []int{20, 50, 100, 200, 500}
	arr := make([]int, 5)
	for i := 4; i >= 0; i-- {
		if amount <= 0 {
			break
		}
		if this.counter[i] > 0 && ticket[i] <= amount {
			c := min(amount/ticket[i], this.counter[i])
			arr[i] += c
			amount -= c * ticket[i]
		}
	}
	if amount > 0 {
		return []int{-1}
	} else {
		// 修改剩余的数量
		for i := 0; i < 5; i++ {
			this.counter[i] -= arr[i]
		}
		return arr
	}
}

/**
 * Your ATM object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Deposit(banknotesCount);
 * param_2 := obj.Withdraw(amount);
 */
