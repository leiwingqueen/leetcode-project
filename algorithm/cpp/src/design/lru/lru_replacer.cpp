#include "unordered_map"
#include "lru_replacer.h"

void remove(LRUNode *node) {
    LRUNode *next = node->next;
    LRUNode *pre = node->pre;
    pre->next = next;
    next->pre = pre;
    node->pre = NULL;
    node->next = NULL;
}

void insert(LRUNode *head, LRUNode *node) {
    LRUNode *next = head->next;
    node->pre = head;
    head->next = node;
    next->pre = node;
    node->next = next;
}

LRUReplacer::LRUReplacer(size_t num_pages) {
    this->capacity = num_pages;
    // dummy node
    this->head = new LRUNode(-1);
    this->tail = new LRUNode(-1);
    this->head->next = tail;
    this->tail->pre = head;
}

// LRUReplacer::~LRUReplacer() = default;
LRUReplacer::~LRUReplacer() {
    LRUNode *node = this->head;
    LRUNode *pre = NULL;
    while (node->next != NULL) {
        pre = node;
        node = node->next;
        delete pre;
    }
    delete node;
}

bool LRUReplacer::Victim(int frame_id) {
    std::unordered_map<int, LRUNode *>::iterator it;
    it = this->mp.find(frame_id);
    if (it != mp.end()) {
    }
}

void LRUReplacer::Pin(int frame_id) {
    // delete operation
    std::unordered_map<int, LRUNode *>::iterator it;
    it = mp.find(frame_id);
    if (it == mp.end()) {
        return;
    }
    LRUNode *node = it->second;
    mp.erase(frame_id);
}

void LRUReplacer::Unpin(int frame_id) {
    // insert operation
    std::map<int, LRUNode *>::iterator it;
    it = mp.find(frame_id);
    if (it == mp.end()) {
        // not exist
        LRUNode *node = new LRUNode(frame_id);
        mp.insert_or_assign(frame_id, node);
        insert(this->head, node);
    } else {
        LRUNode *node = it->second;
        remove(node);
        insert(this->head, node);
    }
}

int LRUReplacer::Size() { return mp.size(); }
