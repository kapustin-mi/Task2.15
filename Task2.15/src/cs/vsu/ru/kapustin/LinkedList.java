package cs.vsu.ru.kapustin;

import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedList<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    public LinkedList() {
    }

    public LinkedList(Collection<? extends T> collection) {
        addAll(collection);
    }

    public void addAll(Collection<? extends T> c) {
        for (T element : c) {
            if (element != null) {
                addLast(element);
            }
        }
    }

    public void addFirst(T elem) {
        first = new Node<>(elem, first);
        if (size == 0) {
            last = first;
        }
        size++;
    }

    public void addLast(T elem) {
        Node<T> newLast = new Node<>(elem, first);
        if (size == 0) {
            first = newLast;
        } else {
            last.next = newLast;
        }
        last = newLast;
        size++;
    }

    public void add(int index, T elem) {
        checkPositionIndex(index);

        if (index == size) {
            addLast(elem);
        } else if (index == 0) {
            addFirst(elem);
        } else {
            Node<T> nodeBefore = node(index - 1);
            nodeBefore.next = new Node<>(elem, nodeBefore.next);
            size++;
        }
    }

    public void removeFirst() {
        checkEmpty();
        if (size == 1) {
            last = null;
            first = null;
        } else {
            first = first.next;
            last.next = first;
        }
        size--;
    }

    public void removeLast() {
        checkEmpty();
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = node(size - 2);
            last.next = first;
        }
        size--;
    }

    public void remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            removeFirst();
        } else {
            Node<T> nodeBefore = node(index - 1);
            remove(nodeBefore);
        }
    }

    public void remove(Node<T> nodeBefore) {
        checkNode(nodeBefore);

        if (nodeBefore.next == last) {
            removeLast();
        } else if (nodeBefore == last) {
            removeFirst();
        } else {
            nodeBefore.next = nodeBefore.next.next;
            size--;
        }
    }

    private Node<T> node(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        checkElementIndex(index);
        return node(index).value;
    }

    public T getFirst() {
        checkEmpty();
        return first.value;
    }

    public T getLast() {
        checkEmpty();
        return last.value;
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkNode(Node<T> checkedNode) {
        checkEmpty();

        Node<T> node = first;
        int numberOfPassesNodes = 0;

        for (int i = 0; i < size - 1; i++) {
            if (checkedNode != node) {
                numberOfPassesNodes++;
            } else {
                break;
            }
            node = node.next;
        }

        if (numberOfPassesNodes == size) {
            throw new NoSuchElementException();
        }
    }

    private static class Node<T> {
        T value;
        int serialNumber;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public int findLast(int numbOfRemovedElem) {
        if (numbOfRemovedElem > size || numbOfRemovedElem <= 0) {
            return -1;
        }

        initSerialNumbers();
        Node<T> node = last;

        while (size != 1) {
            for (int numberOfPasses = 1; numberOfPasses < numbOfRemovedElem; numberOfPasses++) {
                node = node.next;
            }
            remove(node);
        }

        return first.serialNumber;
    }

    private void initSerialNumbers() {
        Node<T> node;
        int serialNumber = 1;

        for (node = first; serialNumber <= size; node = node.next) {
            node.serialNumber = serialNumber;
            serialNumber++;
        }
    }
}
