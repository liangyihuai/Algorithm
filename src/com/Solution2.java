package com;

/**
 * Created by liangyh on 4/12/16.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return null;

        int tempValue = 0;
        ListNode root = new ListNode(0);
        ListNode current = root;

        while(l1 != null && l2 != null){
            int value = l1.val+l2.val + tempValue;

            if(value > 9){
                current.next = new ListNode(value%10);
                current = current.next;
                tempValue = 1;
            }else{
                current.next = new ListNode(value);
                current = current.next;
                tempValue = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            int value = l1.val + tempValue;
            if(value > 9){
                current.next = new ListNode(value%10);
                tempValue = 1;
                current = current.next;
                l1 = l1.next;
                continue;
            }
            current.next = new ListNode(value);
            current = current.next;
            tempValue = 0;
            l1 = l1.next;
        }
        while(l2 != null){
            int value = l2.val + tempValue;
            if(value > 9){
                current.next = new ListNode(value%10);
                tempValue = 1;
                current = current.next;
                l2 = l2.next;
                continue;
            }
            current.next = new ListNode(value);
            current = current.next;
            tempValue = 0;
            l2 = l2.next;
        }

        if(l1 == null && l2 == null && tempValue != 0){
            current.next = new ListNode(tempValue);
            current = current.next;
        }

        return root.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void doTest(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        n2.next = n3;
        addTwoNumbers(n1, n2);
    }

    public static void main(String[] args) {
        Solution2 test = new Solution2();
        test.doTest();
    }
}