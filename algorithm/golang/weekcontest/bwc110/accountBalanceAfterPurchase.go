package bwc110

func accountBalanceAfterPurchase(purchaseAmount int) int {
	mod := purchaseAmount % 10
	if mod == 0 {
		return 100 - purchaseAmount
	} else {
		upper := purchaseAmount + 10 - mod
		lower := purchaseAmount - mod
		if upper-purchaseAmount <= purchaseAmount-lower {
			return 100 - upper
		} else {
			return 100 - lower
		}
	}
}
