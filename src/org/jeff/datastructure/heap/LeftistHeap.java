package org.jeff.datastructure.heap;

public class LeftistHeap<T extends Comparable<T>> {

    private class LeftistNode<T extends Comparable<T>> {
        public T key;
        public LeftistNode<T> left;
        public LeftistNode<T> right;
        public int npl;


        public LeftistNode(T key, LeftistNode<T> left, LeftistNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.npl = 0;
        }

        @Override
        public String toString() {
            return "key:" + this.key;
        }
    }


    private LeftistNode<T> mRoot;

    public LeftistHeap() {
        this.mRoot = null;
    }

    /*
     * 前序遍历"左倾堆"
     */
    private void preOrder(LeftistNode<T> heap) {
        if(heap != null) {
            System.out.print(heap.key+" ");
            preOrder(heap.left);
            preOrder(heap.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"左倾堆"
     */
    private void inOrder(LeftistNode<T> heap) {
        if(heap != null) {
            inOrder(heap.left);
            System.out.print(heap.key+" ");
            inOrder(heap.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"左倾堆"
     */
    private void postOrder(LeftistNode<T> heap) {
        if(heap != null)
        {
            postOrder(heap.left);
            postOrder(heap.right);
            System.out.print(heap.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }
    /**
     * 合并
     *
     * @param x
     * @param y
     * @return
     */
    private LeftistNode<T> merge(LeftistNode<T> x, LeftistNode<T> y) {
        if (x == null)
            return y;
        if (y == null)
            return x;
        //x结点比y结点大
        if (x.key.compareTo(y.key) > 0) {
            LeftistNode<T> temp = y;
            y = x;
            x = temp;
        }
        x.right = merge(x.right, y);
        if (x.left == null || x.left.npl < x.right.npl) {
            LeftistNode<T> tmp = x.right;
            x.right = x.left;
            x.left = tmp;
        }

        if (x.right == null || x.left == null)
            x.npl = 0;
        else
            x.npl = (x.left.npl < x.right.npl) ? x.left.npl + 1 : x.right.npl + 1;
        return x;
    }


    public void merge(LeftistHeap<T> other) {
        merge(mRoot, other.mRoot);
    }

    /*
     * 新建结点(key)，并将其插入到左倾堆中
     *
     * 参数说明：
     *     key 插入结点的键值
     */
    public void insert(T key) {
        LeftistNode<T> node = new LeftistNode<T>(key,null,null);

        // 如果新建结点失败，则返回。
        if (node != null)
            this.mRoot = merge(this.mRoot, node);
    }

    /*
     * 删除根结点
     *
     * 返回值：
     *     返回被删除的节点的键值
     */
    public T remove() {
        if (this.mRoot == null)
            return null;

        T key = this.mRoot.key;
        LeftistNode<T> l = this.mRoot.left;
        LeftistNode<T> r = this.mRoot.right;

        this.mRoot = null;          // 删除根节点
        this.mRoot = merge(l, r);   // 合并左右子树

        return key;
    }

    /*
     * 销毁左倾堆
     */
    private void destroy(LeftistNode<T> heap) {
        if (heap==null)
            return ;

        if (heap.left != null)
            destroy(heap.left);
        if (heap.right != null)
            destroy(heap.right);

        heap=null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"左倾堆"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(LeftistNode<T> heap, T key, int direction) {

        if(heap != null) {

            if(direction==0)    // heap是根节点
                System.out.printf("%2d(%d) is root\n", heap.key, heap.npl);
            else                // heap是分支节点
                System.out.printf("%2d(%d) is %2d's %6s child\n", heap.key, heap.npl, key, direction==1?"right" : "leftChild");

            print(heap.left, heap.key, -1);
            print(heap.right,heap.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
