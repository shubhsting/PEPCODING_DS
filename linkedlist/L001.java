public class L001 {
    public static class linkedlist {
        public class Node {
            int data = 0;
            Node next = null;

            Node(int data) {
                this.data = data;
            }
        }

        Node head = null;
        Node tail = null;
        Node size = 0;

        // ========================== general util ===============
        public int size() {
            return this.size();
        }

        public int isEmpty() {
            return this.size() == 0;
        }

        @Override
        public String toString() {
            Node curr = this.head;
            String str = "";
            while (curr != null) {
                str += curr.data + " ";
                curr = curr.next;
            }
            return str;
        }

        public Node getNodeAt(int idx) {
            Node curr = this.head;
            while (idx-- > 0)
                curr = curr.next;
            return curr;
        }

        private void addFirstNode(Node node) {
            if (this.head == null && this.tail == null) {
                this.head = node;
                this.tail = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
            this.size++;
        }

        public void addFirst(int data) {
            Node node = new Node(data);
            addFirstNode(node);
        }

        private void addLastNode(Node node) {
            if (this.head == null && this.tail == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                this.tail = node;
            }
            this.size++;
        }

        public void addLast(int data) {
            Node node = new Node(data);
            addLastNode(node);
        }

        private void addNodeAt(Node node, int pos) {
            if (pos == 0)
                addFirstNode(node);
            else if (pos == this.size())
                addLastNode(node);
            else {
                Node prev = getNodeAt(pos - 1);

                Node temp = prev.next;
                prev.next = node;
                node.next = temp;
            }
            this.size++;
        }

        public void addAt(int data, int pos) {
            if (pos < 0 || pos > this.size()) {
                System.out.println("Invalid Input");
                return;
            }
            Node node = new Node(data);
            addNodeAt(node, pos);
        }

        // ======================remove function========================

        public int removeFirst() {

            if (this.size() == 0)
                System.out.println("LinkedList Empty");

            Node node = this.head;
            if (this.size() == 1) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.next;
                node.next = null;
            }
            this.size--;
            return node.data;

        }

        public int removeLast() {
            if (this.size() == 0)
                System.out.println("Empty List");
            Node node = this.tail;
            if (this.size() == 1) {
                this.head = null;
                this.tail = null;
            } else {
                Node secondLastNode = getNodeAt(this.size() - 2);
                secondLastNode.next = null;
                this.tail = secondLastNode;
            }
            this.size--;
            return node.data;
        }

        public int removeAt(int idx) {
            if (idx < 0 || idx >= this.size()) {
                System.out.println("Invalid index");
                return -1;
            }
            if (idx == 0)
                return removeFirst();
            else if (idx == this.size() - 1)
                return removeFirst();
            else {
                Node prev = getNodeAt(idx - 1);
                Node curr = prev.next;
                prev.next = curr.next;
                curr.next = null;
                this.size--;
                return curr.data;

            }
        }

        // ======================get function====================

        public int getFirst() {
            if (this.size == 0)
                return -1;
            return this.head.data;
        }

        public int getLast() {
            if (this.size == 0)
                return -1;
            return this.tail.data;
        }

        public int getAt(int idx) {
            if (idx < 0 || idx >= this.size())
                return -1;
            return getNodeAt(idx).data;
        }
    }
}