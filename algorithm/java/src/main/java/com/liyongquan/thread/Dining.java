package com.liyongquan.thread;

/**
 * 哲学家就餐问题
 */
public class Dining {
    public static final int N = 5;

    // status define
    public static final int THINKING = 0;
    public static final int HUNGRY = 1;
    public static final int EATING = 2;

    // state of each philosopher
    public static final int[] state = new int[N];

    // global mutex
    public static Object mutex = new Object();
    // mutex for each philosopher
    public static Object[] s = new Object[N];

    // id of the philosopher
    private int id;

    public Dining(int id) {
        this.id = id;
    }

    public void start() {
        while (true) {
            think();
            takeForks();
            eat();
            putForks();
        }
    }

    public void think() {
        System.out.println(String.format("哲学家%d开始思考", id));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        System.out.println(String.format("哲学家%d开始吃饭", id));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeForks() {
        synchronized (mutex) {
            state[id] = HUNGRY;
            test(id);
        }
        synchronized (s[id]) {
            if (state[id] != EATING) {
                try {
                    s[id].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void putForks() {
        synchronized (mutex) {
            state[id] = THINKING;
            test((id + N - 1) % N);
            test((id + 1) % N);
        }
    }

    private void test(int i) {
        if (state[i] == HUNGRY && state[(i + N - 1) % N] != EATING && state[(i + 1) % N] != EATING) {
            state[i] = EATING;
            synchronized (s[i]) {
                s[i].notify();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            s[i] = new Object();
        }
        for (int i = 0; i < N; i++) {
            final int id = i;
            new Thread(() -> {
                Dining dining = new Dining(id);
                dining.start();
            }).start();
        }
    }
}
