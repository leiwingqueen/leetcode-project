//
// Created by leiwingqueen on 2023/12/1.
//

long mult2(long, long);

void multstore(long x, long y, long *dest) {
    long t = mult2(x, y);
    *dest = t;
}

