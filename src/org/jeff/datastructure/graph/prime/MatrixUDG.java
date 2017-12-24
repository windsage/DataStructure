package org.jeff.datastructure.graph.prime;

import java.util.Arrays;

public class MatrixUDG {
    private char[] mVexs;
    private int[][] mMatrix;
    private int mEdgNum;//边的数量
    private static final int INF = Integer.MAX_VALUE;   // 最大值


    public MatrixUDG(char[] vexs, int[][] matrix) {
        //复制数组
        mVexs = Arrays.copyOf(vexs, vexs.length);
        //
        mMatrix = new int[vexs.length][vexs.length];
        for (int i = 0; i < vexs.length; i++) {
            for (int j = 0; j < vexs.length; j++) {
                mMatrix[i][j] = matrix[i][j];
            }
        }
        mEdgNum = 0;
        for (int i = 0; i < vexs.length; i++) {
            for (int j = i + 1; j < vexs.length; j++) {
                if (mMatrix[i][j] != INF)
                    mEdgNum++;
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

    public void prime(int start) {
        int num = mVexs.length;
        int index = 0;
        //最小生成树
        char[] prims = new char[num];
        // 存储的是已经在最小生成树中结点到其余各结点中最小值
        int[] weights = new int[num];
        prims[index++] = mVexs[start];
        for (int i = 0; i < num; i++) {
            weights[i] = mMatrix[start][i];
        }
        System.out.println(Arrays.toString(weights));
        for (int i = 0; i < num; i++) {
            if (start == i)
                continue;
            //j是一个循环遍历，k是最小权重索引，min最小权重值
            int j = 0, k = 0, min = INF;
            while (j < num) {
                if (weights[j] != 0 && weights[j] < min) {
                    //记录最小权重的值
                    min = weights[j];
                    //记录最小权重的索引
                    k = j;
                }
                j++;
            }
            //将weights中大于0的最小值的索引在mVexs中的值保存进最小生成树
            prims[index++] = mVexs[k];
            //k = 1
            weights[k] = 0;
            System.out.println(Arrays.toString(weights));
            for (j = 0; j < num; j++) {
                if (weights[j] != 0 && mMatrix[k][j] < weights[j]) {
                    weights[j] = mMatrix[k][j];
                }
            }
            System.out.println(Arrays.toString(weights));
        }
        // 计算最小生成树的权值
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            // 获取prims[i]在mMatrix中的位置
            int n = getPosition(prims[i]);
            // 在vexs[0...i]中，找出到j的权值最小的顶点。
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n] < min)
                    min = mMatrix[m][n];
            }
            sum += min;
        }
        // 打印最小生成树
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
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
        pG.prime(0);
    }
}
