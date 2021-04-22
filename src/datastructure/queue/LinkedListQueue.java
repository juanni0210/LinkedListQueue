package datastructure.queue;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This is Lab 3 for CST8288 - implement a queue using LinkedList.
 * It is to create implementation of java.util.Queue using a DoublyLinkedList
 * rules.
 * Finished on March 10, 2021
 * Edited on March 19, 2021
 * 
 * @author Juan Ni
 * 
 *
 * @param <R> The type of elements held in this queue
 */
public class LinkedListQueue<R> extends AbstractQueue<R> {
    private Node<R> head;
    private Node<R> tail;
    private int size;

    class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        private Node(T value) {
            this(value, null, null);
        }

        private Node(Node<T> next, Node<T> prev) {
            this(null, next, prev);
        }

        private Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /**
     * Adds the specified element as the tail(last element) of this queue.
     */
    @Override
    public boolean offer(R r) {
        Node<R> temp = new Node<R>(r);
        if (isEmpty()) {
            head = tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }

        size++;
        return true;
    }

    /**
     * Retrieves and removes the head (first element) of this queue or returns null
     * if this queue is empty.
     */
    @Override
    public R poll() {
        if (isEmpty()) {
            return null;
        }
        Node<R> temp = head;
        removeOneNode(head);
        return temp.value;
    }


    /**
     * Retrieves, but does not remove, the head (first element) of this queue, or
     * returns null if this queue is empty.
     */
    @Override
    public R peek() {
        if (isEmpty()) {
            return null;
        }
        return head.value;
    }

    /**
     * Returns the number of elements in this queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this queue contains no elements.
     */
    @Override
    public boolean isEmpty() {
        //if there is no head, the list is empty
        return size == 0;
    }

    /**
     * Returns true if this queue contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        Node<R> currentNode = head;

        if (isEmpty()) {
            return false;
        }

        if (head.value.equals(o)) {
            return true;
        }


        while (currentNode != tail) {
            currentNode = currentNode.next;
            if (currentNode.value.equals(o)) {
                return true;
            }
        }


        return false;
    }


    /**
     * Removes a single instance of the specified element from this queue if it is
     * present. Returns true if an element was removed as a result of this call.
     */
    @Override
    public boolean remove(Object o) {
        Node<R> currentNode = head;
        if (!contains(o)) {
            return false;
        } else {
            if (head.value.equals(o)) {
                removeOneNode(head);
                return true;
            }
            while (currentNode != tail) {
                currentNode = currentNode.next;
                if (currentNode.value.equals(o)) {
                    removeOneNode(currentNode);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes all of the elements from this queue. The queue will be empty after
     * this method returns.
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Removes a specific node from this queue. The size of the queue will be
     * decremented by 1 when one node is removed.
     * 
     * @param node node that will be removed from the queue
     */
    private void removeOneNode(Node<R> node) {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty..");
        }
        if (size() == 1) {
            head = tail = null;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
        }
        size--;
    }


    /**
     * Returns an iterator over the elements in this queue.
     */
    @Override
    public Iterator<R> iterator() {
        if (isEmpty()) {
            return Collections.<R>emptyList().iterator();
        }

        return new Iterator<R>() {

            private Node<R> currentNode = null;
            private Node<R> returnNode = null;

            /**
             * Returns true if the iteration has more elements.
             */
            @Override
            public boolean hasNext() {
                return returnNode != tail;
            }

            /**
             * Returns the next element in the iteration.
             */
            @Override
            public R next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (currentNode == null) {
                    currentNode = head;
                }

                //keep another variable called returnedNode
                returnNode = currentNode;
                currentNode = currentNode.next;

                //return the value of last node
                return returnNode.value;
            }

            /**
             * Removes from the underlying queue the last element returned by this
             * iterator. This method can be called only once per call to next().
             */
            @Override
            public void remove() {
                if (returnNode == null) {
                    throw new IllegalStateException();
                } else {
                    //remove returnNode
                    removeOneNode(returnNode);
                    //then set returnNode to null
                    returnNode = null;
                }
            }
        };
    }
}
