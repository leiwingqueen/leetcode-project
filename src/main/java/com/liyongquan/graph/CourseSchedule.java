package com.liyongquan.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseSchedule {
    /**
     * 先尝试使用bfs，从入度入手
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //维护一个边的列表。下标表示始点。用这个索引来找到以对应下标的边
        List<Integer>[] edges = new List[numCourses];
        //入度统计
        int[] inDegree = new int[numCourses];
        //初始化边
        for (int[] edge : prerequisites) {
            int start = edge[1];
            int end = edge[0];
            if (edges[start] == null) {
                edges[start] = new LinkedList<>();
            }
            edges[start].add(end);
            inDegree[end]++;
        }
        //初始化队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        //bfs过程
        int visit = 0;
        while (!queue.isEmpty()) {
            Integer start = queue.poll();
            visit++;
            //入度更新
            if (edges[start] != null) {
                for (Integer end : edges[start]) {
                    inDegree[end]--;
                    if (inDegree[end] == 0) {
                        queue.add(end);
                    }
                }
            }
        }
        return visit == numCourses;
    }
}
