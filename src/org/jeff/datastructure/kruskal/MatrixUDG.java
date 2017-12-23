package org.jeff.datastructure.kruskal;

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

    /**
     * 获取所有的边
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] array = new EData[mEdgNum];
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = i + 1; j < mVexs.length; j++) {
                if (mMatrix[i][j] != INF)
                    array[index++] = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
            }
        }
        return array;
    }


    /**
     * 冒泡排序
     *
     * @return
     */
    private EData[] sortEdges(EData[] edges) {
        for (int i = edges.length - 1; i > 0; i--) {
            boolean tag = false;
            for (int j = 0; j < i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                    tag = true;
                }
            }
            if (!tag)
                break;
        }
        return edges;
    }


    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (ch == mVexs[i])
                return i;

        }
        return -1;
    }

    public void kruskal() {
        int index = 0;
        EData[] edges = sortEdges(getEdges());
        EData[] rets = new EData[mEdgNum];
        int[] vends = new int[edges.length];
        for (int i = 0; i < edges.length; i++) {
            System.out.print(edges[i].weight + " ");
            int p1 = getPosition(edges[i].start);      // 获取第i条边的"起点"的序号
            int p2 = getPosition(edges[i].end);        // 获取第i条边的"终点"的序号
            int m = getEnd(p1, vends);
            int n = getEnd(p2, vends);
            if (m != n) {
                //m的终点是N
                vends[m] = n;
                rets[index++] = edges[i];
            }

        }
        System.out.println(Arrays.toString(vends));
        //统计最小生成树的权重
        int weight = 0;
        for (int i = 0; i < index; i++) {
            weight += rets[i].weight;
        }
        System.out.println("Kruskal = " + weight);
        for (EData data : rets) {
            if (data != null)
                System.out.printf("%c,%c ", data.start, data.end);
        }
    }

    private int getEnd(int i, int[] vends) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
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

        pG.kruskal();   // Kruskal算法生成最小生成树
    }
}
