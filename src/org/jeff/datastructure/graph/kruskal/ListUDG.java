package org.jeff.datastructure.graph.kruskal;

public class ListUDG {

    private class VNode {
        char data;
        ENode firstNode;

        public VNode(char data) {
            this.data = data;
            this.firstNode = null;
        }
    }

    private class ENode {
        int index;
        int weight;
        ENode nextNode;

        public ENode(int index, int weight) {
            this.index = index;
            this.weight = weight;
            this.nextNode = null;
        }
    }


    private static class EData {
        char start, end;
        int weight;

        EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


    private VNode[] mVexes;
    private EData[] mEdges;

    /**
     * 构造邻接表
     *
     * @param vexes
     * @param edges
     */
    private ListUDG(char[] vexes, EData[] edges) {
        this.mEdges = edges;
        mVexes = new VNode[vexes.length];
        for (int i = 0; i < vexes.length; i++) {
            mVexes[i] = new VNode(vexes[i]);
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

    private void linkLast(int index, ENode link) {
        ENode node = mVexes[index].firstNode;
        if (node == null)
            mVexes[index].firstNode = link;
        else {
            while (node.nextNode != null) {
                node = node.nextNode;
            }
            node.nextNode = link;
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < mVexes.length; i++) {
            if (ch == mVexes[i].data)
                return i;
        }
        return -1;
    }


    public void kruskal() {
        //排序
        sortEdges();
        //最小生成树
        int[] vends = new int[mEdges.length];
        //
        int index = 0;
        //
        EData[] rets = new EData[mVexes.length - 1];
        for (EData edge : mEdges) {
            int start = getPosition(edge.start);
            int end = getPosition(edge.end);
            int m = getEnd(start, vends);
            int n = getEnd(end, vends);
            if (m != n) {
                vends[m] = n;
                rets[index++] = edge;
            }
        }
        int weight = 0;
        for (EData edge : rets) {
            weight += edge.weight;
            System.out.printf("%c,%c,%d ", edge.start, edge.end, edge.weight);
        }
        System.out.println("weight =" + weight);

    }

    private int getEnd(int i, int[] vends) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    /**
     * 把边按照从小到大用选择排序
     */
    private void sortEdges() {
        for (int i = mEdges.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (mEdges[j].weight > mEdges[j + 1].weight) {
                    EData tmp = mEdges[j + 1];
                    mEdges[j + 1] = mEdges[j];
                    mEdges[j] = tmp;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
        for (EData edge : mEdges) {
            System.out.printf("%d ", edge.weight);
        }
        System.out.println();
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < mVexes.length; i++) {
            System.out.printf("%d(%c): ", i, mVexes[i].data);
            ENode node = mVexes[i].firstNode;
            while (node != null) {
                System.out.printf("%d(%c) ", node.index, mVexes[node.index].data);
                node = node.nextNode;
            }
            System.out.printf("\n");
        }
    }

    public void DFS() {
        for (int i = 0; i < mVexes.length; i++) {
            ENode node = mVexes[i].firstNode;
        }
    }

    public static void main(String[] args) {
        char[] vexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //这里的边都是有序的
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
        ListUDG udg = new ListUDG(vexes, edges);
        udg.print();
        udg.kruskal();
    }
}
