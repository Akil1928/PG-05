package model.tree;

public class BST<T extends Comparable<T>> extends BTree<T>{
    private BTreeNode<T> root; // representa la única entrada al árbol


    @Override
    public void remove(T element) throws TreeException {
        super.remove(element);
    }

    @Override
    public boolean contains(T element) throws TreeException {
        return super.contains(element);
    }

    @Override
    public void add(T element) {
        this.root = add(root,element);

    }
    private BTreeNode<T> add(BTreeNode<T> node, T element){
        if(node == null){
            node = new BTreeNode<>(element);

        }else if(compareElements(element,node.data) < 0)
            node.left = add(node.left,element);
        else if(compareElements(element,node.data) > 0)
            node.right = add(node.right,element);

        return node;
    }



    @Override
    public T min() throws TreeException {
        return super.min();
    }

    @Override
    public T max() throws TreeException {
        return super.max();
    }

    @Override
    public String preOrder() throws TreeException {
        return super.preOrder();
    }
}
