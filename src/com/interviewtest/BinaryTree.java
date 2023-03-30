package com.interviewtest;

/**
 * @author gyf
 * @date 2022-06-10 19:56
 */
public class BinaryTree {
    /**
     * 实现要求：
     * 1、根据已有的代码片段，创建二叉搜索树；
     * 2、用中序遍历输出排序结果，结果形如：0，1，2 ，3 ，4， 5， 6， 7， 8， 9，
     * 3、使用递归、非递归二种方式实现遍历；
     * 4、注意编写代码注释。
     */

    public static void main(String[] args) {

        final int[] values = { 1, 3, 4, 5, 2, 8, 6, 7, 9, 0 };

        System.out.println("Create BST: ");
        Node root = createBinaryTree(values);

        System.out.println("Traversing the BST with recursive algorithm: ");
        inOrderTransvalWithRecursive(root);

        System.out.println("Traversing the BST with iterative algorithm: ");
        inOrderTransvalWithIterate(root);
    }



    // 构建二叉树
    public static Node createBinaryTree(int[] values) {
        Node root = null;
        for (int i = 0; i < values.length; i++) {

            root = new Node(values[i]);
            root.setLeftNode(new Node(values[2*i+1]));
            root.setRigthNode(new Node(values[2*i+2]));
        }

        return root;
    }

    // 递归实现
    public static void inOrderTransvalWithRecursive(Node node) {
        // TODO:
    }

    // 非递归实现
    public static void inOrderTransvalWithIterate(Node root) {
        // TODO:
    }
}

//创建节点类
 class Node{
    private Node leftNode;
    private int value;
    private Node rigthNode;

    public Node(int value){
        this.value = value;
        this.leftNode = null;
        this.rigthNode =null;
    }

    public Node(Node leftNode, int value, Node rigthNode) {
        this.leftNode = leftNode;
        this.value = value;
        this.rigthNode = rigthNode;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRigthNode() {
        return rigthNode;
    }

    public void setRigthNode(Node rigthNode) {
        this.rigthNode = rigthNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "leftNode=" + leftNode +
                ", value=" + value +
                ", rigthNode=" + rigthNode +
                '}';
    }
}
