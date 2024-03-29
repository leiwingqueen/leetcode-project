package com.liyongquan.array;

import java.util.List;
import java.util.Stack;

//有一个 单线程 CPU 正在运行一个含有 n 道函数的程序。每道函数都有一个位于  0 和 n-1 之间的唯一标识符。
//
//函数调用 存储在一个 调用栈 上 ：当一个函数调用开始时，它的标识符将会推入栈中。而当一个函数调用结束时，它的标识符将会从栈中弹出。标识符位于栈顶的函数是 当前正在执行的函数 。每当一个函数开始或者结束时，将会记录一条日志，包括函数标识符、是开始还是结束、以及相应的时间戳。
//
//给你一个由日志组成的列表 logs ，其中 logs[i] 表示第 i 条日志消息，该消息是一个按 "{function_id}:{"start" | "end"}:{timestamp}" 进行格式化的字符串。例如，"0:start:3" 意味着标识符为 0 的函数调用在时间戳 3 的 起始开始执行 ；而 "1:end:2" 意味着标识符为 1 的函数调用在时间戳 2 的 末尾结束执行。注意，函数可以 调用多次，可能存在递归调用 。
//
//函数的 独占时间 定义是在这个函数在程序所有函数调用中执行时间的总和，调用其他函数花费的时间不算该函数的独占时间。例如，如果一个函数被调用两次，一次调用执行 2 单位时间，另一次调用执行 1 单位时间，那么该函数的 独占时间 为 2 + 1 = 3 。
//
//以数组形式返回每个函数的 独占时间 ，其中第 i 个下标对应的值表示标识符 i 的函数的独占时间。
//
// 
//示例 1：
//
//
//输入：n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
//输出：[3,4]
//解释：
//函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，于时间戳 1 的末尾结束执行。
//函数 1 在时间戳 2 的起始开始执行，执行 4 个单位时间，于时间戳 5 的末尾结束执行。
//函数 0 在时间戳 6 的开始恢复执行，执行 1 个单位时间。
//所以函数 0 总共执行 2 + 1 = 3 个单位时间，函数 1 总共执行 4 个单位时间。
//示例 2：
//
//输入：n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
//输出：[8]
//解释：
//函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
//函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
//函数 0（初始调用）恢复执行，并立刻再次调用它自身。
//函数 0（第二次递归调用）在时间戳 6 的起始开始执行，执行 1 个单位时间。
//函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间。
//所以函数 0 总共执行 2 + 4 + 1 + 1 = 8 个单位时间。
//示例 3：
//
//输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
//输出：[7,1]
//解释：
//函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
//函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
//函数 0（初始调用）恢复执行，并立刻调用函数 1 。
//函数 1在时间戳 6 的起始开始执行，执行 1 个单位时间，于时间戳 6 的末尾结束执行。
//函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间，于时间戳 7 的末尾结束执行。
//所以函数 0 总共执行 2 + 4 + 1 = 7 个单位时间，函数 1 总共执行 1 个单位时间。
//示例 4：
//
//输入：n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
//输出：[8,1]
//示例 5：
//
//输入：n = 1, logs = ["0:start:0","0:end:0"]
//输出：[1]
// 
//
//提示：
//
//1 <= n <= 100
//1 <= logs.length <= 500
//0 <= function_id < n
//0 <= timestamp <= 109
//两个开始事件不会在同一时间戳发生
//两个结束事件不会在同一时间戳发生
//每道函数都有一个对应 "start" 日志的 "end" 日志
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/exclusive-time-of-functions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class ExclusiveTime {
    /**
     * 错误
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = -1;
            arr[i][1] = -1;
        }
        for (String log : logs) {
            String[] split = log.split(":");
            int pid = Integer.parseInt(split[0]);
            String type = split[1];
            int t = Integer.parseInt(split[2]);
            if (type.equals("start")) {
                if (arr[pid][0] < 0 || t < arr[pid][0]) {
                    arr[pid][0] = t;
                }
            } else {
                if (arr[pid][1] < 0 || t > arr[pid][1]) {
                    arr[pid][1] = t;
                }
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int time = arr[i][1] - arr[i][0] + 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (arr[j][0] > arr[i][0] && arr[j][1] < arr[i][1]) {
                        time -= arr[j][1] - arr[j][0] + 1;
                    }
                }
            }
            res[i] = time;
        }
        return res;
    }

    /**
     * 栈解法
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastTime = 0;
        for (String log : logs) {
            String[] split = log.split(":");
            int pid = Integer.parseInt(split[0]);
            String type = split[1];
            int t = Integer.parseInt(split[2]);
            if (type.equals("start")) {
                if (stack.size() > 0) {
                    //中断上一个线程
                    Integer lastPid = stack.peek();
                    res[lastPid] += t - lastTime;
                }
                stack.add(pid);
                lastTime = t;
            } else {
                stack.pop();
                res[pid] += t + 1 - lastTime;
                lastTime = t + 1;
            }
        }
        return res;
    }
}
