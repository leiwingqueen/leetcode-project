//
// Created by 李泳权 on 2022/6/30.
//

int numPrimeArrangements(int n) {
    auto isPrime = [](int num) -> bool {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i < num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    };
    int mod = 1000000007;
    int k = 0;
    for (int i = 2; i <= n; ++i) {
        if (isPrime(i)) {
            k++;
        }
    }
    int p = n - k;
    int res = 1;
    while (k > 1) {
        res = (res * (long) k) % mod;
        k--;
    }
    while (p > 1) {
        res = (res * (long) p) % mod;
        p--;
    }
    return res;
}
