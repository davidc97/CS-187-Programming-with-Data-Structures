package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListInterfaceTest {

	private ListInterface<String> list;
	
	@Before
	public void setup(){
          list = new RecursiveList<String>();
          list.insertFirst("hello");
          list.insertFirst("goodbye");
          list.insertFirst("snafu");
	}
	@Test
	public void testSize(){
		assertEquals(3,list.size());
	}
	@Test
	public void testInsertFirst(){
		list.insertFirst("first");
		assertEquals("first",list.getFirst());
		ListInterface<String> list2 = new RecursiveList<String>();
		list2.insertFirst("first");
		assertEquals("first",list.getFirst());
		assertEquals(1,list2.size());
		assertFalse(list.isEmpty());
	}
	@Test
	public void testInsertLast(){
		ListInterface<String> list1 = new RecursiveList<String>();
		list1.insertLast("last");
		assertEquals("last",list1.getLast());
		list1.insertLast("last2");
		assertEquals("last2",list1.getLast());
		assertFalse(list1.isEmpty());
		assertEquals(2,list1.size());
	}
	@Test
	public void testInsertAt(){
		ListInterface<String> test = new RecursiveList<String>();
		test.insertAt(0, "fail");
		assertEquals("fail",test.get(0));
		test.insertAt(0, "zero");
		test.insertAt(2, "two");
		assertEquals("zero", test.get(0));
		assertEquals("two", test.get(2));
		assertEquals("fail",test.get(1));
		assertFalse(list.isEmpty());
		assertEquals(3, list.size());
	}
	@Test
	public void testRemoveFirst(){
		assertEquals("snafu",list.removeFirst());
		assertEquals("goodbye",list.removeFirst());
		assertEquals("hello",list.removeFirst());
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		}
	@Test
	public void testRemoveLast(){
		assertEquals("hello",list.removeLast());
		assertEquals("goodbye",list.removeLast());
		assertEquals("snafu",list.removeLast());
		assertTrue(list.isEmpty());
		assertEquals(0,list.size());
	}
	@Test
	public void testremoveAt(){
		assertEquals("snafu",list.removeAt(0));
		assertEquals("hello",list.removeAt(1));
		assertEquals("goodbye",list.removeAt(0));
		assertEquals(0,list.size());
		assertTrue(list.isEmpty());
	}
	@Test
	public void testGet(){
		assertEquals("snafu",list.getFirst());
		assertEquals("hello",list.getLast());
		assertEquals("goodbye",list.get(1));
		assertEquals("snafu",list.get(0));
		assertEquals("hello",list.get(2));
		assertFalse(list.isEmpty());
		assertEquals(3,list.size());
	}
	@Test
	public void testRemove(){
		list.insertFirst("hello");
		assertTrue(list.remove("hello"));
		assertEquals(2, list.indexOf("hello"));
		assertTrue(list.remove("hello"));
		assertEquals(-1,list.indexOf("hello"));
		assertFalse(list.remove("hello"));
	}
	@Test
	public void testInsertandTestGet(){
		assertEquals(3,list.size());
		assertEquals("snafu",list.getFirst());
		assertEquals("goodbye",list.get(1));
		assertEquals("hello",list.getLast());
		list.insertAt(0, "foo");
		assertEquals("foo",list.getFirst());
		list.insertAt(3, "boo");
		assertEquals("boo",list.get(3));
		list.insertAt(5, "read");
		assertEquals("read",list.getLast());
		assertEquals("foo",list.get(0));
		assertEquals("read",list.get(5));
		assertEquals(6,list.size());
		assertEquals(-1,list.indexOf("nan"));
		
	}
	@Test
	public void testRemoveandTestIndex(){
		assertEquals("snafu",list.removeAt(0));
		assertEquals(0,list.indexOf("goodbye"));
		assertEquals(1,list.indexOf("hello"));
		assertEquals("hello",list.removeAt(1));
		assertEquals("goodbye",list.removeAt(0));
		assertTrue(list.isEmpty());
	}
	
	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		ListInterface<String> test = new RecursiveList<String>();
		assertTrue("Newly constructed list should be empty.", test.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, test.size());
		assertEquals("Insert First should return instance of self", test, test.insertFirst("hello"));
		assertFalse("List should now have elements.", test.isEmpty());
		assertEquals("List should now have 1 element.", 1, test.size());
		assertEquals("First element should .equals \"hello\".", "hello", test.getFirst());
		test.insertFirst("world");
		assertEquals(2, test.size());
		test.insertFirst("foo");
		assertEquals(3, test.size());
		assertEquals("First element should .equals \"foo\".", "foo", test.getFirst());
	}
	@Test
	public void testInsertion() {
		ListInterface<String> test = new RecursiveList<String>();
		assertTrue("empty",test.isEmpty());
		test.insertLast("hello");
		assertFalse("empty",test.isEmpty());
		assertEquals("hello",test.getLast());
		test.insertLast("goodbye");
		assertEquals("goodbye",test.getLast());
		assertEquals(2,test.size());
		test.insertAt(2, "boo");
		assertEquals("boo",test.getLast());
	}
	@Test
	public void testRemoval() {
		ListInterface<String> test = new RecursiveList<String>();
		test.insertFirst("one");
		test.insertFirst("two");
		test.insertFirst("three");
		assertEquals("three",test.removeFirst());
		assertEquals("one",test.removeLast());
		assertEquals("two",test.removeAt(0));
		assertTrue(test.isEmpty());
		test.insertFirst("snafu");
		test.insertFirst("bar");
		test.insertFirst("foo");
		assertEquals(3,test.size());
		assertEquals("snafu",test.removeLast());
		assertEquals(2,test.size());
		test.insertLast("bee");
		assertEquals(2,test.indexOf("bee"));
		assertEquals(-1,test.indexOf("nothing"));
		assertEquals("bee",test.removeAt(2));
		assertEquals(2,test.size());
		assertEquals("foo",test.removeAt(0));
		assertTrue(test.remove("bar"));
		assertTrue(test.isEmpty());
		
	}
//	@Test
//	public void test() {
//		
//	}
}
