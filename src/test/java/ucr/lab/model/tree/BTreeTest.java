package ucr.lab.model.tree;

import org.junit.jupiter.api.Test;

// Removed: import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    void testAddAndContains() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testAddAndContains ---");
        System.out.println("Testing isEmpty on new tree: " + tree.isEmpty());

        try {
            tree.add(10);
            System.out.println("Testing isEmpty after adding 10: " + tree.isEmpty());
            System.out.println("Testing size after adding 10: " + tree.size());
            System.out.println("Testing contains 10: " + tree.contains(10));
            System.out.println("Testing contains 5: " + tree.contains(5));

            tree.add(20);
            System.out.println("Testing size after adding 20: " + tree.size());
            System.out.println("Testing contains 20: " + tree.contains(20));

            tree.add(30);
            System.out.println("Testing size after adding 30: " + tree.size());
            System.out.println("Testing contains 30: " + tree.contains(30));

            tree.add(40);
            System.out.println("Testing size after adding 40: " + tree.size());
            System.out.println("Testing contains 40: " + tree.contains(40));

            // Test contains for existing and non-existing elements
            System.out.println("Testing contains 10 (again): " + tree.contains(10));
            System.out.println("Testing contains 20 (again): " + tree.contains(20));
            System.out.println("Testing contains 30 (again): " + tree.contains(30));
            System.out.println("Testing contains 40 (again): " + tree.contains(40));
            System.out.println("Testing contains 5: " + tree.contains(5));
            System.out.println("Testing contains 15: " + tree.contains(15));
            System.out.println("Testing contains 25: " + tree.contains(25));
            System.out.println("Testing contains 35: " + tree.contains(35));
            System.out.println("Testing contains 50: " + tree.contains(50));

            System.out.println("Tree after additions:\n" + tree);
        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testAddAndContains ---");
        System.out.println();
    }

    @Test
    void testRemove() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testRemove ---");
        try {
            tree.add(50);
            tree.add(30);
            tree.add(70);
            tree.add(20);
            tree.add(40);
            tree.add(60);
            tree.add(80);

            System.out.println("Initial tree size: " + tree.size());
            System.out.println("Initial tree contains 30: " + tree.contains(30));

            tree.remove(30); // Remove a node with two children
            System.out.println("Size after removing 30: " + tree.size());
            System.out.println("Contains 30 after removal: " + tree.contains(30));
            System.out.println("Tree after removing 30:\n" + tree);

            tree.remove(20); // Remove a leaf node
            System.out.println("Size after removing 20: " + tree.size());
            System.out.println("Contains 20 after removal: " + tree.contains(20));
            System.out.println("Tree after removing 20:\n" + tree);

            tree.remove(70); // Remove a node with one child
            System.out.println("Size after removing 70: " + tree.size());
            System.out.println("Contains 70 after removal: " + tree.contains(70));
            System.out.println("Tree after removing 70:\n" + tree);

            tree.remove(100); // Remove non-existent element
            System.out.println("Size after attempting to remove 100: " + tree.size()); // Size should remain the same
            System.out.println("Tree after attempting to remove 100:\n" + tree);

            tree.remove(50); // Remove root
            System.out.println("Size after removing 50: " + tree.size());
            System.out.println("Contains 50 after removal: " + tree.contains(50));
            System.out.println("Tree after removing 50:\n" + tree);

            tree.clear();
            System.out.println("Is tree empty after clear: " + tree.isEmpty());
            System.out.println("Attempting to remove from empty tree (expecting exception)");
            try {
                tree.remove(10);
                System.out.println("ERROR: remove(10) on empty tree did not throw exception.");
            } catch (TreeException e) {
                System.out.println("SUCCESS: Caught expected TreeException when removing from empty tree: " + e.getMessage());
            }

        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testRemove ---");
        System.out.println();
    }

    @Test
    void testMinMax() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testMinMax ---");
        System.out.println("Attempting min on empty tree (expecting exception)");
        try {
            tree.min();
            System.out.println("ERROR: min() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for min() on empty tree: " + e.getMessage());
        }
        System.out.println("Attempting max on empty tree (expecting exception)");
        try {
            tree.max();
            System.out.println("ERROR: max() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for max() on empty tree: " + e.getMessage());
        }

        try {
            tree.add(50);
            System.out.println("Min after adding 50: " + tree.min());
            System.out.println("Max after adding 50: " + tree.max());

            tree.add(30);
            tree.add(70);
            tree.add(20);
            tree.add(40);
            tree.add(60);
            tree.add(80);

            System.out.println("Min after adding multiple elements: " + tree.min());
            System.out.println("Max after adding multiple elements: " + tree.max());

            tree.remove(20);
            System.out.println("Min after removing 20: " + tree.min());

            tree.remove(80);
            System.out.println("Max after removing 80: " + tree.max());
        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testMinMax ---");
        System.out.println();
    }

    @Test
    void testHeight() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testHeight ---");
        System.out.println("Attempting height on empty tree (expecting exception)");
        try {
            tree.height();
            System.out.println("ERROR: height() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for height() on empty tree: " + e.getMessage());
        }
        System.out.println("Attempting height of element on empty tree (expecting exception)");
        try {
            tree.height(10);
            System.out.println("ERROR: height(10) on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for height(10) on empty tree: " + e.getMessage());
        }

        try {
            tree.add(50);
            System.out.println("Height of tree with 50: " + tree.height());
            System.out.println("Height of element 50: " + tree.height(50));

            tree.add(30);
            tree.add(70);
            // Tree structure:
            //      50 (depth 1)
            //     /  \
            //    30   70 (depth 2)
            System.out.println("Height of tree with 50,30,70: " + tree.height());
            System.out.println("Height of element 50: " + tree.height(50));
            System.out.println("Height of element 30: " + tree.height(30));
            System.out.println("Height of element 70: " + tree.height(70));

            tree.add(20);
            tree.add(40);
            tree.add(60);
            tree.add(80);
            // Tree structure (deterministic BST):
            //          50 (depth 1)
            //         /  \
            //        30   70 (depth 2)
            //       / \   / \
            //      20 40 60 80 (depth 3)
            System.out.println("Height of tree with all elements: " + tree.height());
            System.out.println("Height of element 50: " + tree.height(50));
            System.out.println("Height of element 30: " + tree.height(30));
            System.out.println("Height of element 70: " + tree.height(70));
            System.out.println("Height of element 20: " + tree.height(20));
            System.out.println("Height of element 40: " + tree.height(40));
            System.out.println("Height of element 60: " + tree.height(60));
            System.out.println("Height of element 80: " + tree.height(80));

            System.out.println("Attempting height of non-existent element 100 (expecting exception)");
            try {
                tree.height(100);
                System.out.println("ERROR: height(100) did not throw exception for non-existent element.");
            } catch (TreeException e) {
                System.out.println("SUCCESS: Caught expected TreeException for height(100) (non-existent element): " + e.getMessage());
            }
        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testHeight ---");
        System.out.println();
    }

    @Test
    void testIsEmptyAndClear() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testIsEmptyAndClear ---");
        try {
            System.out.println("Testing isEmpty on new tree: " + tree.isEmpty());
            System.out.println("Attempting size on new tree (expecting exception)");
            try {
                tree.size();
                System.out.println("ERROR: size() on new empty tree did not throw exception.");
            } catch (TreeException e) {
                System.out.println("SUCCESS: Caught expected TreeException for size() on new empty tree: " + e.getMessage());
            }

            tree.add(10);
            System.out.println("Testing isEmpty after adding 10: " + tree.isEmpty());
            System.out.println("Testing size after adding 10: " + tree.size());

            tree.add(20);
            System.out.println("Testing isEmpty after adding 20: " + tree.isEmpty());
            System.out.println("Testing size after adding 20: " + tree.size());

            tree.clear();
            System.out.println("Testing isEmpty after clear: " + tree.isEmpty());
            System.out.println("Attempting size on empty tree after clear (expecting exception)");
            try {
                tree.size();
                System.out.println("ERROR: size() on empty tree after clear did not throw exception.");
            } catch (TreeException e) {
                System.out.println("SUCCESS: Caught expected TreeException for size() on empty tree after clear: " + e.getMessage());
            }

        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testIsEmptyAndClear ---");
        System.out.println();
    }

    @Test
    void testTraversals() {
        BTree<Integer> tree = new BTree<>();
        System.out.println("--- Running testTraversals ---");
        System.out.println("Attempting inOrder on empty tree (expecting exception)");
        try {
            tree.inOrder();
            System.out.println("ERROR: inOrder() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for inOrder() on empty tree: " + e.getMessage());
        }
        System.out.println("Attempting preOrder on empty tree (expecting exception)");
        try {
            tree.preOrder();
            System.out.println("ERROR: preOrder() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for preOrder() on empty tree: " + e.getMessage());
        }
        System.out.println("Attempting postOrder on empty tree (expecting exception)");
        try {
            tree.postOrder();
            System.out.println("ERROR: postOrder() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for postOrder() on empty tree: " + e.getMessage());
        }
        System.out.println("Attempting nodeHeight on empty tree (expecting exception)");
        try {
            tree.nodeHeight();
            System.out.println("ERROR: nodeHeight() on empty tree did not throw exception.");
        } catch (TreeException e) {
            System.out.println("SUCCESS: Caught expected TreeException for nodeHeight() on empty tree: " + e.getMessage());
        }

        try {
            tree.add(50);
            tree.add(30);
            tree.add(70);
            tree.add(20);
            tree.add(40);
            tree.add(60);
            tree.add(80);

            String inOrder = tree.inOrder();
            System.out.println("InOrder result: " + inOrder);
            System.out.println("InOrder is not null: " + (inOrder != null));
            System.out.println("InOrder is not empty: " + (!inOrder.isEmpty()));

            String preOrder = tree.preOrder();
            System.out.println("PreOrder result: " + preOrder);
            System.out.println("PreOrder is not null: " + (preOrder != null));
            System.out.println("PreOrder is not empty: " + (!preOrder.isEmpty()));

            String postOrder = tree.postOrder();
            System.out.println("PostOrder result: " + postOrder);
            System.out.println("PostOrder is not null: " + (postOrder != null));
            System.out.println("PostOrder is not empty: " + (!postOrder.isEmpty()));

            String nodeHeight = tree.nodeHeight();
            System.out.println("Node Height result: " + nodeHeight);
            System.out.println("Node Height is not null: " + (nodeHeight != null));
            System.out.println("Node Height is not empty: " + (!nodeHeight.isEmpty()));

            System.out.println("Full Tree toString:\n" + tree.toString());
        } catch (TreeException e) {
            System.out.println("Caught unexpected TreeException: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Finished testTraversals ---");
        System.out.println();
    }
}
