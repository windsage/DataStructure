package org.jeff.datastructure.graph.floyd;

import java.util.Arrays;

public class MatrixUDG {
    private static int INF = Integer.MAX_VALUE;

    private static class EData {
        char start, end;
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    private char[] mVexs;
    private int[][] mMatrix;
    private int mEdges = 0;

    public MatrixUDG(char[] vexs, int[][] matrix) {
        mVexs = Arrays.copyOf(vexs, vexs.length);
        mMatrix = new int[vexs.length][vexs.length];
        for (int i = 0; i < vexs.length; i++) {
            for (int j = 0; j < vexs.length; j++) {
                mMatrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vexs.length; i++) {
            for (int j = i + 1; j < vexs.length; j++) {
                if (mMatrix[i][j] != 0 && mMatrix[i][j] != INF) {
                    mEdges++;
                }
            }
        }
    }


    private int firstVisited(int i) {
        for (int k = 0; k < mVexs.length; k++) {
            if (mMatrix[i][k] != 0 && mMatrix[i][k] != INF)
                return k;
        }
        return -1;
    }

    private int nextVisited(int i, int k) {
        for (k = k + 1; k < mVexs.length; k++) {
            if (mMatrix[i][k] != 0 && mMatrix[i][k] != INF)
                return k;
        }
        return -1;
    }

    private void DFS() {
        boolean[] visited = new boolean[mVexs.length];
        System.out.printf("DFS:");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    private void DFS(int i, boolean[] visited) {
        visited[i] = true;
        System.out.printf("%C ", mVexs[i]);
        for (int k = firstVisited(i); k >= 0; k = nextVisited(i, k)) {
            if (!visited[k])
                DFS(k, visited);
        }
    }


    private void BFS() {
        boolean[] visited = new boolean[mVexs.length];
        int head = 0, rear = 0;
        int[] queue = new int[mVexs.length];
        System.out.printf("BFS:");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%C ", mVexs[i]);
                queue[rear++] = i;
            }
            while (head != rear) {
                int j = queue[head++];
                for (int k = firstVisited(j); k >= 0; k = nextVisited(j, k)) {
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%C ", mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.println();
    }

    private EData[] getAllEdges() {
        EData[] edges = new EData[mEdges];
        int index = 0;
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = i + 1; j < mVexs.length; j++) {
                if (mMatrix[i][j] != 0 && mMatrix[i][j] != INF) {
                    EData edge = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
                    edges[index++] = edge;
                }
            }
        }
        return edges;
    }

    private void sortEdges(EData[] edges) {
        for (int i = edges.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    private int getPosition(char ch) {
        return Arrays.binarySearch(mVexs, ch);
    }

    private int getEnd(int i, int[] vends) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    private void kruskal() {
        int[] vends = new int[mVexs.length];
        EData[] result = new EData[mEdges];
        int index = 0;
        EData[] edges = getAllEdges();
        sortEdges(edges);
        for (int i = 0; i < edges.length; i++) {
            EData edge = edges[i];
            int start = getPosition(edge.start);
            int end = getPosition(edge.end);
            int m = getEnd(start, vends);
            int n = getEnd(end, vends);
            if (m != n) {
                vends[m] = n;
                result[index++] = edge;
            }
        }
        int weight = 0;
        for (int i = 0; i < index; i++) {
            weight += result[i].weight;
        }

        System.out.println("Kruskal = " + weight);
        for (EData data : result) {
            if (data != null)
                System.out.printf("(%c,%c) ", data.start, data.end);
        }
        System.out.println();

    }


    private void prim(int start) {
        char[] prims = new char[mVexs.length];
        int[] weights = new int[mVexs.length];
        int index = 0;
        for (int i = 0; i < mVexs.length; i++) {
            weights[i] = mMatrix[start][i];
        }
        prims[index++] = mVexs[start];
        weights[start] = 0;

        for (int i = 0; i < mVexs.length; i++) {
            if (i == start)
                continue;
            int k = 0;
            int min = INF;
            for (int j = 0; j < mVexs.length; j++) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
            }
            prims[index++] = mVexs[k];
            weights[k] = 0;
            for (int j = 0; j < mVexs.length; j++) {
                if (weights[j] != 0 && weights[j] > mMatrix[k][j]) {
                    weights[j] = mMatrix[k][j];
                }
            }
        }

        int sum = 0;
        for (int i = 1; i < mVexs.length; i++) {
            int m = getPosition(prims[i]);
            int min = INF;
            for (int j = 0; j < i; j++) {
                int n = getPosition(prims[j]);
                if (min > mMatrix[m][n]) {
                    min = mMatrix[m][n];
                }
            }
            sum += min;
        }
        // 打印最小生成树
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
    }

    private void floyd() {
        int[][] dist = new int[mVexs.length][mVexs.length];
        int[][] path = new int[mVexs.length][mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = mMatrix[i][j];
                path[i][j] = j;
            }
        }


        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    int tmp = (mMatrix[i][k] == INF || mMatrix[k][j] == INF) ? INF : (mMatrix[i][k] + mMatrix[k][j]);
                    if (dist[i][j] > tmp) {
                        dist[i][j] = tmp;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d ", dist[i][j]);
            System.out.printf("\n");
        }

    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        MatrixUDG pG;
        pG = new MatrixUDG(vexs, matrix);
        //深度搜索
        pG.DFS();
        //广度搜索
        pG.BFS();
        //kruskal算最小生成树
        pG.kruskal();
        //prime
        pG.prim(0);
        // floyd算法最短距离
        pG.floyd();
    }
}
