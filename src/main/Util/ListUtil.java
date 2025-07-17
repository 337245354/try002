package Util;

import DataStructure.ListNode;

public class ListUtil {

    public static ListNode arrayToList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static int[] listToArray(ListNode head) {
        int[] temp = new int[100];
        int count = 0;
        while (head != null) {
            temp[count++] = head.val;
            head = head.next;
        }
        int[] result = new int[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }

}
