import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class ResizingDequeImplTest {
    private ResizingDequeImpl<String> empty;
    private ResizingDequeImpl<String> testDeque;
    private ResizingDequeImpl<String> givenDeque;
    private ResizingDequeImpl<String> givenDeque1;
    private ResizingDequeImpl<String> givenDeque2;
    private ResizingDequeImpl<String> givenDeque3;
    private ResizingDequeImpl<String> givenDeque4;
    Iterator<String> iterDeque;
    Iterator<String> iterDeque1;
    Iterator<String> iterDeque2;
    Iterator<String> iterDeque3;
    Iterator<String> iterDeque4;

    @Before
    public void setUpResizingDequeImplTest() {
        empty = new ResizingDequeImpl<>();
        String[] givenArr = {null, null, "1", "2"};
        givenDeque = new ResizingDequeImpl<>(givenArr, 2, 3, 2);

        String[] givenArr1 = {"2", null, null, "1"};
        givenDeque1 = new ResizingDequeImpl<>(givenArr1, 3, 1, 2);

        String[] givenArr2 = {null, null, "1", null};
        givenDeque2 = new ResizingDequeImpl<>(givenArr2, 2, 2, 1);

        String[] givenArr3 = {"2", "3", "0", "1"};
        givenDeque3 = new ResizingDequeImpl<>(givenArr3, 1, 0, 4);

        String[] givenArr4 = {null, null, null, "1"};
        givenDeque4 = new ResizingDequeImpl<>(givenArr3, 3, 3, 1);
    }

    //ResizingDequeImpl(), size(), getArray()
    @Test
    public void resizingDequeImplEmpty() {
        String[] emptyArr = new String[2];

        assertEquals(0, empty.size());
        assertArrayEquals(emptyArr, empty.getArray());
    }

    //addFirst
    @Test
    public void addFirstEmpty() {
        String[] equArr = {"1", null};

        empty.addFirst("1");
        assertArrayEquals(equArr, empty.getArray());
    }

    @Test
    public void addFirst13() {
        String[] tstArr = {"1", "3"};
        String[] resArr = {"1", "3", null, "7"};
        testDeque = new ResizingDequeImpl<>(tstArr, 0, 1, 2);

        testDeque.addFirst("7");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test
    public void addFirst1234() {
        String[] tstArr = {null, null, null, null, "1", "2", "3", "4"};
        String[] resArr = {null, null, null, "5", "1", "2", "3", "4"};
        testDeque = new ResizingDequeImpl<>(tstArr, 4, 7, 4);

        testDeque.addFirst("5");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test
    public void addFirst12() {
        String[] tstArr = {"1", "2", null, null};
        String[] resArr = {"1", "2", null, "3"};
        testDeque = new ResizingDequeImpl<>(tstArr, 0, 1, 2);

        testDeque.addFirst("3");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    //addLast()
    @Test
    public void addLastEmpty() {
        String[] resArr = {"1", null};
        empty.addLast("1");
        assertArrayEquals(resArr, empty.getArray());
    }

    @Test
    public void addLast1243h0t3() {
        String[] tstArr = {"1", "2", "4", "3"};
        String[] resArr = {"1", "2", "4", "3", "9", null, null, null};
        testDeque = new ResizingDequeImpl<>(tstArr, 0, 3, 4);

        testDeque.addLast("9");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test
    public void addLast1243h1t0() {
        String[] tstArr = {"1", "2", "4", "3"};
        String[] resArr = {"2", "4", "3", "1", "9", null, null, null};
        testDeque = new ResizingDequeImpl<>(tstArr, 1, 0, 4);

        testDeque.addLast("9");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test
    public void addLast1234() {
        String[] tstArr = {null, null, null, null, "1", "2", "3", "4"};
        String[] resArr = {"5", null, null, null, "1", "2", "3", "4"};
        testDeque = new ResizingDequeImpl<>(tstArr, 4, 7, 4);

        testDeque.addLast("5");
        assertArrayEquals(resArr, testDeque.getArray());
    }

    //pollFirst()
    @Test
    public void pollFirst1() {
        String[] tstArr = {null, "1", null, null};
        String[] resArr = {null, null};
        testDeque = new ResizingDequeImpl<>(tstArr, 1, 1, 1);

        testDeque.pollFirst();
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void pollFirstErrorEmpty() {
        empty.pollFirst();
    }

    //pollLast()
    @Test
    public void pollLast12() {
        String[] tstArr = {null, null, null, null, "1", "2", null, null};
        String[] resArr = {"1", null, null, null};
        testDeque = new ResizingDequeImpl<>(tstArr, 4, 5, 2);

        testDeque.pollLast();
        assertArrayEquals(resArr, testDeque.getArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void pollLastErrorEmpty() {
        empty.pollLast();
    }

    //peekFirst()
    @Test
    public void peekFirstGivenDeque() {
        assertEquals("1", givenDeque.peekFirst());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekFirstErrorEmpty() {
        empty.peekFirst();
    }

    //peekLast()
    @Test
    public void peekLastGivenDeque() {
        assertEquals("2", givenDeque.peekLast());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekLastErrorEmpty() {
        empty.peekLast();
    }

    //Iterator.hasNext()
    @Test
    public void iteratorHasNextGivenDeque() {
        iterDeque = givenDeque.iterator();
        assertTrue(iterDeque.hasNext());
        iterDeque.next();
        assertTrue(iterDeque.hasNext());
        iterDeque.next();
        assertFalse(iterDeque.hasNext());
    }

    @Test
    public void iteratorHasNextGivenDeque1() {
        iterDeque1 = givenDeque1.iterator();
        assertTrue(iterDeque1.hasNext());
        iterDeque1.next();
        assertTrue(iterDeque1.hasNext());
        iterDeque1.next();
        assertFalse(iterDeque1.hasNext());
    }

    @Test
    public void iteratorHasNextGivenDeque2() {
        iterDeque2 = givenDeque2.iterator();
        assertTrue(iterDeque2.hasNext());
        iterDeque2.next();
        assertFalse(iterDeque2.hasNext());
    }

    @Test
    public void iteratorHasNextGivenDeque3() {
        iterDeque3 = givenDeque3.iterator();
        assertTrue(iterDeque3.hasNext());
        iterDeque3.next();
        assertTrue(iterDeque3.hasNext());
        iterDeque3.next();
        assertTrue(iterDeque3.hasNext());
        iterDeque3.next();
        assertTrue(iterDeque3.hasNext());
        iterDeque3.next();
        assertFalse(iterDeque3.hasNext());
    }

    @Test
    public void iteratorHasNextGivenDeque4() {
        iterDeque4 = givenDeque4.iterator();
        assertTrue(iterDeque4.hasNext());
        iterDeque4.next();
        assertFalse(iterDeque4.hasNext());
    }

    @Test
    public void iteratorHasNextEmpty() {
        assertFalse(empty.iterator().hasNext());
    }

    //Iterator.next()
    @Test
    public void iteratorNextGivenDeque() {
        iterDeque = givenDeque.iterator();
        assertEquals("1", iterDeque.next());
        assertEquals("2", iterDeque.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextErrorGivenDeque() {
        iterDeque = givenDeque.iterator();
        iterDeque.next();
        iterDeque.next();
        iterDeque.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextEmpty() {
        empty.iterator().next();
    }
}

