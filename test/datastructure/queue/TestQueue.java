package datastructure.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

/**
 * This Junit class tests the functionality of LinkedListQueue.
 * 
 * @author Juan Ni on Feb 21, 2021
 *         Update March 11, 2021 for Lab3
 *
 */
public class TestQueue {
    private Queue<String> queue;

    @BeforeEach
    public void setup() {
        queue = new LinkedListQueue<>();
    }

    @AfterEach
    public void teardown() {
        queue = null;
    }

    @Nested
    @DisplayName("Offer Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class OfferMethodTesting {
        @Test
        @DisplayName("Normal test for offer method: insert into a none empty queue")
        @Order(1)
        public void testOfferingToNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            assertTrue(queue.offer(testStr1));
            assertTrue(queue.offer(testStr2));
            assertTrue(queue.offer(testStr3));
            assertTrue(!queue.isEmpty());

            String testStr4 = "TEST4";
            assertTrue(queue.offer(testStr4));
            assertEquals(4, queue.size());
            assertEquals(testStr1, queue.poll());
            assertEquals(testStr2, queue.element());
        }

        @Test
        @DisplayName("Edge test for offer method: insert into an empty queue")
        @Order(2)
        public void testOfferingToEmptyQueue() {
            assertTrue(queue.isEmpty());

            String testStr = "TEST";
            assertTrue(queue.offer(testStr));
            assertTrue(queue.offer(testStr));
            assertFalse(queue.isEmpty());
            assertEquals(2, queue.size());
            assertEquals(testStr, queue.element());
        }

