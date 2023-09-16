//
// Created by leiwingqueen on 2023/9/16.
//

#include "TreeNode.h"

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int dfs(struct TreeNode *node, char parent, char grand) {
    if (node == 0) {
        return 0;
    }
    int res = 0;
    if (grand == 1) {
        res += node->val;
    }
    res += dfs(node->left, node->val % 2 == 0, parent);
    res += dfs(node->right, node->val % 2 == 0, parent);
    return res;
}


int sumEvenGrandparent(struct TreeNode *root) {
    return dfs(root, 0, 0);
}

