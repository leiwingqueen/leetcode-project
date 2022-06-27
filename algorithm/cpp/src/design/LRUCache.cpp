//
// Created by 李泳权 on 2022/6/27.
//

//请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
//实现 LRUCache 类：
//LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
//int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
//void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
//函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
//
// 
//
//示例：
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
//提示：
//
//1 <= capacity <= 3000
//0 <= key <= 10000
//0 <= value <= 105
//最多调用 2 * 105 次 get 和 put
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/lru-cache
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

#include <unordered_map>

using namespace std;

struct LRUNode {
    int key;
    int value;
    LRUNode *pre;
    LRUNode *next;

    LRUNode() {
        this->key = 0;
        this->value = 0;
    }

    LRUNode(int _key, int _value) {
        this->key = _key;
        this->value = _value;
    }
};

class LRUCache {
public:
    LRUCache(int capacity) {
        this->capacity = capacity;
        //dummy node
        this->head = new LRUNode();
        this->tail = new LRUNode();
        this->head->next = tail;
        this->tail->pre = head;
    }

    int get(int key) {
        if (!this->map.count(key)) {
            return -1;
        }
        LRUNode *node = this->map[key];
        remove(node);
        append(node);
        return node->value;
    }

    void put(int key, int value) {
        if (!this->map.count(key)) {
            if (this->map.size() == this->capacity) {
                //淘汰旧的key
                LRUNode *delNode = this->tail->pre;
                remove(delNode);
                this->map.erase(delNode->key);
                //内存释放
                delete delNode;
            }
            LRUNode *node = new LRUNode(key, value);
            append(node);
            this->map[key] = node;
        } else {
            //移动到头部
            LRUNode *node = map[key];
            node->value = value;
            remove(node);
            append(node);
        }
    }

    void remove(LRUNode *node) {
        LRUNode *pre = node->pre;
        LRUNode *next = node->next;
        pre->next = next;
        next->pre = pre;
        //delete node;
    }

    void append(LRUNode *node) {
        LRUNode *next = this->head->next;
        this->head->next = node;
        node->pre = this->head;
        node->next = next;
        next->pre = node;
    }

private:
    int capacity;
    unordered_map<int, LRUNode *> map;
    LRUNode *head;
    LRUNode *tail;
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */

