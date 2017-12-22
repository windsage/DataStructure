package org.jeff.datastructure.graph;

import java.util.LinkedList;

public class ListDG {
    private class ENode {
        int ivex;       // 该边所指向的顶点的位置
        ENode nextEdge; // 指向下一条弧的指针

        ENode(int ivex) {
            this.ivex = ivex;
            this.nextEdge = null;
        }
    }

    // 邻接表中表的顶点
    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧

        VNode(char data) {
            this.data = data;
            this.firstEdge = null;
        }
    }

    private VNode[] mVexs;  // 顶点数组

    public ListDG(char[] vexs, char[][] edges) {
        mVexs = new VNode[vexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode(vexs[i]);
        }
        for (char[] tmp : edges) {
            int start = position(tmp[0]);
            int end = position(tmp[1]);
            ENode node = mVexs[start].firstEdge;
            if (node == null)
                mVexs[start].firstEdge = new ENode(end);
            else {
                while (node.nextEdge != null)
                    node = node.nextEdge;
                node.nextEdge = new ENode(end);
            }
        }
    }


    public int position(char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (ch == mVexs[i].data)
                return i;
        }
        return -1;
    }

    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("%d(%c): ", i, mVexs[i].data);
            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, mVexs[node.ivex].data);
                node = node.nextEdge;
            }
            System.out.printf("\n");
        }
    }


    public void BFS() {
        int head = 0;
        int rear = 0;
        boolean[] visited = new boolean[mVexs.length];
        int[] queue = new int[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i].data);
                queue[rear++] = i;
            }
            while (head != rear) {
                int j = queue[head++];
                ENode node = mVexs[j].firstEdge;
                while (node != null) {
                    if (!visited[node.ivex]) {
                        visited[node.ivex] = true;
                        System.out.printf("%c ", mVexs[node.ivex].data);
                        queue[rear++] = node.ivex;
                    }
                    node = node.nextEdge;
                }
            }
        }
    }


    /**
     * 拓补排序
     */
    private int topologicalSort() {
        //把所有结点的度存储起来
        int[] ins = new int[mVexs.length];
        for (VNode mVex : mVexs) {
            ENode node = mVex.firstEdge;
            while (node != null) {
                ins[node.ivex]++;
                node = node.nextEdge;
            }
        }
        //把入度为0的结点放入队列中
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < ins.length; i++) {
            if (ins[i] == 0)
                queue.offer(i);
        }
        int index = 0;
        char[] tops = new char[mVexs.length];
        while (!queue.isEmpty()) {
            int j = queue.poll().intValue();
            tops[index++] = mVexs[j].data;
            ENode node = mVexs[j].firstEdge;
            while (node != null) {
                ins[node.ivex]--;
                if (ins[node.ivex]==0)
                    queue.offer(node.ivex);
                node = node.nextEdge;
            }
        }

        if(index != mVexs.length) {
            System.out.printf("Graph has a cycle\n");
            return 1;
        }

        // 打印拓扑排序结果
        System.out.printf("== TopSort: ");
        for(int i = 0; i < mVexs.length; i ++)
            System.out.printf("%c ", tops[i]);
        System.out.printf("\n");

        return 0;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'B'},
                {'B', 'C'},
                {'B', 'E'},
                {'B', 'F'},
                {'C', 'E'},
                {'D', 'C'},
                {'E', 'B'},
                {'E', 'D'},
                {'F', 'G'}};
        ListDG pG;

        pG = new ListDG(vexs, edges);
        pG.print();
        pG.BFS();
        pG.topologicalSort();
    }
}
