import java.io.*;
import java.util.*;

class Node {
    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
    }
}

class BinarySearchTree {
    Node root;
    int leftHeight, rightHeight;
    Map<Node, Integer> heightCache = new HashMap<>();

    BinarySearchTree() {
        root = null;
        leftHeight = rightHeight = 0;
    }
    BinarySearchTree(Node root) {
        this.root = root;
        leftHeight = rightHeight = 0;
    }

    void insert(int value) {
        root = insertRec(root, value);
    }

    Node insertRec(Node root, int value) {
        if (root == null) { root = new Node(value); }
        else if (value < root.value) { root.left = insertRec(root.left, value); }
        else if (value > root.value) { root.right = insertRec(root.right, value); }
        return root;
    }

    int getHeight(Node node) {
        if (node == null) return 0;
        if (heightCache.containsKey(node)) return heightCache.get(node);

        int height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        heightCache.put(node, height);
        return height;
    }

    boolean isRightSkewed() {
        return isRightSkewedRec(root, true);
    }

    boolean isRightSkewedRec(Node node, boolean isRoot) {
        if (node.left != null) return false;
        if (node.right == null) return !isRoot;
        return isRightSkewedRec(node.right, false);
    }

    boolean isLeftSkewed() {
        return isLeftSkewedRec(root, true);
    }

    boolean isLeftSkewedRec(Node node, boolean isRoot) {
        if (node.right != null) return false;
        if (node.left == null) return !isRoot;
        return isLeftSkewedRec(node.left, false);
    }

    boolean isBalanced() {
        return isBalancedRec(root);
    }

    boolean isBalancedRec(Node node) {
        if (node == null) return true;
        if (Math.abs(getHeight(node.left) - getHeight(node.right)) > 1) return false;
        return isBalancedRec(node.left) && isBalancedRec(node.right);
    }

    boolean isComplete() {
        return isCompleteRec(root);
    }

    boolean isCompleteRec(Node node) {
        if (node == null) return true;
        if ((node.left == null ) && (node.right == null)) return leftHeight >= rightHeight;
        if (node.left == null) return false;
        if (node.right == null) return isCompleteRec(node.left);
        return isCompleteRec(node.left) && isCompleteRec(node.right);
    }

    boolean isFull() {
        return isFullRec(root);
    }

    boolean isFullRec(Node node) {
        return (node.left == null) == (node.right == null);
    }

    boolean isPerfect() {
        return isPerfectRec(root);
    }

    boolean isPerfectRec(Node node) {
        if (node == null) return leftHeight == rightHeight;;
        if ((node.left == null) != (node.right == null)) return false;
        if (getHeight(node.left) != getHeight(node.right)) return false;
        return isPerfectRec(node.left) && isPerfectRec(node.right);
    }



}


public class Jackie {
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("jackie.dat"));
        int cases = Integer.parseInt(scanner.nextLine());

        // while (cases-- > 0) {
        for (int i = 1; i <= cases; i++) {
            System.out.println("Case: " + i);

            String[] values = scanner.nextLine().trim().split("\\s+");
            BinarySearchTree bst = new BinarySearchTree();

            for (String val : values) bst.insert(Integer.parseInt(val));


            boolean isRightSkewed, isLeftSkewed, isBalanced, isComplete, isFull, isPerfect;
            isRightSkewed = isLeftSkewed = isBalanced= isComplete = isFull = isPerfect = false;

            if (bst.isPerfect()) {
                isPerfect = isFull = isComplete = isBalanced = true;
            } else if (bst.isComplete()) {
                isComplete = isBalanced = true;
            } else {
                if (bst.isFull())isFull = true;
                if (bst.isBalanced()) isBalanced = true;
                if (bst.isLeftSkewed()) isLeftSkewed = true;
                else if (bst.isRightSkewed()) isRightSkewed = true;
            }

            if (isBalanced) System.out.print("BALANCED ");
            if (isComplete) System.out.print("COMPLETE ");
            if (isFull) System.out.print("FULL ");
            if (isLeftSkewed) System.out.print("LEFT-SKEWED ");
            if (isPerfect) System.out.print("PERFECT ");
            if (isRightSkewed) System.out.print("RIGHT-SKEWED ");
            System.out.println();
            System.out.println();



        }
    }
}
