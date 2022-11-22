//
// Created by leiwingqueen on 2022/11/21.
//

// 又写一遍，加深下记忆

#include <unordered_map>

using namespace std;

struct LRUNode {
    int key;
    int value;
    LRUNode *pre;
    LRUNode *next;
};

class LRUCache {
public:
    LRUCache(int capacity) {
        this->size = 0;
        this->capacity = capacity;
        this->head = new LRUNode();
        this->tail = new LRUNode();
        this->head->next = this->tail;
        this->tail->pre = this->head;
    }

    int get(int key) {
        if (map.count(key) == 0) {
            return -1;
        }
        LRUNode *node = map[key];
        this->remove(node);
        this->append(node);
        return node->value;
    }

    void put(int key, int value) {
        if (map.count(key) == 0) {
            LRUNode *node = new LRUNode();
            node->key = key;
            node->value = value;
            this->append(node);
            map[key] = node;
            if (size > capacity) {
                LRUNode *rmNode = this->head->next;
                map.erase(rmNode->key);
                this->remove(rmNode);
            }
        } else {
            LRUNode *node = map[key];
            node->value = value;
            this->remove(node);
            this->append(node);
        }
    }

private:
    LRUNode *head;
    LRUNode *tail;
    unordered_map<int, LRUNode *> map;
    int capacity;
    int size;

    void remove(LRUNode *node) {
        LRUNode *pre = node->pre;
        LRUNode *next = node->next;
        pre->next = next;
        next->pre = pre;
        this->size--;
    }

    void append(LRUNode *node) {
        LRUNode *pre = this->tail->pre;
        pre->next = node;
        node->pre = pre;
        node->next = this->tail;
        this->tail->pre = node;
        this->size++;
    }
};

