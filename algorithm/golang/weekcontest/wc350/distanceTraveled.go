package wc350

func distanceTraveled(mainTank int, additionalTank int) int {
	res := 0
	for mainTank > 0 {
		if mainTank >= 5 {
			res += 50
			mainTank -= 5
			if additionalTank > 0 {
				additionalTank--
				mainTank++
			}
		} else {
			res += mainTank * 10
			mainTank = 0
		}
	}
	return res
}
