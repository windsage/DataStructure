package org.jeff.datastructure.graph;

import java.util.Arrays;

public class MatrixDG {
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵

    public MatrixDG(char[] vexs, char[][] edges) {
        mVexs = Arrays.copyOf(vexs, vexs.length);
        mMatrix = new int[vexs.length][vexs.length];
        for (char[] tmp : edges) {
            int p1 = getPosition(tmp[0]);
            int p2 = getPosition(tmp[1]);
            mMatrix[p1][p2] = 1;
        }
    }

    public int getPosition(char ch) {
        return Arrays.binarySearch(mVexs, ch);
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
        MatrixDG pG;

        pG = new MatrixDG(vexs, edges);
        pG.print();

    }
}
