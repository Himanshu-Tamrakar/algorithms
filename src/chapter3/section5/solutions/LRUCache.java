package chapter3.section5.solutions;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.HashSet;

interface Cache<Item> {
    public void access(Item item);
    public Item remove() throws OperationNotSupportedException;
}
public class LRUCache<Item> implements Cache<Item> {
    private HashMap<Item, Node> st;
    private Node<Item> first, last;

    public LRUCache() {
        this.st = new HashMap<>();
        this.first = null;
        this.last = null;
    }

    @Override
    public void access(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }

        if (first == null && last == null) {
            first = last = new Node<>(item);
            st.put(item, first);
            return;
        }

        if (!st.containsKey(item)) {
            Node oldFirst = first;
            first = new Node<>(item);
            first.next = oldFirst;
            oldFirst.previous = first;
            st.put(item, first);
            return;
        }

        Node node = st.get(item);
        Item f = (Item) node.item;
        node.item = first.item;
        first.item = f;
    }

    /**
     * As per this development, least recent is in the last
     * @return
     */
    @Override
    public Item remove() {
        if (last == null) {
            throw new IllegalArgumentException("List is empty");
        }

        if (first == last) {
            Node leastRecent = first;
            first = last = null;
            return (Item) leastRecent.item;
        }
        Node leastRecent = last;
        last = last.previous;
        st.remove(leastRecent.item);
        return (Item) leastRecent.item;
    }

    private static class Node<Item> {
        Item item;
        Node next, previous;

        public Node(Item item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        LRUCache<String> collections = new LRUCache<>();

        collections.access("Himanshu");
        collections.access("Aman");
        collections.access("Anoop");
        collections.access("Shetty");
        System.out.printf("least recent access %s\n", collections.remove());
        collections.access("Himanshu");
        System.out.printf("least recent access %s\n", collections.remove());
        System.out.printf("least recent access %s\n", collections.remove());
        System.out.printf("least recent access %s\n", collections.remove());
        System.out.printf("least recent access %s\n", collections.remove());
    }

}
