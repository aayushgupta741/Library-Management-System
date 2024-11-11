public class CustomDataStructures {

    // Custom LinkedList class implementation
    public static class CustomLinkedList {
        private Node head;

        // Node class to represent each element in the list
        private static class Node {
            Object data;
            Node next;

            public Node(Object data) {
                this.data = data;
                this.next = null;
            }
        }

        // Add an element at the end of the list
        public void add(Object data) {
            if (data == null) {
                throw new IllegalArgumentException("Cannot add null element to the list");
            }
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        // Remove an element from the start of the list
        public Object remove() {
            if (head == null) {
                throw new IllegalStateException("Cannot remove from an empty list");
            }
            Object data = head.data;
            head = head.next;
            return data;
        }

        // Print all elements in the list
        public void printList() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            Node current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }

        // Check if the list is empty
        public boolean isEmpty() {
            return head == null;
        }
    }

    // Custom Stack class implementation
    public static class CustomStack {
        private Node top;

        // Node class for stack
        private static class Node {
            Object data;
            Node next;

            public Node(Object data) {
                this.data = data;
                this.next = null;
            }
        }

        // Push an element onto the stack
        public void push(Object data) {
            if (data == null) {
                throw new IllegalArgumentException("Cannot push null onto the stack");
            }
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
        }

        // Pop an element from the stack
        public Object pop() {
            if (top == null) {
                throw new IllegalStateException("Cannot pop from an empty stack");
            }
            Object data = top.data;
            top = top.next;
            return data;
        }

        // Peek the top element without removing it
        public Object peek() {
            if (top == null) {
                throw new IllegalStateException("Cannot peek on an empty stack");
            }
            return top.data;
        }

        // Check if the stack is empty
        public boolean isEmpty() {
            return top == null;
        }
    }

    // Custom Queue class implementation
    public static class CustomQueue {
        private Node front, rear;

        // Node class for queue
        private static class Node {
            Object data;
            Node next;

            public Node(Object data) {
                this.data = data;
                this.next = null;
            }
        }

        // Enqueue an element to the queue
        public void enqueue(Object data) {
            if (data == null) {
                throw new IllegalArgumentException("Cannot enqueue null element");
            }
            Node newNode = new Node(data);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }

        // Dequeue an element from the queue
        public Object dequeue() {
            if (front == null) {
                throw new IllegalStateException("Cannot dequeue from an empty queue");
            }
            Object data = front.data;
            front = front.next;
            if (front == null) {
                rear = null; // Queue is empty now
            }
            return data;
        }

        // Peek the front element without removing it
        public Object peek() {
            if (front == null) {
                throw new IllegalStateException("Cannot peek on an empty queue");
            }
            return front.data;
        }

        // Check if the queue is empty
        public boolean isEmpty() {
            return front == null;
        }
    }
}
