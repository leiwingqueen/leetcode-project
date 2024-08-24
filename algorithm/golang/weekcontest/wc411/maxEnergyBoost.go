package wc411

// f0(n)为最后一个选A的最大值,f1(n)为最后一个选B的最大值
// f0(n)=max{f1(n-1),f0(n-1)+a[n-1]}
func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	dp0, dp1 := make([]int64, n), make([]int64, n)
	dp0[0] = int64(energyDrinkA[0])
	dp1[0] = int64(energyDrinkB[0])
	for i := 1; i < n; i++ {
		dp0[i] = max(dp1[i-1], dp0[i-1]+int64(energyDrinkA[i]))
		dp1[i] = max(dp0[i-1], dp1[i-1]+int64(energyDrinkB[i]))
	}
	return max(dp0[n-1], dp1[n-1])
}
