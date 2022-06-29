#include <iostream>

using namespace std;

struct TestObj {
    int value;

    TestObj(int value) : value(value) {}
};

int main() {
    cout << "Hello, World!" << endl;
    for (int i = 0; i < 10; ++i) {
        cout << i << endl;
    }
    //for 循环测试
    cout << "====================================" << endl;
    int arr[] = {1, 2, 3};
    for (const auto &item: arr) {
        cout << item << endl;
    }
    cout << "====================================" << endl;
    for (int i: arr) {
        cout << i << endl;
        i = 3;
    }
    cout << "====================================" << endl;
    for (int &i: arr) {
        cout << i << endl;
        i = 3;
    }
    cout << "====================================" << endl;
    for (int i: arr) {
        cout << i << endl;
    }
    cout << "====================================" << endl;
    TestObj *arr2[] = {new TestObj(1), new TestObj(2)};
    for (TestObj *obj: arr2) {
        cout << obj->value << endl;
    }
    return 0;
}
