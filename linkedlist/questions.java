public class questions {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // ====================Trick one:slow fast method=======================
    // agar 2 middle honge to ye doosre wala dega
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // agar 2 middle honge to ye phlr wala dega
    public ListNode getMidNode2(ListNode node) {
        if (node == null || node.next == null)
            return node;
        ListNode slow = node;
        ListNode fast = node;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // =================== 2 pointer approach=====================

    public ListNode reverseList(ListNode node) {
        if (node == null || node.next == null)
            return node;
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    // data ke base pr reverse krna hai
    public void reverseListData(ListNode node) {
        if (node == null || node.next == null)
            return;
        ListNode curr1 = node;
        ListNode midNode = middleNode(node);
        ListNode nhead = midNode.next;
        midNode.next = null;
        nhead = reverseList(nhead);
        ListNode curr2 = nhead;
        while (curr1 != null && curr2 != null) {
            int temp = curr1.val;
            curr1.val = curr2.val;
            curr2.val = temp;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        nhead = reverseList(nhead);
        midNode.next = nhead;

    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode curr1 = head;
        ListNode midNode = getMidNode2(head);
        ListNode nhead = midNode.next;
        midNode.next = null;
        nhead = reverseList(nhead);
        ListNode curr2 = nhead;
        while (curr1 != null && curr2 != null) {
            if (curr1.data != curr2.data)
                return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        nhead = reverseList(nhead);
        midNode.next = nhead;
        return true;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null)
            return head;
        if (n == 1 && head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

    }
}