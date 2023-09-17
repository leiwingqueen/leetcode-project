//
// Created by leiwingqueen on 2023/9/17.
//
#include "stdio.h"
#include "stdlib.h"

char check(int *nums, int n, int k) {
    int l = 0, r = n - 1;
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] > k) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    if (nums[l] <= k) {
        l = n;
    }
    char res = l == k && (l == 0 || nums[l - 1] != k);
    // printf("k:%d,idx:%d,res:%d\n", k, l, res);
    return res;
}

int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;//升序
}

int countWays(int *nums, int numsSize) {
    int res = 0;
    qsort(nums, numsSize, sizeof(int), cmp);
    for (int i = 0; i <= numsSize; ++i) {
        if (check(nums, numsSize, i)) {
            res++;
        }
    }
    return res;
}

