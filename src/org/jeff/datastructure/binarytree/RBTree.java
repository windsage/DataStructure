package org.jeff.datastructure.binarytree;

public class RBTree<T extends Comparable<T>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class RBTNode<T extends Comparable<T>> {
        T key;
        boolean color;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return key;
        }
    }

    private RBTNode<T> mRoot;

    public RBTree() {
        mRoot = null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node.parent;
    }

    private boolean isRed(RBTNode<T> node) {
        return node.color;
    }

    private RBTNode<T> grandParentOf(RBTNode<T> node) {
        if (node.parent != null) {
            return node.parent.parent;
        } else {
            return null;
        }
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    private void setRed(RBTNode<T> node) {
        if (node != null)
            node.color = RED;
    }

    private void setColor(RBTNode<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node != null)
            node.parent = parent;
    }

    /**
     * 左旋转x结点
     *
     * @param x
     */
    private void leftRotate(RBTNode<T> x) {
        RBTNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.right;
        if (x.parent == null) {
            this.mRoot = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else
                x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBTNode<T> y) {
        RBTNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        x.parent = y.parent;

        if (y.parent == null)
            this.mRoot = x;
        else {
            if (y == y.parent.right)
                y.parent.right = x;
            else
                y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    private void insert(RBTNode<T> node) {
        RBTNode<T> x = this.mRoot;
        RBTNode<T> y = null;
        int cmp;
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            this.mRoot = node;
        }
        node.color = RED;
        insertFixUp(node);
    }

    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, grandparent;
        parent = parentOf(node);
        while (parent != null && isRed(parent)) {
            grandparent = parentOf(parent);
            if (parent == grandparent.left) {
                RBTNode<T> uncle = grandparent.right;
                if (uncle != null && isRed(uncle)) {
                    //叔结点是红色的
                    setBlack(parent);
                    setRed(grandparent);
                    setBlack(uncle);
                    node = grandparent;
                } else if (uncle != null && isBlack(uncle)) {
                    //叔结点是黑色的
                    if (parent.right == node) {
                        RBTNode<T> tmp;
                        leftRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    } else if (parent.left == node) {
                        setBlack(parent);
                        setRed(grandparent);
                        rightRotate(grandparent);
                    }
                }
            } else if (parent == grandparent.right) {
                RBTNode<T> uncle = grandparent.left;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandparent);
                    node = grandparent;
                } else if (uncle != null && isBlack(uncle)) {
                    if (parent.left == node) {
                        RBTNode<T> tmp;
                        rightRotate(parent);
                        tmp = parent;
                        parent = node;
                        node = tmp;
                    } else if (parent.right == node) {
                        setBlack(parent);
                        setRed(grandparent);
                        leftRotate(grandparent);
                    }
                }
            }
        }
        setBlack(this.mRoot);
    }

    public void insert(T key) {
        RBTNode<T> node = new RBTNode<>(key, BLACK, null, null, null);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }

    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        while ((node == null || isBlack(node)) && (node != this.mRoot)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right == null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node != null)
            setBlack(node);
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RBTNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"红黑树"
     */
    private void inOrder(RBTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"红黑树"
     */
    private void postOrder(RBTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }


    private void remove(RBTNode<T> node) {
        RBTNode<T> child, parent;
        boolean color;
        if (node.left != null && node.right != null) {
            RBTNode<T> replace = node;
            //获取后继结点，右孩子中的最小值
            replace = replace.right;
            while (replace.left != null) {
                replace = replace.left;
            }

            if (parentOf(node) != null) {
                if (parentOf(node).left == node) {
                    parentOf(node).left = replace;
                } else {
                    parentOf(node).right = replace;
                }
            } else {
                this.mRoot = replace;
            }
            //replace一定没有左孩子
            child = replace.right;
            parent = parentOf(replace);
            color = colorOf(replace);

            if (parent == node) {
                parent = replace;
            } else {
                if (child != null)
                    setParent(node, parent);
                parent.left = child;
                replace.right = node.right;
                setParent(node.right, replace);
            }
            replace.left = node.left;
            replace.color = node.color;
            replace.parent = node.parent;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);
            node = null;
        } else {
            if (node.left != null)
                child = node.left;
            else
                child = node.right;
            parent = node.parent;
            color = node.color;
            if (parent != null) {
                if (parent.left == node)
                    parent.left = child;
                else if (parent.right == node)
                    parent.right = child;
            } else {
                this.mRoot = child;
            }
            if (color == BLACK)
                removeFixUp(child, parent);
            node = null;
        }
    }
}
