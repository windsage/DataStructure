package org.jeff.datastructure.graph.prime;

public class ListUDG {
    private static int INF = Integer.MAX_VALUE;

    private class ENode {
        int ivex;
        int weight;
        ENode nextEdge;

        public ENode(int ivex, int weight) {
            this.ivex = ivex;
            this.weight = weight;
            this.nextEdge = null;
        }
    }

    private class VNode {
        char data;
        ENode firstEdge;

        public VNode(char data) {
            this.data = data;
            this.firstEdge = null;
        }
    }


    private static class EData {
        char start, end;
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


    private VNode[] mVexs;  // 顶点数组


    private ListUDG(char[] vexs, EData[] edges) {
        mVexs = new VNode[vexs.length];
        for (int i = 0; i < vexs.length; i++) {
            mVexs[i] = new VNode(vexs[i]);
        }
        for (EData edge : edges) {
            int start = getPosition(edge.start);
            int end = getPosition(edge.end);
            ENode node1 = new ENode(start, edge.weight);
            ENode node2 = new ENode(end, edge.weight);
            linkLast(start, node2);
            linkLast(end, node1);
        }
    }

    private void linkLast(int index, ENode last) {
        ENode node = mVexs[index].firstEdge;
        if (node == null)
            mVexs[index].firstEdge = last;
        else {
            while (node.nextEdge != null) {
                node = node.nextEdge;
            }
            node.nextEdge = last;
        }
    }


    private int getPosition(char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (ch == mVexs[i].data)
                return i;
        }
        return -1;
    }

    private int getWeight(int start, int end) {
        if (start == end)
            return 0;
        ENode node = mVexs[start].firstEdge;
        while (node != null) {
            if (node.ivex == end) {
                return node.weight;
            }
            node = node.nextEdge;
        }
        return INF;
    }


    public void prim(int start) {
        int index = 0;
        char[] prims = new char[mVexs.length];
        int[] weights = new int[mVexs.length];
        prims[index++] = mVexs[start].data;
        //获取start点到每个顶点的权重
        for (int i = 0; i < mVexs.length; i++) {
            weights[i] = getWeight(start, i);
        }
        //找出最小的权重顶点

        for (int i = 0; i < mVexs.length; i++) {
            if (start == i)
                continue;
            //找出到所有顶点中权重大于0最小的顶点
            int min = INF, j = 0, k = 0;
            while (j < mVexs.length) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }
            prims[index++] = mVexs[k].data;
            weights[k] = 0;
            for (j = 0; j < mVexs.length; j++) {
                int tmp = getWeight(k, j);
                if (tmp != 0 && tmp < weights[j]) {
                    weights[j] = tmp;
                }
            }
        }

    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        EData[] edges = {
                // 起点 终点 权
                new EData('A', 'B', 12),
                new EData('A', 'F', 16),
                new EData('A', 'G', 14),
                new EData('B', 'C', 10),
                new EData('B', 'F', 7),
                new EData('C', 'D', 3),
                new EData('C', 'E', 5),
                new EData('C', 'F', 6),
                new EData('D', 'E', 4),
                new EData('E', 'F', 2),
                new EData('E', 'G', 8),
                new EData('F', 'G', 9),
        };
    }
}
