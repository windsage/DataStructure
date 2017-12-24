package org.jeff.datastructure.graph.floyd;

public class MatrixUDG {
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
    private EData[] mEdges;

    public static void main(String[] args) {

    }
}
