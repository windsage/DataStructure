package org.jeff.datastructure.graph.dijkstra;

import java.util.Arrays;

public class MatrixUDG {
    private int mEdgNum;        // 边的数量
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    public MatrixUDG(char[] vexs, int[][] matrix) {
        int vlen = vexs.length;
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];
        mEdgNum = 0;
        for (int i = 0; i < vlen; i++)
            for (int j = i + 1; j < vlen; j++)
                if (mMatrix[i][j] != INF)
                    mEdgNum++;
    }

    /**
     * 计算vs顶点到其他各顶点的最短距离
     *
     * @param vs
     * @param prev
     * @param dist
     */
    public void dijkstra(int vs, int[] prev,int[] dist) {
        boolean[] flag = new boolean[mVexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            flag[i] = false;
            prev[i] = 0;
            dist[i] = mMatrix[vs][i];
        }
        flag[vs] = true;
        dist[vs] = 0;

        int k = 0;
        for (int i = 1; i < mVexs.length; i++) {
            int min = INF;
            //找出最小值
            for (int j = 0; j < mVexs.length; j++) {
                if (!flag[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            flag[k] = true;
            for (int j = 0; j < mVexs.length; j++) {
                int tmp = mMatrix[k][j] == INF ? INF : (min + mMatrix[k][j]);
                if (!flag[j] && (tmp < dist[j])) {
                    dist[j] = tmp;
                    prev[j] = k;
                }
            }
        }
        System.out.println(Arrays.toString(prev));
        System.out.printf("dijkstra(%c): \n", mVexs[vs]);
        for (int i = 0; i < mVexs.length; i++)
            System.out.printf("  shortest(%c, %c)=%d\n", mVexs[vs], mVexs[i], dist[i]);
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
        int[] prev = new int[pG.mVexs.length];
        int[] dist = new int[pG.mVexs.length];
        pG.dijkstra(3, prev, dist);
    }
}
