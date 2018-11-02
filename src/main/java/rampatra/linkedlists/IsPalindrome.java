package rampatra.linkedlists;

import com.rampatra.common.LinkedStack;
import com.rampatra.common.SingleLinkedList;
import com.rampatra.common.SingleLinkedNode;
import com.rampatra.common.Stack;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ramswaroop
 * @date: 6/18/15
 * @time: 2:35 PM
 */
public class IsPalindrome<E extends Comparable<E>> extends SingleLinkedList<E> {

    /**
     * Uses Stack to test whether a linked list starting
     * from {@param node} is palindrome or not.
     *
     * @param list
     * @return
     */
    public static <E extends Comparable<E>> boolean isPalindrome(SingleLinkedList<E> list) {
        SingleLinkedNode<E> head = list.getNode(0);
        SingleLinkedNode<E> curr = head;
        Stack<SingleLinkedNode<E>> stack = new LinkedStack<>();

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = head;

        while (curr != null) {
            if (curr.item != stack.pop().item) {
                return false;
            }
            curr = curr.next;
        }

        return true;
    }

    /**
     * Recursive function to test whether a linked list
     * starting from {@param node} is palindrome or not.
     * <p/>
     * NOTE: This method moves the head reference. (disadvantage)
     *
     * @param node
     * @return
     */
    public boolean isPalindromeRecursive(SingleLinkedNode<E> node) {
        if (node == null) return true;

        boolean isPalindrome = isPalindromeRecursive(node.next);

        if (head.item == node.item) {
            head = head.next;
            return isPalindrome;
        } else {
            return false;
        }
    }

    public static void main(String a[]) {
        IsPalindrome<Integer> linkedList = new IsPalindrome<>();
        linkedList.add(00);
        linkedList.add(11);
        linkedList.add(22);
        linkedList.add(11);
        linkedList.add(00);
        linkedList.printList();
        System.out.println(isPalindrome(linkedList));
        linkedList.printList();
        System.out.println(linkedList.isPalindromeRecursive(linkedList.getNode(0)));
    }
}
