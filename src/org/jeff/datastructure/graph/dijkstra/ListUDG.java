package org.jeff.datastructure.graph.dijkstra;

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


    private VNode[] mVexs;
    private EData[] mEdges;

    public ListUDG(char[] vexs, EData[] edges) {
        mVexs = new VNode[vexs.length];
        for (int i = 0; i < vexs.length; i++) {
            mVexs[i] = new VNode(vexs[i]);
        }
        for (EData edge : edges) {
            int start = getPosition(edge.start);
            int end = getPosition(edge.end);
            linkLast(start, new ENode(end, edge.weight));
            linkLast(end, new ENode(start, edge.weight));
        }
    }


    private void linkLast(int index, ENode last) {
        ENode node = mVexs[index].firstEdge;
        if (node == null) {
            mVexs[index].firstEdge = last;
        } else {
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


    public void djistra(int start) {

    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        EData[] edges = {
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
