package org.jeff.datastructure.graph;

public class ListDG {
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

    public ListDG(char[] vexs, char[][] edges) {
        mVexs = new VNode[vexs.length];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode(vexs[i]);
        }
        for (char[] tmp : edges) {
            int start = position(tmp[0]);
            int end = position(tmp[1]);
            ENode node = mVexs[start].firstEdge;
            if (node == null)
                mVexs[start].firstEdge = new ENode(end);
            else {
                while (node.nextEdge != null)
                    node = node.nextEdge;
                node.nextEdge = new ENode(end);
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
        ListDG pG;

        pG = new ListDG(vexs, edges);
        pG.print();
    }
}
