//
// Created by 李泳权 on 2022/6/26.
//

#ifndef CPP_LRUCACHE_H
#define CPP_LRUCACHE_H


class LRUCache {
public:
    LRUCache(int capacity);

    virtual ~LRUCache();

    virtual int get(int key);

    virtual void set(int key, int value);
};


#endif //CPP_LRUCACHE_H
