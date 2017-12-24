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


    public void floyd(int[][] path, int[][] dist) {
        int num = mVexs.length;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                dist[i][j] = mMatrix[i][j];
                path[i][j] = j;
            }
        }
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    int tmp = (dist[i][k] == INF || dist[k][j] == INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        dist[i][j] = tmp;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        // 打印floyd最短路径的结果
        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
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
        int[][] path = new int[pG.mVexs.length][pG.mVexs.length];
        int[][] floy = new int[pG.mVexs.length][pG.mVexs.length];
        // floyd算法获取各个顶点之间的最短距离
        pG.floyd(path, floy);
    }
}
