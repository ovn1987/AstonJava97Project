package collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomCollectionTest {

    @Test
    void testSize0() {
        List<Object> list = new List<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void testAddAndGet() {
        List<Object> list = new List<>();
        list.add(3);
        Assertions.assertEquals(3, list.get(0));
    }

    @Test
    void testSize() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void testAddAll() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        List<Object> list2 = new List<>();
        list2.add(5);
        list2.add(6);
        list.addAll(list2);
        Assertions.assertEquals(3, list.get(0));
        Assertions.assertEquals(4, list.get(1));
        Assertions.assertEquals(5, list.get(2));
        Assertions.assertEquals(6, list.get(3));
        Assertions.assertEquals(4, list.size());
    }

    @Test
    public void testAddMiddle() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(5);
        list.add(1, 4);
        Assertions.assertEquals(4, list.get(1));
        Assertions.assertEquals(5, list.get(2));
    }

    @Test
    public void testSet() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        list.set(1, 5);
        Assertions.assertEquals(5, list.get(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRemoveByIndex() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(1);
        Assertions.assertEquals(5, list.get(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRemoveObject() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(Integer.valueOf(5));
        Assertions.assertEquals(4, list.get(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testSubList() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        List<Object> subList = list.subList(1, 3);
        Assertions.assertEquals(4, subList.get(0));
        Assertions.assertEquals(5, subList.get(1));
        Assertions.assertEquals(2, subList.size());
    }

    @Test
    public void testCopyOf() {
        List<Object> list = new List<>();
        list.add(3);
        list.add(4);
        List<Object> listCopy = List.copyOf(list);
        Assertions.assertEquals(3, listCopy.get(0));
        Assertions.assertEquals(4, listCopy.get(1));
        Assertions.assertEquals(2, listCopy.size());
    }

}
