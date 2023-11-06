//
// Created by leiwingqueen on 2023/11/6.
//

// Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
//
//
//
//Example 1:
//
//Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//Output: 16
//Explanation: The two words can be "abcw", "xtfn".
//Example 2:
//
//Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
//Output: 4
//Explanation: The two words can be "ab", "cd".
//Example 3:
//
//Input: words = ["a","aa","aaa","aaaa"]
//Output: 0
//Explanation: No such pair of words.
//
//
//Constraints:
//
//2 <= words.length <= 1000
//1 <= words[i].length <= 1000
//words[i] consists only of lowercase English letters.

#include "string.h"
#include "math.h"

int maxProduct(char **words, int wordsSize) {
    int masks[wordsSize];
    memset(masks, 0, sizeof(int) * wordsSize);
    // 等价于
    /*for (int i = 0; i < wordsSize; ++i) {
        masks[i] = 0;
    }*/
    for (int i = 0; i < wordsSize; ++i) {
        char *word = words[i];
        int l = strlen(word);
        int mask = 0;
        for (int j = 0; j < l; ++j) {
            int offset = word[j] - 'a';
            mask |= 1 << offset;
        }
        masks[i] = mask;
    }
    int res = 0;
    for (int i = 1; i < wordsSize - 1; ++i) {
        for (int j = i + 1; j < wordsSize; ++j) {
            if ((masks[i] & masks[j]) == 0) {
                int s = strlen(words[i]) * strlen(words[j]);
                res = res > s ? res : s;
            }
        }
    }
    return res;
}

int main() {

}



