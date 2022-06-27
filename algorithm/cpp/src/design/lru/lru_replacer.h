#include <list>
#include <mutex>  // NOLINT
#include <vector>

#include <map>
#include "replacer.h"

class LRUNode {
public:
    LRUNode(int frame_id) { this->frame_id = frame_id; }

    ~LRUNode() {
        pre = NULL;
        next = NULL;
        delete pre;
        delete next;
    }

    int frame_id;
    LRUNode *pre;
    LRUNode *next;
};

/**
 * LRUReplacer implements the Least Recently Used replacement policy.
 */
class LRUReplacer : public Replacer {
public:
    /**
     * Create a new LRUReplacer.
     * @param num_pages the maximum number of pages the LRUReplacer will be required to store
     */
    explicit LRUReplacer(size_t num_pages);

    /**
     * Destroys the LRUReplacer.
     */
    ~LRUReplacer() override;

    bool Victim(int frame_id) override;

    void Pin(int frame_id) override;

    void Unpin(int frame_id) override;

    int Size() override;

private:
    std::map<int, LRUNode *> mp;
    int capacity;
    LRUNode *head;
    LRUNode *tail;
};
