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

    // ===================leetcode 19======================
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null)
            return head;
        if (n == 1 && head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null)
            return slow.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;
        return head;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode curr1 = head;
        ListNode midNode = getMidNode2(head);
        ListNode nhead = midNode.next;
        midNode.next = null;
        nhead = reverseList(nhead);
        ListNode curr2 = nhead;
        while (curr1 != null && curr2 != null) {
            ListNode next1 = curr1.next;
            ListNode next2 = curr2.next;
            curr1.next = curr2;
            curr2.next = next1;
            curr1 = next1;
            curr2 = next2;
        }
    }

    // =================== dummy node method for resolving previous
    // =======================
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        ListNode nhead = new ListNode(-1);
        ListNode prev = nhead;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                prev.next = curr1;
                prev = prev.next;
                curr1 = curr1.next;
            } else {
                prev.next = curr2;
                prev = prev.next;
                curr2 = curr2.next;
            }
        }
        if (curr1 != null)
            prev.next = curr1;
        if (curr2 != null)
            prev.next = curr2;
        return nhead.next;
    }

    // jha par parallel compare krenge vha pr node!=null aur jha pr next fetch
    // krenge vha pr .next!=null;
    // 2 pointor evenodd approach
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr1 = head;
        ListNode nhead = head.next;
        ListNode curr2 = head.next;

        while (curr1.next != null && curr2.next != null) {
            curr1.next = curr2.next;
            curr1 = curr2.next;

            curr2.next = curr1.next;
            curr2 = curr1.next;
        }

        curr1.next = nhead;
        return head;
    }

    // ==========================leetcode 83====================
    // jb tak duplicate aate rhe remove krte rho nhi to pointor aage bdha do
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr1 = head;

        while (curr1 != null && curr1.next != null) {
            ListNode temp = curr1.next;
            if (temp.val == curr1.val) {
                curr1.next = temp.next;
                temp.next = null;
            } else
                curr1 = curr1.next;
        }
        return head;
    }

    // fast meet krega slow se agar cycle hogi
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // =====================leetcode 1290=====================
    public int getDecimalValue(ListNode head) {
        if (head == null)
            return 0;
        int num = 0;
        while (head != null) {
            num = num << 1 | head.val;
            head = head.next;
        }
        return num;
    }

    // ============leetcode 203===============
    // good question with prev conditional jump
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return head;
        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode nhead = prev;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                ListNode temp = curr.next;
                prev.next = temp;

                curr.next = null;
                curr = temp;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
        }
        return nhead.next;
    }

    
}