//Isaac Lichter
package myarrayliststring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


//I don't know why, but all my tests are throwing NullPointerExceptions


public class MyArrayListTest {
    
    MyArrayList instance;

    public MyArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
         instance = new MyArrayList<String>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd_String() {
        System.out.println("add");
        String e = "word";
        boolean expResult = true;
        boolean result = instance.add(e);
        assertEquals(expResult, result);
    }

    @Test
    public void testSize() {
        System.out.println("size");
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.add("asdfa");
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    @Test
    public void testContains() {
        System.out.println("contains");
        Object o = "word";
        boolean expResult = false;
        boolean result = instance.contains(o);
        assertEquals(expResult, result);

        instance.add("word");
        expResult = true;
        result = instance.contains(o);
        assertEquals(expResult, result);
    }

    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        instance.add("word");
        String expResult = "word";
        Object result = instance.get(index);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        Object o = "word";
        boolean expResult = false;
        boolean result = instance.remove(o);
        assertEquals(expResult, result);

        instance.add("word");
        instance.add("wordTwo");
        expResult = true;
        result = instance.remove(o);
        assertEquals(expResult, result);

        String expNewValueAtZero = "wordTwo";
        Object newValueAtZero = instance.get(0);
        assertEquals(expNewValueAtZero, newValueAtZero);
    }

    @Test
    public void testClear() {
        System.out.println("clear");
        instance.add("word");
        instance.clear();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    @Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        String element = "newWord";
        instance.add("word");
        String expOldString = "word";
        Object oldString = instance.set(index, element);
        assertEquals(expOldString, oldString);

        String expNewString = "newWord";
        Object newString = instance.get(index);
        assertEquals(expNewString, newString);
    }

    @Test
    public void testAdd_int_String() {
        System.out.println("add");
        int index = 1;
        String element = "addedWord";
        instance.add("word1");
        instance.add("word2");
        instance.add(index, element);

        String expResult = "addedWord";
        Object result = instance.get(index);
        assertEquals(expResult, result);

        expResult = "word2";
        index = 2;
        result = instance.get(index);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int index = 1;
        instance.add("word1");
        instance.add("word2");
        instance.add("word3");
        String expResult = "word2";
        Object result = instance.remove(index);
        assertEquals(expResult, result);

        expResult = "word1";
        index = 0;
        result = instance.get(index);
        assertEquals(expResult, result);

        expResult = "word3";
        index = 1;
        result = instance.get(index);
        assertEquals(expResult, result);
    }

    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Object o = "word";
        instance.add("word");
        instance.add("word");
        int expResult = 0;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }

    @Test
    public void testLastIndexOf() {
        System.out.println("lastIndexOf");
        Object o = "word";
        instance.add("word");
        instance.add("word");
        int expResult = 1;
        int result = instance.lastIndexOf(o);
        assertEquals(expResult, result);
    }

    //HW5
    @Test
    public void testContainsAll() {
        ArrayList<String> array = new ArrayList<>();
        array.add("word1");
        array.add("word2");
        array.add("word2");
        instance.add("word1");
        instance.add("word1");
        instance.add("word2");
        boolean expResult = true;
        boolean result = instance.containsAll(array);
        assertEquals(expResult, result);

        array.add("word3");
        expResult = false;
        result = instance.containsAll(array);
        assertEquals(expResult, result);
    }

    //HW5
    @Test
    public void testAddAll_Collection() {
        
        ArrayList<String> array = new ArrayList<>();
        boolean expResult = false;
        boolean result = instance.addAll(array);
        assertEquals(expResult, result);
        
        array.add("word1");
        array.add("word2");
        
        expResult = true;
        result = instance.addAll(array);
        assertEquals(expResult, result);
        
        assertEquals(2, instance.size());
    }

    //HW5
    @Test
    public void testAddAll_int_Collection() {
        ArrayList<String> array = new ArrayList<>();
        boolean expResult = false;
        boolean result = instance.addAll(0, array);
        assertEquals(expResult, result);
        
        array.add("word1");
        array.add("word2");
        
        instance.add("word3");
        instance.add("word3");
        expResult = true;
        result = instance.addAll(1, array);
        assertEquals(expResult, result);
        
        assertEquals(4, instance.size());
        assertEquals("word1", instance.get(1));
    }

    //HW5
    @Test
    public void testRemoveAll() {
        ArrayList<String> array = new ArrayList<>();
        instance.add("word1");
        instance.add("word1");
        instance.add("word2");
        array.add("word3");
        boolean expResult = false;
        boolean result = instance.removeAll(array);
        assertEquals(expResult, result);
        
        array.add("word1");
        expResult = true;
        result = instance.removeAll(array);
        assertEquals(expResult, result);
        assertEquals(1, instance.size());
    }

    @Test
    public void testRetainAll() {
        ArrayList<String> array = new ArrayList<>();
        instance.add("word1");
        instance.add("word1");
        instance.add("word2");
        array.add("word1");
        array.add("word2");
        boolean expResult = false;
        boolean result = instance.retainAll(array);
        assertEquals(expResult, result);
        
        array.remove("word1");
        expResult = true;
        result = instance.retainAll(array);
        assertEquals(expResult, result);
        assertEquals(1, instance.size());
    }

    //HW7
    @Test
    public void testIterator() {
        System.out.println("iterator");
        MyArrayList instance = new MyArrayList();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class MyArrayList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Object e = null;
        MyArrayList instance = new MyArrayList();
        boolean expResult = false;
        boolean result = instance.add(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class MyArrayList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int index = 0;
        Object element = null;
        MyArrayList instance = new MyArrayList();
        instance.add(index, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class MyArrayList.
     */
    @Test
    public void testToArray_0args() {
        System.out.println("toArray");
        MyArrayList instance = new MyArrayList();
        Object[] expResult = null;
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toArray method, of class MyArrayList.
     */
    @Test
    public void testToArray_GenericType() {
        System.out.println("toArray");
        Object[] a = null;
        MyArrayList instance = new MyArrayList();
        Object[] expResult = null;
        Object[] result = instance.toArray(a);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subList method, of class MyArrayList.
     */
    @Test
    public void testSubList() {
        System.out.println("subList");
        int fromIndex = 0;
        int toIndex = 0;
        MyArrayList instance = new MyArrayList();
        List expResult = null;
        List result = instance.subList(fromIndex, toIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listIterator method, of class MyArrayList.
     */
    @Test
    public void testListIterator_0args() {
        System.out.println("listIterator");
        MyArrayList instance = new MyArrayList();
        ListIterator expResult = null;
        ListIterator result = instance.listIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listIterator method, of class MyArrayList.
     */
    @Test
    public void testListIterator_int() {
        System.out.println("listIterator");
        int index = 0;
        MyArrayList instance = new MyArrayList();
        ListIterator expResult = null;
        ListIterator result = instance.listIterator(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
