//
// Created by leiwingqueen on 2022/7/9.
//
#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    int lenLongestFibSubseq(vector<int> &arr) {
        unordered_map<int, int> indexMap;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            indexMap[arr[i]] = i;
        }
        vector<vector<int>> dp(n - 1, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            dp[0][i] = 2;
        }
        int mx = 2;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = 2;
                int sub = arr[j] - arr[i];
                if (indexMap.count(sub) > 0 && indexMap[sub] < i) {
                    dp[i][j] = dp[indexMap[sub]][i] + 1;
                }
                if (dp[i][j] > mx) {
                    mx = dp[i][j];
                }
            }
        }
        return mx > 2 ? mx : 0;
    }
};

