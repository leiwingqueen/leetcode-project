//
// Created by leiwingqueen on 2022/9/8.
//

#include "vector"

using namespace std;

class Solution {
public:
    vector<int> constructArray(int n, int k) {
        vector<int> res;
        for (int i = 1; i < n - k; ++i) {
            res.push_back(i);
        }
        int i = n - k;
        int p1 = n - k;
        int p2 = n;
        while (i <= n) {
            res.push_back(p1++);
            i++;
            if (i > n) {
                break;
            }
            res.push_back(p2--);
            i++;
        }
        return res;
    }
};
