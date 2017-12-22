package org.jeff.datastructure.graph;

/**
 * 邻接表无向图
 */
public class ListUDG {
    // 邻接表中表对应的链表的顶点
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


    public ListUDG(char[] vexs, char[][] edges) {
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;
        // 初始化"顶点"
        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode(vexs[i]);
        }
        for (int i = 0; i < elen; i++) {
            char[] tmp = edges[i];
            int start = position(tmp[0]);
            int end = position(tmp[1]);

            ENode node0 = new ENode(end);
            ENode node1 = new ENode(start);
            ENode startNode = mVexs[start].firstEdge;
            if (startNode == null) {
                mVexs[start].firstEdge = node0;
            } else {
                while (startNode.nextEdge != null) {
                    startNode = startNode.nextEdge;
                }
                startNode.nextEdge = node0;
            }
            ENode endNode = mVexs[end].firstEdge;
            if (endNode == null)
                mVexs[end].firstEdge = node1;
            else {
                while (endNode.nextEdge != null) {
                    endNode = endNode.nextEdge;
                }
                endNode.nextEdge = node1;
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


    /**
     * 深度遍历
     */
    public void DFS() {
        boolean[] visited = new boolean[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i].data);
                DFS(i, visited);
            }

        }
        System.out.println();
    }

    private void DFS(int i, boolean[] visited) {
        ENode nextVertex = mVexs[i].firstEdge;
        while (nextVertex != null) {
            int index = nextVertex.ivex;
            if (!visited[index]) {
                visited[index] = true;
                System.out.printf("%c ", mVexs[index].data);
                DFS(index, visited);
            }
            nextVertex = nextVertex.nextEdge;
        }

    }


    public void BFS() {
        int[] queue = new int[mVexs.length];
        //访问标记
        boolean[] visited = new boolean[mVexs.length];
        int head = 0;
        int rear = 0;
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
        System.out.println();
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        ListUDG pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new ListUDG();
        // 采用已有的"图"
        pG = new ListUDG(vexs, edges);

        pG.print();   // 打印图
        pG.DFS();
        pG.BFS();
    }
}