        @Test
        @DisplayName("Edge test for offer method: insert to a queue with one element")
        @Order(3)
        public void testOfferingToOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());

            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            assertTrue(queue.offer(testStr2));
            assertTrue(queue.offer(testStr3));
            assertEquals(3, queue.size());
        }
    }

    @Nested
    @DisplayName("Poll Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class PollMethodTesting {
        @Test
        @DisplayName("Normal test for poll method in a none empty queue")
        @Order(1)
        public void testPollForNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertFalse(queue.isEmpty());

            assertEquals(3, queue.size());
            assertEquals(testStr1, queue.poll());
            assertEquals(testStr2, queue.poll());
            assertEquals(testStr3, queue.poll());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Edge test for poll method in an empty queue")
        @Order(2)
        public void testPollForEmptyQueue() {
            assertTrue(queue.isEmpty());
            assertEquals(null, queue.poll());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Edge test for poll method in a queue with one element")
        @Order(3)
        public void testPollForOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());
            assertEquals(testStr1, queue.poll());
            assertEquals(0, queue.size());
        }
    }

    @Nested
    @DisplayName("Peek Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class PeekMethodTesting {
        @Test
        @DisplayName("Normal test for peek method in a none empty queue")
        @Order(1)
        public void testPeekForNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertFalse(queue.isEmpty());

            assertEquals(testStr1, queue.peek());
            assertEquals(testStr1, queue.poll());
            assertEquals(testStr2, queue.peek());
            assertEquals(2, queue.size());
        }

        @Test
        @DisplayName("Edge test for peek method in an empty queue")
        @Order(2)
        public void testPeekForEmptyQueue() {
            assertTrue(queue.isEmpty());
            assertEquals(null, queue.peek());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Edge test for peek method in a queue with one element")
        @Order(3)
        public void testPeekForOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());

            assertEquals(testStr1, queue.peek());
            assertEquals(1, queue.size());

            Iterator<String> itr = queue.iterator();
            while (itr.hasNext()) {
                assertEquals(testStr1, itr.next());
            }
        }

    }

    @Nested
    @DisplayName("Size Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class SizeMethodTesting {

        @Test
        @DisplayName("Normal test for size method in a none empty queue")
        @Order(1)
        public void testSizeForNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertFalse(queue.isEmpty());
            assertEquals(3, queue.size());
        }

        @Test
        @DisplayName("Edge test for size method in a none empty queue")
        @Order(2)
        public void testSizeForEmptyQueue() {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Edge test for size method in a queue with one element")
        @Order(3)
        public void testSizeForOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());
        }
    }

    @Nested
    @DisplayName("IsEmpty Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class IsEmptyMethodTesting {

        @Test
        @DisplayName("Normal test for isEmpty method in a none empty queue")
        @Order(1)
        public void testIsEmptyOnNotEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);

            assertEquals(3, queue.size());
            assertEquals(false, queue.isEmpty());
        }

        @Test
        @DisplayName("Edge test for isEmpty method in an empty queue")
        @Order(2)
        public void testIsEmptyOnEmptyQueue() {
            assertEquals(0, queue.size());
            assertTrue(queue.isEmpty());
            assertEquals(true, queue.isEmpty());
        }

        @Test
        @DisplayName("Edge test for isEmpty method in a queue with one element")
        @Order(3)
        public void testIsEmptyOnOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());
            assertEquals(false, queue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Contains Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class ContainsMethodTesting {
        @Test
        @DisplayName("Normal test for contains method in a none empty queue")
        @Order(1)
        public void testContainsOnNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertFalse(queue.isEmpty());

            assertTrue(queue.contains(testStr1));
            assertTrue(queue.contains(testStr2));
            assertTrue(queue.contains(testStr3));

            Iterator<String> itr = queue.iterator();
            while (itr.hasNext()) {
                assertTrue(queue.contains(itr.next()));
            }
        }

        @Test
        @DisplayName("Edge test for contains method in an empty queue")
        @Order(2)
        public void testContainsOnEmptyQueue() {
            assertTrue(queue.isEmpty());
            assertFalse(queue.contains("TEST"));
        }

        @Test
        @DisplayName("Edge test for contains method in a queue with one element")
        @Order(3)
        public void testContainsOnOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());
            assertTrue(queue.contains(testStr1));
        }
    }

    @Nested
    @DisplayName("Remove Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class RemoveMethodTesting {
        @Test
        @DisplayName("Normal test for remove method in a none empty queue")
        @Order(1)
        public void testRemoveOnNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertEquals(3, queue.size());

            assertTrue(queue.remove(testStr1));
            assertTrue(queue.remove(testStr2));
            assertTrue(queue.remove(testStr3));
            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Edge test for remove method in an empty queue")
        @Order(2)
        public void testRemoveOnEmptyQueue() {
            assertTrue(queue.isEmpty());
            String testStr = "TEST";
            assertFalse(queue.remove(testStr));
        }

        @Test
        @DisplayName("Edge test for remove method in a queue with one element")
        @Order(3)
        public void testRemoveOnOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());

            assertTrue(queue.remove(testStr1));
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
        }
    }

    @Nested
    @DisplayName("Clear Method Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class ClearMethodTesting {
        @Test
        @DisplayName("Normal test for clear method in a none empty queue")
        @Order(1)
        public void testClearOnNoneEmptyQueue() {
            String testStr1 = "TEST1";
            String testStr2 = "TEST2";
            String testStr3 = "TEST3";
            queue.offer(testStr1);
            queue.offer(testStr2);
            queue.offer(testStr3);
            assertFalse(queue.isEmpty());

            queue.clear();
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Edge test for clear method in an empty queue")
        @Order(2)
        public void testClearOnEmptyQueue() {
            assertTrue(queue.isEmpty());
            queue.clear();
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Edge test for clear method in a queue with one element")
        @Order(3)
        public void testClearOnOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());

            queue.clear();
            assertTrue(queue.isEmpty());
        }
    }

    @Nested
    @DisplayName("Iterator Nested Testing")
    @TestMethodOrder(OrderAnnotation.class)
    class IteratorTesting {
        @Test
        @DisplayName("Normal test for Iterator next method in a none empty queue")
        @Order(1)
        public void testNextOnNoneEmptyQueue() {
            for (int i = 0; i < 5; i++) {
                String value = Integer.toString(i);
                assertTrue(queue.offer(value));
            }
            assertFalse(queue.isEmpty());
            assertEquals(5, queue.size());

            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
            while (itr.hasNext()) {
                for (int i = 0; i < 5; i++) {
                    String value = Integer.toString(i);
                    assertEquals(value, itr.next());
                }
            }
        }

        @Test
        @DisplayName("Edge test for Iterator next method in a queue with one element")
        @Order(2)
        public void testNextOnOneElementQueue() {
            String testStr1 = "TEST1";
            assertTrue(queue.offer(testStr1));
            assertEquals(1, queue.size());

            Iterator<String> itr = queue.iterator();
            while (itr.hasNext()) {
                assertEquals(testStr1, itr.next());
            }
        }

        @Test
        @DisplayName("Edge test for Iterator next method in a null queue")
        @Order(3)
        public void testNextOnEmptyQueue() {
            queue.offer(null);
            queue.offer(null);
            assertEquals(2, queue.size());

            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
            while (itr.hasNext()) {
                assertEquals(null, itr.next());
            }
        }

        @Test
        @DisplayName("Error test for Iterator next method in an empty queue")
        @Order(4)
        public void errorTestNextOnEmptyQueue() {
            assertTrue(queue.isEmpty());
            Iterator<String> itr = queue.iterator();
            assertThrows(NoSuchElementException.class, () -> itr.next());
        }

        @Test
        @DisplayName("Normal test for Iterator hasNext method in a none empty queue")
        @Order(5)
        public void testHasNextOnNoneEmptyQueue() {
            for (int i = 0; i < 5; i++) {
                String value = Integer.toString(i);
                assertTrue(queue.offer(value));
            }
            assertFalse(queue.isEmpty());
            assertEquals(5, queue.size());

            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
        }

        @Test
        @DisplayName("Edge test for Iterator hasNext method in an empty queue")
        @Order(6)
        public void testHasNextOnEmptyQueue() {
            assertTrue(queue.isEmpty());
            assertEquals(0, queue.size());
            Iterator<String> itr = queue.iterator();
            assertFalse(itr.hasNext());
        }

        @Test
        @DisplayName("Edge test for Iterator hasNext method in a queue with one element")
        @Order(7)
        public void testHasNextOnOneElementQueue() {
            String testStr1 = "TEST1";
            queue.offer(testStr1);
            assertEquals(1, queue.size());
            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
        }

        @Test
        @DisplayName("Normal test for Iterator remove method in a none empty queue")
        @Order(8)
        public void testRemoveOnNoneEmptyQueue() {
            for (int i = 0; i < 5; i++) {
                String value = Integer.toString(i);
                assertTrue(queue.offer(value));
            }
            assertFalse(queue.isEmpty());
            assertEquals(5, queue.size());

            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
            while (itr.hasNext()) {
                itr.next();
                itr.remove();
            }

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Edge test for Iterator remove method in a queue with one element")
        @Order(9)
        public void testRemoveOnOneElementQueue() {
            String testStr1 = "TEST1";
            queue.offer(testStr1);
            assertEquals(1, queue.size());

            Iterator<String> itr = queue.iterator();
            assertTrue(itr.hasNext());
            while (itr.hasNext()) {
                itr.next();
                itr.remove();
            }
            assertTrue(queue.isEmpty());
        }

        //
        @Test
        @DisplayName("Error test for Iterator remove method in a none empty queue")
        @Order(10)
        public void errorTestRemoveOnNoneEmptyQueue() {
            for (int i = 0; i < 5; i++) {
                String value = Integer.toString(i);
                assertTrue(queue.offer(value));
            }
            assertFalse(queue.isEmpty());
            assertEquals(5, queue.size());

            Iterator<String> itr = queue.iterator();
            assertThrows(IllegalStateException.class, () -> itr.remove());
        }
    }

}
