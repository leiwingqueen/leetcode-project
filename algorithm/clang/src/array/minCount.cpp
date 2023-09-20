//
// Created by leiwingqueen on 2023/9/20.
//



int minCount(int *coins, int coinsSize) {
    int res = 0;
    for (int i = 0; i < coinsSize; ++i) {
        res += coins[i] / 2 + coins[i] % 2;
    }
    return res;
}

