package org.example;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }
    @Test
    void testSizeAndAddMethods() {
        assertEquals(0, list.size());
        list.addFirst(10);
        assertEquals(1, list.size());
        list.addLast(20);
        assertEquals(2, list.size());
    }

    @Test
    void testGetMethods() {
        list.addLast(5);
        list.addLast(15);
        list.addLast(25);
        assertEquals(5, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(25, list.get(2));

        assertEquals(5, list.getFirst());
        assertEquals(25, list.getLast());
    }

    @Test
    void testAddByIndex() {
        list.addLast(1);
        list.addLast(3);
        list.add(1, 2);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testRemoveFirst() {
        list.addLast(100);
        list.addLast(200);
        int removed = list.removeFirst();
        assertEquals(100, removed);
        assertEquals(1, list.size());
        assertEquals(200, list.getFirst());

        list.removeFirst();
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    void testRemoveLast() {
        list.addLast(300);
        list.addLast(400);
        int removed = list.removeLast();
        assertEquals(400, removed);
        assertEquals(1, list.size());
        assertEquals(300, list.getLast());


        list.removeLast();
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void testRemoveByIndex() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        int removed = list.remove(2);
        assertEquals(30, removed);
        assertEquals(3, list.size());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(40, list.get(2));
    }

    @Test
    void testExceptionForInvalidIndex() {
        list.addLast(5);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testToStringEmptyAndNonEmpty() {

        assertEquals("[]", list.toString());

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals("[1, 2, 3]", list.toString());
    }
}
