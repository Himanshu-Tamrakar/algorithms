package chapter3.section5.solutions;

import java.util.*;

interface ListMethod<Item> {
    public int size();
    public void addFront(Item item);
    public void addBack(Item item);
    public Item delFront();
    public Item delBack();
    public boolean contains(Item item);
    public Item delete(Item item);
    public void add(int i, Item item);
    public Item delete(int i);
}

/**
 * Not a correct implementation of add front and add back adn remove front and remove back
 * Item should implement hasCode;
 * @param <Item>
 */
public class EfficientList<Item> implements ListMethod<Item> {
    HashMap<Item, Integer> list;
    HashMap<Integer, Item> inverseList;
    int n;

    /**
     * Initializes constructor
     */
    public EfficientList() {
        this.n = 0;
        this.list = new HashMap<>();
        this.inverseList = new HashMap<>();
    }


    @Override
    public int size() {
        return n;
    }

    @Override
    public void addFront(Item item) {
        Item oldFront = inverseList.get(0);
        if (oldFront == null) {
            list.put(item, 0);
            inverseList.put(0, item);
            n++;
            return;
        }
        list.put(oldFront, n);
        inverseList.put(n, oldFront);

        inverseList.put(0, item);
        list.put(item, 0);
        n++;
    }

    @Override
    public void addBack(Item item) {
        list.put(item, n);
        inverseList.put(n, item);
        n++;
    }

    @Override
    public Item delFront() {
        Item first = inverseList.get(0);
        list.remove(first);
        inverseList.remove(0);
        n--;
        return first;
    }

    @Override
    public Item delBack() {
        Item last = inverseList.get(n-1);
        list.remove(last);
        inverseList.remove(n-1);
        n--;
        return last;
    }

    @Override
    public boolean contains(Item item) {
        return false;
    }

    @Override
    public Item delete(Item item) {
        return null;
    }

    @Override
    public void add(int i, Item item) {

    }

    @Override
    public Item delete(int i) {
        return null;
    }

    public static void main(String[] args) {
        EfficientList<String> list = new EfficientList<>();

        list.addFront("Himanshu");
        list.addBack("Anoop");
        list.addFront("Shetty");
        list.addBack("Harbu");


    }
}