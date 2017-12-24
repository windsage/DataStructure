package org.jeff.datastructure.graph.basic;

/**
 * 邻接矩阵无向图
 */
public class MatrixUDG {

    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵

    public MatrixUDG(char[] vexs, char[][] edges) {
        int vlen = vexs.length;
        int elen = edges.length;
        mVexs = new char[vlen];
        //两个字符数组赋值
        System.arraycopy(vexs, 0, mVexs, 0, vlen);
        //
        mMatrix = new int[vlen][vlen];
        for (char[] tmp : edges) {
            if (tmp.length == 2) {
                int x = getPosition(tmp[0]);
                int y = getPosition(tmp[1]);
                mMatrix[x][y] = 1;
                mMatrix[y][x] = 1;
            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (ch == mVexs[i])
                return i;
        }
        return -1;
    }

    /**
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }


    boolean[] visited;

    public void DFS() {
        visited = new boolean[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                DFS(i);
            }
        }

    }

    private int firstVertex(int i) {
//        if (i < 0 || i >= mVexs.length)
//            return -1;
        for (int k = 0; k < mVexs.length; k++) {
            if (mMatrix[k][i] == 1)
                return k;
        }
        return -1;
    }

    private int nextVertex(int i, int k) {
        for (k = k + 1; k < mVexs.length; k++) {
            if (mMatrix[k][i] == 1)
                return k;
        }
        return -1;
    }

    private void DFS(int j) {
        visited[j] = true;
        System.out.printf("%c ", mVexs[j]);
        for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) {
            if (!visited[k])
                DFS(k);
        }

    }

    public void BFS() {
        boolean[] mark = new boolean[mVexs.length];
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            if (!mark[i]) {
                mark[i] = true;
                System.out.printf("%c ", mVexs[i]);
                queue[rear++] = i;
            }
            while (head != rear) {
                int j = queue[head++];
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) {
                    if (!mark[k]) {
                        mark[k] = true;
                        System.out.printf("%c ", mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
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
        MatrixUDG pG;

        // 采用已有的"图"
        pG = new MatrixUDG(vexs, edges);

        pG.print();   // 打印图
        pG.DFS();
        pG.BFS();
    }
}
