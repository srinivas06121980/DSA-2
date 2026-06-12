import java.util.HashMap;

class Node {
    int orderId;
    int price;
    Node left, right;

    Node(int orderId, int price) {
        this.orderId = orderId;
        this.price = price;
        left = right = null;
    }
}

public class NSEOrderBook {

    static HashMap<Integer, Integer> orderMap = new HashMap<>();

    static Node insert(Node root, int orderId, int price) {
        if (root == null)
            return new Node(orderId, price);

        if (price < root.price)
            root.left = insert(root.left, orderId, price);
        else
            root.right = insert(root.right, orderId, price);

        return root;
    }

    static Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    static Node delete(Node root, int price) {
        if (root == null)
            return null;

        if (price < root.price)
            root.left = delete(root.left, price);
        else if (price > root.price)
            root.right = delete(root.right, price);
        else {
            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            Node temp = minValueNode(root.right);

            root.price = temp.price;
            root.orderId = temp.orderId;

            root.right = delete(root.right, temp.price);
        }
        return root;
    }

    static int peekBestBid(Node root) {
        if (root == null)
            return -1;

        while (root.right != null)
            root = root.right;

        return root.price;
    }

    public static void main(String[] args) {

        Node root = null;

        int[][] orders = {
                {101, 2980},
                {102, 2965},
                {104, 2985},
                {105, 2970},
                {107, 2978},
                {109, 2982},
                {111, 2972},
                {113, 2986},
                {114, 2975}
        };

        // Insert Orders
        for (int[] order : orders) {
            root = insert(root, order[0], order[1]);
            orderMap.put(order[0], order[1]);
        }

        System.out.println("Best Bid: ₹" + peekBestBid(root));

        // Cancel Order #107
        root = delete(root, orderMap.get(107));
        orderMap.remove(107);

        // Cancel Order #105
        root = delete(root, orderMap.get(105));
        orderMap.remove(105);

        System.out.println("Best Bid After Cancellation: ₹" + peekBestBid(root));
    }
}