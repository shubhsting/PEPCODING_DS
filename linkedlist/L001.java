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

    }
}