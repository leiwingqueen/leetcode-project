#include <iostream>
#include "vector"

using namespace std;

struct TestObj {
    int value;

    TestObj(int value) : value(value) {}
};

enum class CoordinateArea {
    First,
    Second,
    Third,
};

int main() {
    //https://www.thegeekstuff.com/2016/02/c-plus-plus-11/
    //按照这里进行测试
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
    cout << "====================================" << endl;
    //枚举测试
    //https://www.tutorialspoint.com/Scope-resolution-operator-in-Cplusplus，范围作用域解决方案
    CoordinateArea area = CoordinateArea::First;
    cout << (area == CoordinateArea::First) << endl;
    //lambda expression
    cout << "====================================" << endl;
    double a = 1;
    double b = 1;
    auto dUpperPart = [](double dX, double dY) -> double { return dX * dX + dY * dY; };
    double r = dUpperPart(a, b);
    cout << r << endl;
    cout << "====================================" << endl;
    int r2 = [](int a) -> int { return a + 1; }(2);
    cout << r2 << endl;
    cout << "====================================" << endl;
    vector<int> iVector;
    for (int i = 0; i < 10; ++i) {
        iVector.push_back(i);
    }
    for_each(begin(iVector), end(iVector), [](int n) { if (n % 2 == 0)cout << n << endl; });
    cout << "====================================" << endl;
    //static assertion
    cout << sizeof(long int) << endl;
    int c;
    static_assert(sizeof(c) <= 8, "This is unexpected");

    return 0;
}
