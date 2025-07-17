package DataStructure;

import java.util.Collection;
import java.util.Iterator;

public class ListNode {
      public int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      public static ListNode createListNode(int[] nums) {
            if (nums == null || nums.length == 0) {
                  return null;
            }
            ListNode head = new ListNode(nums[0]);
            ListNode current = head;
            for (int i = 1; i < nums.length; i++) {
                  current.next = new ListNode(nums[i]);
                  current = current.next;
            }
            return head;
      }

      // 或者根据Collection创建链表
      public static ListNode createListNode(Collection<Integer> nums) {
            if (nums == null || nums.isEmpty()) {
                  return null;
            }
            Iterator<Integer> iterator = nums.iterator();
            ListNode head = new ListNode(iterator.next());
            ListNode current = head;
            while (iterator.hasNext()) {
                  current.next = new ListNode(iterator.next());
                  current = current.next;
            }
            return head;
      }
}